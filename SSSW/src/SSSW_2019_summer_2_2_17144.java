import java.util.*;
import java.io.*;

public class SSSW_2019_summer_2_2_17144 {
    static int R;
    static int C;
    static int[][] home;
    static int cx;
    static int cy;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        home = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < C; j++) {
                home[i][j] = Integer.parseInt(st.nextToken());
                if (home[i][j] == -1) {
                    cx = i;
                    cy = j;
                }
            }
        }
        for (int t = 0; t < T; t++) {
            spread();
        }
        int result = 0;
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                result += home[i][j];
        sb.append(result + 2);
        System.out.println(sb.toString());

    }

    static void spread() {
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        int nx, ny, count;
        int[][] add = new int[R][C];
        // 확산
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                count = 0;
                for (int d = 0; d < 4; d++) {
                    nx = i + dx[d];
                    ny = j + dy[d];
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C || home[nx][ny] == -1)
                        continue;
                    count++;
                    add[nx][ny] += home[i][j] / 5;
                }
                add[i][j] -= (home[i][j] / 5) * count;
            }
        }
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                home[i][j] += add[i][j];

        // 공청
        // 1. 윗 부분
        nx = 0;
        ny = 0;
        int idx = 0;
        int x = cx - 2;
        int y = cy;
        while (x != cx - 1 || y != cy) {
            nx = x + dx[idx];
            ny = y + dy[idx];
            if (nx < 0 || ny < 0 || nx >= cx || ny >= C) {
                idx += 1;
                if (idx > 3)
                    idx = 0;
                nx = x + dx[idx];
                ny = y + dy[idx];
            }
            if (home[nx][ny] != -1)
                home[x][y] = home[nx][ny];
            else
                home[x][y] = 0;
            x = nx;
            y = ny;
        }
        // 2. 아랫 부분
        x = cx + 1;
        y = cy;
        while (x != cx || y != cy) {
            nx = x + dx[idx];
            ny = y + dy[idx];
            if (nx < cx || ny < 0 || nx >= R || ny >= C) {
                idx -= 1;
                if (idx < 0)
                    idx = 3;
                nx = x + dx[idx];
                ny = y + dy[idx];
            }
            if (home[nx][ny] != -1)
                home[x][y] = home[nx][ny];
            else
                home[x][y] = 0;
            x = nx;
            y = ny;
        }
    }
}