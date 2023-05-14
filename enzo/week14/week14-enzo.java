import java.util.*;
public class Enzo {
    class Pair {
        int y,x;
        public Pair(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        Pair[][] indexTable = new Pair[51][51];
        String[][] valueTable = new String[51][51];
        pairInit(indexTable);
        for(String c : commands){
            String [] curCommandList =  c.split(" ");
            String curCommand = curCommandList[0];
            switch(curCommand){
                case "UPDATE":
                    doUpdate(indexTable, valueTable,curCommandList);
                    break;
                case "MERGE":
                    doMerge(indexTable, valueTable,curCommandList);
                    break;
                case "UNMERGE":
                    doUnMerge(indexTable, valueTable, curCommandList);
                    break;
                case "PRINT":
                    doPrint(answer, curCommandList, indexTable, valueTable);
                    break;
            }
        }
        return answer.stream().toArray(String[]::new);
    }
    private boolean emptyString(String s){
        return s==null || s.isEmpty();
    }
    public void doUnMerge(Pair[][] indexTable, String[][] valueTable, String[] commandArg){
        int r1 = Integer.parseInt(commandArg[1]);
        int c1 = Integer.parseInt(commandArg[2]);
        Pair root = getRootIndex(r1,c1, indexTable);
        String value =  valueTable[root.y][root.x];
        List<Pair> pList = new ArrayList<>();
        for(int i=0;i<indexTable.length;i++){
            for(int j=0;j<indexTable[0].length;j++){
                Pair curRoot = getRootIndex(i,j, indexTable);
                if(root.y == curRoot.y && root.x == curRoot.x){
                    pList.add(new Pair(i,j));
                    valueTable[i][j] = "";
                }
            }
        }
        for(Pair p : pList){
            indexTable[p.y][p.x] = p;
        }
        valueTable[r1][c1] = value;
    }
    public void doMerge(Pair[][] indexTable, String[][] valueTable , String[] commandArg){
        int r1 = Integer.parseInt(commandArg[1]);
        int c1 = Integer.parseInt(commandArg[2]);
        int r2 = Integer.parseInt(commandArg[3]);
        int c2 = Integer.parseInt(commandArg[4]);
        Pair p1 =  getRootIndex(r1,c1, indexTable);
        Pair p2 =  getRootIndex(r2,c2, indexTable);
        if(p1.x == p2.x && p1.y == p2.y) return;
        String val1 = valueTable[p1.y][p1.x];
        String val2 = valueTable[p2.y][p2.x];
        if(!emptyString(val1)){
            indexTable[p2.y][p2.x] = p1;
            if(!emptyString(val2)){
                valueTable[p2.y][p2.x] = "";
            }
        }
        else{
            indexTable[p1.y][p1.x] = p2;
        }

    }
    public void doUpdate(Pair[][] indexTable, String[][] valueTable, String[] commandArg){
        if(commandArg.length ==3){
            String value1 = commandArg[1];
            String value2 = commandArg[2];
            for(int i=0;i<valueTable.length;i++){
                for(int j=0;j<valueTable[0].length;j++){
                    if(value1.equals(valueTable[i][j])){
                        valueTable[i][j] = value2;
                    }
                }
            }
        }
        else{
            int curR = Integer.parseInt(commandArg[1]);
            int curC = Integer.parseInt(commandArg[2]);
            Pair rootIndex = getRootIndex(curR,curC,indexTable);
            valueTable[rootIndex.y][rootIndex.x] = commandArg[3];
        }
    }
    public void doPrint(List<String> printer, String[] commandList, Pair[][] mergeTable, String[][] updateTable){
        int r = Integer.parseInt(commandList[1]);
        int c = Integer.parseInt(commandList[2]);
        Pair rootIndex = getRootIndex(r,c,mergeTable);
        String val = updateTable[rootIndex.y][rootIndex.x];
        val = (val == null || val.isEmpty()) ? "EMPTY" : val;
        printer.add(val);
    }
    private Pair getRootIndex(int r, int c, Pair[][] indexTable){
        int curIndexR = r;
        int curIndexC = c;
        while(true){
            Pair nextPair = indexTable[curIndexR][curIndexC];
            if(curIndexR == nextPair.y && curIndexC == nextPair.x) break;
            curIndexR = nextPair.y;
            curIndexC = nextPair.x;
        }
        return new Pair(curIndexR,curIndexC);
    }

    private void pairInit(Pair[][] table){
        for(int i=0;i<table.length;i++){
            for(int j=0; j<table[0].length;j++){
                table[i][j] = new Pair(i,j);
            }
        }
    }
}
