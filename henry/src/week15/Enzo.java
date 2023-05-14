package week15;

import java.util.*;

public class Enzo {
    /**
     문제 설명
     - 표:50 x 50 고정, 초기 셀 비어있음, 문자열
     - MERGE r1 c1 r2 c2
     - 사이에 위치한 셀은 영향 x / 두셀 중 한 셀 값 -> 병합된 셀은 그값 / 모두 값 가지고 -> r1, c1기준
     - 어느 위치를 선택해도 병합된 셀로 접근
     - UNMERGE r c
     - 선택한 셀이 포함하고 있던 모든 셀은 프로그램 실행 초기의 상태로 돌아갑니다.
     - 값을 가지고 있었을 경우 (r, c) 위치의 셀이 그 값을 가지게 됩니다.
     */

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] input = {"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1",
                "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"};

        String[] answerArray = solution.solution(input);
        for (String answer : answerArray) {
            System.out.println(answer);
        }
    }
    static class Solution {

        static Cell[][] root = new Cell[51][51];
        static Cell[][] table = new Cell[51][51];

        public Cell find(Cell childCell){
            Cell findCell = root[childCell.r][childCell.c];
            if(findCell.equals(childCell)){
                return findCell;
            }else{
                root[childCell.r][childCell.c] = find(findCell);
                return root[childCell.r][childCell.c];
            }
        }

        public List<Cell> findCellList(Cell childCell){
            Cell rootCell = find(childCell);

            List<Cell> result = new ArrayList<>();
            for(int i=1; i<=50; i++){
                for(int j=1; j<=50; j++){
                    Cell parentCell = find(table[i][j]);
                    if (rootCell.equals(parentCell)) {
                        result.add(table[i][j]);
                    }
                }
            }
            return result;
        }

        public void union(Cell cellA, Cell cellB){
            Cell cellAParent = find(cellA);
            Cell cellBParent = find(cellB);
            root[cellAParent.r][cellAParent.c] = cellBParent;
        }

        public String[] solution(String[] commands) {
            String[] answer = {};
            List<String> printStringList = new ArrayList<>();

            for(int i=1; i<=50; i++){
                for(int j=1; j<=50; j++){
                    Cell cell = new Cell(i, j, null);
                    table[i][j] = cell;
                    root[i][j] = cell;
                }
            }

            for(String input : commands){
                String[] query = input.split(" ");
                if("UPDATE".equals(query[0])){
                    if(query.length == 4){
                        List<Cell> findCellList = findCellList(table[Integer.valueOf(query[1])][Integer.valueOf(query[2])]);
                        for (Cell cell : findCellList) {
                            cell.value = query[3];
                        }
                    }else{
                        for(int i=1; i<=50; i++){
                            for(int j=1; j<=50; j++){
                                if(table[i][j].value!= null && table[i][j].value.equals(query[1])){
                                    table[i][j].value =query[2];
                                }
                            }
                        }
                    }
                }else if("MERGE".equals(query[0])){
                    int r1 = Integer.valueOf(query[1]);
                    int c1 = Integer.valueOf(query[2]);
                    int r2 = Integer.valueOf(query[3]);
                    int c2 = Integer.valueOf(query[4]);

                    Cell cell1 = table[r1][c1];
                    Cell cell2 = table[r2][c2];
                    List<Cell> cell1List = findCellList(cell1);
                    List<Cell> cell2List = findCellList(cell2);

                    union(cell1, cell2);

                    String value = null;
                    if (cell1.value != null) {
                        value = cell1.value;
                    } else if (cell2.value != null) {
                        value = cell2.value;
                    }

                    for (Cell cell : cell1List) {
                        cell.value = value;
                    }
                    for (Cell cell : cell2List) {
                        cell.value = value;
                    }
                }else if("UNMERGE".equals(query[0])){
                    int r = Integer.valueOf(query[1]);
                    int c = Integer.valueOf(query[2]);
                    Cell cell = table[r][c];
                    String tmpValue = cell.value;

                    List<Cell> cellList = findCellList(cell);
                    for (Cell nowCell : cellList) {
                        root[nowCell.r][nowCell.c] = nowCell;
                        nowCell.value = null;
                    }

                    cell.value = tmpValue;

                }else if("PRINT".equals(query[0])){
                    String val = table[Integer.valueOf(query[1])][Integer.valueOf(query[2])].value;
                    if(val== null){
                        printStringList.add("EMPTY");
                    }else{
                        printStringList.add(val);
                    }
                }
            }

            return printStringList.toArray(new String[printStringList.size()]);
        }

        static class Cell{
            int r; int c; String value;
            Cell(int r, int c, String value){
                this.r = r; this.c = c; this.value = value;
            }

            public boolean equals(Cell obj){
                if(this.r == obj.r && this.c == obj.c){
                    return true;
                }else{
                    return false;
                }
            }
        }
    }
}
