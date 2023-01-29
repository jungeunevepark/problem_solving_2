import java.io.*;
import java.util.*;

public class SSSW_2018_summer_2_2_15686 {
    static int N;
    static int M;
    static int chicken;
    static int[][] chickens;
    static int[][] city;
    static int home;
    static int[][] homes;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        city = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 2)
                    chicken++;
                if (city[i][j] == 1)
                    home++;
            }
        }
        chickens = new int[chicken][3];
        homes = new int[home][2];
        int h = 0;
        int c = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (city[i][j] == 2) {
                    chickens[c][0] = i;
                    chickens[c++][1] = j;
                }
                if (city[i][j] == 1) {
                    homes[h][0] = i;
                    homes[h++][1] = j;
                }
            }
        result = Integer.MAX_VALUE;
        dfs(0, 0);
        sb.append(result).append("\n");
        System.out.println(sb.toString());
    }

    static void dfs(int market, int x) {
        if (market == M) {
            account();
            return;
        }

        for (int i = x; i < chicken; i++) {
            if (chickens[i][2] == 1)
                continue;
            chickens[i][2] = 1;
            dfs(market + 1, i);
            chickens[i][2] = 0;
        }
    }

    static void account() {
        int loads = 0;
        for (int i = 0; i < home; i++) {
            int load = Integer.MAX_VALUE;
            for (int j = 0; j < chicken; j++) {
                if (chickens[j][2] == 0)
                    continue;
                if (load > Math.abs(homes[i][0] - chickens[j][0]) + Math.abs(homes[i][1] - chickens[j][1]))
                    load = Math.abs(homes[i][0] - chickens[j][0]) + Math.abs(homes[i][1] - chickens[j][1]);
            }
            loads += load;
        }
        if (loads < result)
            result = loads;
    }
}
