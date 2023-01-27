import java.io.*;
import java.util.*;

public class SSSW_2017_winter_1_2_14889 {
    static int half;
    static int N;
    static int[] team;
    static int[][] power;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        power = new int[N][N]; // 능력치를 담을 변수
        team = new int[N]; // 0, 1의 값만 갖고 같은 숫자를 가진 i+1번은 같은 팀이다.
        half = N / 2;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < N; j++)
                power[i][j] = Integer.parseInt(st.nextToken());
        }
        result = Integer.MAX_VALUE; // 최솟값을 구하기 때문에 가장 최대값을 결과값에 넣어준다.
        dfs(0, 0);
        sb.append(result);
        System.out.println(sb.toString());
    }

    static void dfs(int member, int x) {
        if (member == half) { // 멤버가 반으로 나누어졌으므로 능력치를 계산해준다.
            int score1 = 0, score2 = 0; // 각 팀의 점수를 저장할 변수로 0으로 초기화 한다.
            for (int k = 0; k < N; k++) {
                for (int u = k + 1; u < N; u++) { // u도 0에서 시작하게 되면 같은 점수를 두번 더하게 된다.
                    if (team[k] == 0 && team[u] == 0)
                        score2 += power[k][u] + power[u][k];
                    if (team[k] == 1 && team[u] == 1)
                        score1 += power[k][u] + power[u][k];
                }
            }
            if (Math.abs(score1 - score2) < result) // 절댓값과 결과를 비교하여 더 작은 경우 갱신한다.
                result = Math.abs(score1 - score2);
            return;
        }

        for (int i = x + 1; i < N; i++) { // 원래는 x에서 시작해야 하지만 이 문제는 분할문제이므로 팀의 숫자가 중요하지 않아 +1에서 시작해도 된다.
            team[i] = 1;
            dfs(member + 1, i);
            team[i] = 0;
        }
    }
}
