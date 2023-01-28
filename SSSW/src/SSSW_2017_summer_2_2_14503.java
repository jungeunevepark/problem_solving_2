import java.io.*;
import java.util.*;

public class SSSW_2017_summer_2_2_14503 {
    static int N;
    static int M;
    static int[][] home;
    static int result = 0;
    static int rx;
    static int ry;
    static int rd; // 로봇의 위치와 방향
    static int check; // 로봇이 현재 위치에서 사방을 체크했는지 확인하는 변수

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        home = new int[N][M];
        st = new StringTokenizer(bf.readLine(), " ");
        rx = Integer.parseInt(st.nextToken());
        ry = Integer.parseInt(st.nextToken());
        rd = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < M; j++)
                home[i][j] = Integer.parseInt(st.nextToken());
        }
        clean();
        sb.append(result);
        System.out.println(sb.toString());
    }

    static void clean() {
        int[][] visited = new int[N][M]; // 이미 청소가 완료된 구역은 1로 표기
        int[] dx = { 0, -1, 0, 1 };// i번째 위치에서 왼쪽방향으로 전진하게 하는 배열
        int[] dy = { -1, 0, 1, 0 }; // 즉, rx += dx[동]이면 rx는 서쪽으로 1칸 전진하게 된다

        while (true) {
            int nx, ny;
            if (visited[rx][ry] == 0) {
                visited[rx][ry] = 1;
                result++;
            } // 1번을 수행하여 현재 위치를 청소한다.
            if (check == 4) {
                if (rd < 1) {
                    nx = rx + dx[3];
                    ny = ry + dy[3];
                } else {
                    nx = rx + dx[rd - 1];
                    ny = ry + dy[rd - 1];
                }
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || home[nx][ny] == 1)
                    return;
                else {
                    rx = nx;
                    ry = ny;
                    check = 0;
                }
            }
            nx = rx + dx[rd];
            ny = ry + dy[rd];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M && visited[nx][ny] != 1 && home[nx][ny] != 1) {
                rx = nx;
                ry = ny;
                check = 0;
            } else
                check++;
            rd--;
            if (rd < 0)
                rd = 3;
        }

    }
}
