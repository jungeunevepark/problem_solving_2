import java.util.*;
import java.io.*;

class Pipe {
    public int x;
    public int y;
    public int d;
}

public class SSSW_A_2_17406 {
    static int N;
    static int[][] room;
    static int result;
    static int[] dx;
    static int[] dy;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        N = Integer.parseInt(st.nextToken());

        room = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < N; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dx = new int[] { 0, 1, 1 };
        dy = new int[] { 1, 0, 1 };
        dfs(0, 1, 0); // 0 가로 1 세로 2 대각선
        sb.append(result).append("\n");
        System.out.println(sb.toString());

    }

    static void dfs(int px, int py, int pd) {
        if (px == N - 1 && py == N - 1) {
            result++;
            return;
        }

        for (int d = 0; d < 3; d++) {
            if (pd == 0 && d == 1)
                continue;
            else if (pd == 1 && d == 0)
                continue;
            int nx = px + dx[d];
            int ny = py + dy[d];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || room[nx][ny] == 1)
                continue;
            if (d == 2)
                if (room[nx][ny - 1] == 1 || room[nx - 1][ny] == 1)
                    continue;
            dfs(nx, ny, d);
        }
    }
}