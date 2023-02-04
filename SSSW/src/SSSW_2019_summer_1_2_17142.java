import java.util.*;
import java.io.*;

class Virus {
    public int x;
    public int y;

    Virus(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class SSSW_2019_summer_1_2_17142 {
    static int N;
    static int M;
    static int[][] lab;
    static int result;
    static ArrayList<Virus> v;
    static ArrayDeque<Virus> energy;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][N];
        v = new ArrayList<>();
        energy = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < N; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 2)
                    v.add(new Virus(i, j));
            }
        }
        result = Integer.MAX_VALUE;
        dfs(0, 0);
        if (result == Integer.MAX_VALUE - 1)
            result = -1;
        sb.append(result).append("\n");
        System.out.println(sb.toString());

    }

    static void dfs(int count, int x) {
        if (count == M) {
            spread();
            return;
        }
        for (int i = x; i < v.size(); i++) {
            Virus vr = v.get(i);
            if (lab[vr.x][vr.y] == 3)
                continue;
            lab[vr.x][vr.y] = 3;
            dfs(count + 1, i);
            lab[vr.x][vr.y] = 2;
        }

    }

    static void spread() {
        int[] dx = { 0, 0, -1, 1 };
        int[] dy = { 1, -1, 0, 0 };
        int[][] visited = new int[N][N];
        int nx, ny;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (lab[i][j] == 3) {
                    energy.add(new Virus(i, j));
                    visited[i][j] = 1;
                }
        while (!energy.isEmpty()) {
            Virus vr = energy.poll();
            for (int d = 0; d < 4; d++) {
                nx = vr.x + dx[d];
                ny = vr.y + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] != 0)
                    continue;
                if (lab[nx][ny] == 1)
                    continue;
                else {
                    visited[nx][ny] = visited[vr.x][vr.y] + 1;
                    energy.add(new Virus(nx, ny));
                }

            }

        }

        int max = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (lab[i][j] != 0)
                    continue;
                if (visited[i][j] == 0)
                    max = Integer.MAX_VALUE;
                else if (visited[i][j] > max)
                    max = visited[i][j];

            }
        }
        if (result > max - 1)
            result = max - 1;
    }
}