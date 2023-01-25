import java.util.*;
import java.io.*;
/*
 * 톱니바퀴의 회전방향을 구하여 12시 방향 위치에 따라 점수를 구하는 문제
 * */

public class SSSW_2017_winter_2_2_14891 {
    public static void main(String[] args) throws Exception {
        // Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("res/input.txt"));
        String[] settings = new String[4]; // 톱니바퀴의 극(N, S)을 저장한 배열
        for (int i = 0; i < 4; i++)
            settings[i] = sc.next(); // 톱니바퀴의 극 들을 배열에 저장
        int k = sc.nextInt();
        for (int i = 0; i < k; i++) {
            int num = sc.nextInt();
            int vector = sc.nextInt(); // k번째 회전은 num번째 톱니바퀴를 vector 방향으로 하는 것이다.
            int[] result = new int[4]; // result[i]에는 i번째 톱니바퀴의 회전 방향이 담겨있다
            num--;
            result[num] = vector;
            for (int j = num - 1; j >= 0; j--) { // 현재보다 왼쪽 톱니바퀴들의 방향 결정
                if (settings[j].charAt(2) != settings[j + 1].charAt(6)) // 오른쪽 톱니바퀴와 극이 다르면
                    result[j] = (-1) * result[j + 1]; // -1을 곱해 회전 방향을 반대로(0인 경우 0이 저장됨)
                else
                    result[j] = 0; // 극이 같다면 회전하지 않음
            }
            for (int j = num + 1; j < 4; j++) { // 현재보다 오른쪽 톱니바퀴들의 방향 결정
                if (settings[j].charAt(6) != settings[j - 1].charAt(2))
                    result[j] = (-1) * result[j - 1];
                else
                    result[j] = 0;
            }
            for (int j = 0; j < 4; j++)
                change_setting(settings, j, result[j]); // 함수를 통해 톱니바퀴를 회전해준다.
        }
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result += (settings[i].charAt(0) - '0') * Math.pow(2, i); // 점수 계산. N극인 경우 settings값이 0이므로 0이 더해진다.
        }
        System.out.println(result);
        sc.close();
    }

    static void change_setting(String[] s, int num, int vector) {
        /*
         * num번째 톱니바퀴를 vector방향으로 회전한뒤 배열에 다시 저장하는 함수
         * 
         * parameter
         * String[] s : 톱니바퀴의 극을 저장한 배열
         * int num : 회전할 톱니바퀴의 위치
         * int vector: 회전 방향
         * 
         */

        String news = ""; // 회전한 뒤 극의 위치들을 담은 변수
        if (vector == 0)
            return; // 회전하지 않으므로 바로 return하여 함수를 빠져나감
        else if (vector == -1) {
            news = s[num].substring(1, 8);
            news += String.valueOf(s[num].charAt(0)); // 반시계 방향인 경우 톱니바퀴의 극들이 index 기준으로 왼쪽으로 한칸씩 밀린다
        } else if (vector == 1) {
            news = String.valueOf(s[num].charAt(7));
            news += s[num].substring(0, 7); // 시계방향은 오른쪽으로 한칸씩 밀린다.
        }
        s[num] = news; // 배열에 다시 저장한다.
    }
}