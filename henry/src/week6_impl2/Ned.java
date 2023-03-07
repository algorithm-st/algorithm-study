package week6_impl2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 문제 설명
 * - AC : 정수 배열에 연산을 하기 위해 만든 언어
 * - 두가지 함수
 * - R(뒤집기) : 배열에 있는 수의 순서를 뒤집는 함수
 * - D(버리기) : 첫 번째 수를 버리는 함수(배열이 비어 있는데 D를 사용한 경우에는 에러가 발생)
 *
 */
public class Ned {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.valueOf(bufferedReader.readLine());

        for (int i = 0; i < T; i++) {
            String[] opArray = bufferedReader.readLine().split("");
            Integer numberSize = Integer.valueOf(bufferedReader.readLine());
            String numberString = bufferedReader.readLine();
            List<String> numberList = Arrays.asList(numberString.substring(1, numberString.length()-1).split(","));
            if ("".equals(numberList.get(0))) {
                numberList = new ArrayList<>();
            }

            Deque<String> numberDeque = new ArrayDeque<>();
            numberList.stream().forEach(number -> numberDeque.add(number));
            acOperation(opArray, numberDeque);
        }
    }

    static void acOperation(String[] opArray, Deque<String> numberDeque) {
        Boolean dir = true;
        for (int i = 0; i < opArray.length; i++) {
            List<String> tmp = new ArrayList<>();

            if("R".equals(opArray[i])){
                dir = !dir;
            }else if ("D".equals(opArray[i])) {
                if (numberDeque.isEmpty()) {
                    System.out.println("error");
                    break;
                }

                if(dir){
                    numberDeque.poll();
                }else{
                    numberDeque.pollLast();
                }
            }

            if (i == opArray.length - 1) {
                System.out.print("[");

                List<String> tmpList = new ArrayList<>();

                while (!numberDeque.isEmpty()) {
                    if (dir) {
                        tmpList.add(numberDeque.poll());
                    }else{
                        tmpList.add(numberDeque.pollLast());
                    }
                }

                System.out.print(tmpList.stream().collect(Collectors.joining(",")));
                System.out.println("]");
            }
        }
    }
}
