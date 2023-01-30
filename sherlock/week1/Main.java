package sherlock.week1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_sherlock solution_sherlock = new Solution_sherlock();
        int answer = solution_sherlock.solution("one4seveneight");
        System.out.println("================================ Sherlock 시작 ================================ ");
        System.out.println(answer);
        System.out.println("================================ Sherlock 끝 ================================ ");

        Solution_han solution_han = new Solution_han();
        System.out.println("================================ Han 시작 ================================ ");
        solution_han.solution();
        System.out.println("================================ Han 끝 ================================ ");

//        Solution_henry solution_henry = new Solution_henry();
//        System.out.println("================================ Henry 시작 ================================ ");
//        solution_henry.solution();
//        System.out.println("================================ Henry 끝 ================================ ");

    }
}
