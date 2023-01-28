import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SSSW_2017_winter_2_1_14890 {
    static int N;
    static int L;
    static float[][] map;

    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new float[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        set();
        sb.append(result).append("\n");
        System.out.println(sb.toString());
    }

    static void set() {
        int start, flag;
        // 열 확인
        for (int i = 0; i < N; i++) {
            start = (-1) * L;
            flag = 0;
            for (int j = 1; j < N; j++) {
                if ((int) map[i][j - 1] == (int) map[i][j] + 1) {
                    start = j;
                    flag = 0;
                } else if ((int) map[i][j - 1] != (int) map[i][j])
                    flag = 1;
                if (j - start == L - 1 && flag == 0) {
                    for (int k = start; k <= j; k++)
                        map[i][k] += 0.4;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            start = N + L;
            flag = 0;
            for (int j = N - 2; j >= 0; j--) {
                if ((int) map[i][j + 1] == (int) map[i][j] + 1) {
                    start = j;
                    flag = 0;
                } else if ((int) map[i][j + 1] != (int) map[i][j])
                    flag = 1;
                if (start - j == L - 1 && flag == 0) {
                    for (int k = start; k >= j; k--)
                        map[i][k] += 0.4;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            flag = 0;
            for (int j = 1; j < N; j++)
                if (Math.abs((float) ((int) map[i][j - 1]) - map[i][j]) >= 1)
                    flag = 1;
            if (flag == 0)
                result++;
        }
        // 행 확인
        for (int j = 0; j < N; j++) {
            start = (-1) * L;
            flag = 0;
            for (int i = 1; i < N; i++) {
                map[i][j] = (int) map[i][j];
                if ((int) map[i - 1][j] == (int) map[i][j] + 1) {
                    start = i;
                    flag = 0;
                } else if ((int) map[i - 1][j] != (int) map[i][j])
                    flag = 1;
                if (i - start == L - 1 && flag == 0) {
                    for (int k = start; k <= i; k++)
                        map[k][j] += 0.4;
                }
            }
        }
        for (int j = 0; j < N; j++) {
            start = N + L;
            flag = 0;
            for (int i = N - 2; i >= 0; i--) {
                if ((int) map[i + 1][j] == (int) map[i][j] + 1) {
                    start = j;
                    flag = 0;
                } else if ((int) map[i + 1][j] != (int) map[i][j])
                    flag = 1;
                if (start - i == L - 1 && flag == 0) {
                    for (int k = start; k >= i; k--)
                        map[k][j] += 0.4;
                }
            }
        }
        for (int j = 0; j < N; j++) {
            flag = 0;
            for (int i = 1; i < N; i++)
                if (Math.abs((int) map[i - 1][j] - map[i][j]) >= 1)
                    flag = 1;
            if (flag == 0)
                result++;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf("%.1f ", map[i][j]);
            System.out.println();
        }
    }
}
