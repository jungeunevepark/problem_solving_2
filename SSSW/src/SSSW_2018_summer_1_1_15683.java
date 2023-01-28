import java.io.*;
import java.util.*;

public class SSSW_2018_summer_1_1_15683 {
    static int N;
    static int M;
    static int max;
    static int num;
    static int[][] office;
    static int[][] cctv;
    static int[][] dir;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        if (N > M)
            max = N;
        else
            max = M;
        office = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < M; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
                if (office[i][j] != 0 && office[i][j] != 6)
                    num++;
            }
        }
        cctv = new int[num][3];
        dir = new int[][] { { 0, 0, 0, 1 }, { 0, 1, 0, 1 }, { 1, 0, 0, 1 }, { 1, 1, 0, 1 }, { 1, 1, 1, 1 } };
        int k = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (office[i][j] != 0 && office[i][j] != 6) {
                    cctv[k][0] = i;
                    cctv[k][1] = j;
                    k++;
                }
        result = N * M;
        dfs(0);
        sb.append(result).append("\n");
        System.out.println(sb.toString());
    }

    static void dfs(int count) {
        if (count == num) {
            count();
            return;
        }
        if (office[cctv[count][0]][cctv[count][1]] == 2)
            for (int i = 0; i < 2; i++) {
                cctv[count][2] = i;
                dfs(count + 1);
            }
        else if (office[cctv[count][0]][cctv[count][1]] == 5) {
            cctv[count][2] = 1;
            dfs(count + 1);
        } else {
            for (int i = 0; i < 4; i++) {
                cctv[count][2] = i;
                dfs(count + 1);
            }
        }
    }

    static void count() {
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, -1, 0, 1 };
        int status = 0;
        int count = 0;
        int[][] check = new int[N][M];
        for (int u = 0; u < N; u++)
            System.arraycopy(office[u], 0, check[u], 0, M);
        int nx, ny;
        for (int i = 0; i < num; i++) {
            status = office[cctv[i][0]][cctv[i][1]];
            for (int d = 0; d < 4; d++) {
                if (dir[status - 1][d] == 0)
                    continue;
                end: for (int k = 1; k < max; k++) {
                    nx = cctv[i][0] + k * dx[(d + cctv[i][2]) % 4];
                    ny = cctv[i][1] + k * dy[(d + cctv[i][2]) % 4];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || office[nx][ny] == 6)
                        break end;
                    else
                        check[nx][ny] = 9;
                }
            }
        }

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (check[i][j] == 0)
                    count++;

        if (count < result)
            result = count;
    }
}
