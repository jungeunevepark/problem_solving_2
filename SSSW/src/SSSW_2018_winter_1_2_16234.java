import java.util.*;

/**
 * 문제 설명: N*N 크기의 격자에 국가가 존재하고 각 칸에는 나라가 존재한다.
 * 나라 간 인구수의 차이가 L이상 R이하라면 국경선을 넘어 인구 이동이 가능하다.
 * 이때 인구의 이동은 (연결된 나라의 총 인구수) / (연결된 나라의 수)가 될때까지 이동한다.
 * 더 이상 이동할 수 없을 때까지 인구 이동은 몇 번 일어나는 지 구하시오.
 */
class Pair {
    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class SSSW_2018_winter_1_2_16234 {
    public static void main(String[] args) throws Exception {
        // N, L, R 입력 받음
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int L = sc.nextInt();
        int R = sc.nextInt();

        // 나라 배열 A 선언
        // 각 나라의 인구수를 값으로 하는 배열
        int[][] A = new int[N][N];

        // A 값 할당: i=행, j=열
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = sc.nextInt();

        int result = 0; // 인구 이동 횟수
        while (true) {
            if (migration(A, L, R) == 0)
                break;
            result++;
        }
        System.out.println(result);
    }

    static int migration(int[][] A, int L, int R) {
        /*
         * 상, 하, 좌, 우 인접한 나라와 인구수를 비교해 국경선이 열려있는 경우 나라 간 인구 이동을 시행하는 함수
         * 
         * Parameter
         * int[][] A: 각 나라의 인구수 표시
         * int L : 연결된 나라의 인구의 차이가 L이상인 경우
         * int R : 연결된 나라의 인구의 차이가 R이하인 경우 국경선 열기
         * 
         * Return
         * int : 이주를 하는 경우 1, 안하는 경우 0을 반환
         */

        int[][] visited = new int[A.length][A.length]; // 그룹에 속해 인구 이동을 완료한 경우 1로 표시

        int[] di = { 0, 0, -1, 1 };
        int[] dj = { 1, -1, 0, 0 };

        int flag = 0; // return 값 정의

        for (int i = 0; i < A.length; i++) {
            label: for (int j = 0; j < A.length; j++) {
                // 그룹의 총 인구수
                int count = A[i][j];
                // 그룹의 개수
                int cnt = 1;
                // 그룹을 찾는데 쓰는 queue
                Queue<Pair> q = new LinkedList<>();
                // 해당 그룹을 이주하는데 사용하는 queue
                Queue<Pair> move = new LinkedList<>();

                // visited가 1이면 이미 그룹에 속해 이동을 마친 나라이므로 넘어간다.
                if (visited[i][j] == 1)
                    continue label;

                // 아닌 경우 (i, j)에서부터 국경선이 열려 연결된 나라 그룹을 찾는다.
                q.add(new Pair(i, j));
                move.add(new Pair(i, j));
                visited[i][j] = 1;

                // 더 이상 그룹에 들어올 나라가 없는 경우 while문 종료
                while (!q.isEmpty()) {
                    Pair p = q.poll();

                    l: for (int d = 0; d < 4; d++) {
                        // 상, 하, 좌, 우 조사
                        int ni = p.x + di[d];
                        int nj = p.y + dj[d];

                        // 주어진 땅 크기를 넘어가거나 이미 이동을 마친 나라의 경우 넘어감
                        if (ni < 0 || nj < 0 || ni >= A.length || nj >= A.length || visited[ni][nj] == 1)
                            continue l;

                        // 인구 차이를 조사하여 국경선을 열지 말지 결정
                        if (Math.abs(A[p.x][p.y] - A[ni][nj]) >= L && Math.abs(A[ni][nj] - A[p.x][p.y]) <= R) {
                            visited[ni][nj] = 1;
                            count += A[ni][nj];
                            cnt++;

                            // 방문 표시를 하고, 다음 조사할 나라로 queue에 넣어줌
                            q.add(new Pair(ni, nj));
                            move.add(new Pair(ni, nj));
                        }
                    }
                }
                int m = count / cnt;
                // 어떤 그룹에 나라가 2개 이상인 경우 국경선이 열려있다는 뜻이므로 flag 값 변경
                if (cnt != 1)
                    flag = 1;
                while (!move.isEmpty()) {
                    Pair np = move.poll();
                    A[np.x][np.y] = m;

                } // 인구 이동
            }

        }
        return flag;
    }
    // migration end
}
