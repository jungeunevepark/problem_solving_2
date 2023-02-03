import java.util.*;
import java.io.*;

public class SSSW_2018_summer_1_2_15684 {
    static int N;
    static int M;
    static int H;
    static int count;
    static int result;
    static int[][] ladder;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[H][N - 1];
        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            ladder[a - 1][b - 1] = 1;
        }
        result = -1;
        count = 0;
        while (count <= 3) {
            dfs(0, 0, 0);
            if (result != -1)
                break;
            count++;
        }
        sb.append(result).append("\n");
        System.out.println(sb.toString());
    }

    static void dfs(int c, int x, int y) {
        if (c == count) {
            if (goladder() == 0) {
                result = count;
            }
            return;
        }
        for (int i = x; i < H; i++) {
            r: for (int j = 0; j < N - 1; j++) {
                if (ladder[i][j] == 1)
                    continue r;
                if (j != 0 && ladder[i][j - 1] == 1)
                    continue r;
                ladder[i][j] = 1;// 설정
                dfs(c + 1, i, j);
                ladder[i][j] = 0;
            }
        }
    }

    static int goladder() {
        int flag = 0;
        int x = 0;
        int now = 0;
        int check = 0;
        f: for (int i = 0; i < N; i++) {
            x = i;
            now = 0;
            while (now < H) {
                check = 0;
                if (x > 0) {
                    if (ladder[now][x - 1] == 1) {
                        x -= 1;
                        check = 1;
                    }
                }
                if (x < N - 1) {
                    if (check == 0 && ladder[now][x] == 1) {
                        x += 1;
                        check = 1;
                    }
                }
                now++;
            } // 사다리 끝에 도착
            if (x != i) {
                flag = 1;
                break f;
            } // 같은 값으로 내려왔는지 확인
        }
        return flag;
    }
}