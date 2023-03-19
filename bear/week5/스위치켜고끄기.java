package bear.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스위치켜고끄기 {

    static int count;
    static int[] switches;

    static int[] students;

    static StringTokenizer st;


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        count = Integer.parseInt(in.readLine());

        switches = new int[count];

        st = new StringTokenizer(in.readLine());

        for (int i = 0; i < switches.length; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        students = new int[Integer.parseInt(in.readLine())];


        for (int i = 0; i < students.length; i++) {
            st = new StringTokenizer(in.readLine());

            int gender = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            if (gender == 1) {

                for (int j = 0; j < switches.length; j++) {

                    if (((j + 1) % number) == 0) {
                        if (switches[j] == 1) {
                            switches[j] = 0;
                        } else {
                            switches[j] = 1;
                        }
                    }

                }
            } else {

                for (int j = 1; j < switches.length / 2; j++) {
                    if (number - j - 1 >= 0 && j + number - 1 < switches.length) {

                        if (switches[number - 1 - j] == switches[number - 1 + j]) {
                            if (switches[number - 1 - j] == 1) {
                                switches[number - 1 - j] = 0;
                                switches[number - 1 + j] = 0;

                            } else {
                                switches[number - 1 - j] = 1;
                                switches[number - 1 + j] = 1;
                            }
                        } else {
                            break;
                        }

                    } else {
                        break;
                    }
                }

                if(switches[number-1] == 1) {
                    switches[number-1] = 0;
                }
                else {
                    switches[number - 1] = 1;
                }
            }

        }
        for(int i = 0 ; i < switches.length; i++) {
            System.out.printf(switches[i] + " ");
            if((i+1) % 20 == 0) {
                System.out.println();
            }
        }


    }
}
