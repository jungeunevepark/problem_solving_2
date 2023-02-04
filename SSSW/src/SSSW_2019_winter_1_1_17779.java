import java.util.*;
import java.io.*;

public class SSSW_2019_winter_1_1_17779 {
    static int N;
    static int[][] city;
    static int result;
    static int x;
    static int y;
    static int d1;
    static int d2;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        city = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < N; j++)
                city[i][j] = Integer.parseInt(st.nextToken());
        }
        result = Integer.MAX_VALUE;
        dfs();
        sb.append(result).append("\n");
        System.out.println(sb.toString());

    }

    static void dfs() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (int ld = 1; ld < N; ld++)
                    for (int rd = 1; rd < N; rd++) {
                        if (i + ld + rd >= N || j + rd >= N || j - ld < 0)
                            continue;
                        x = i;
                        y = j;
                        d1 = ld;
                        d2 = rd;
                        check();

                    }

    }

    static void check() {
        int[][] check = new int[N][N];
        // 5번 선거구
        int n = 0;
        int j = y;
        int ld = 0;
        int rd = 0;
        int lflag = 0;
        int rflag = 0;
        while (true) {
            for (int k = j - ld; k < j + rd + 1; k++)
                check[x + n][k] = 5;
            n++;
            if (lflag == 0)
                ld += 1;
            else
                ld -= 1;
            if (rflag == 0)
                rd += 1;
            else
                rd -= 1;

            if (ld == d1)
                lflag = 1;
            if (rd == d2)
                rflag = 1;
            if (n == d1 + d2 + 1)
                break;

        }

        // 1번 선거구
        for (int i = 0; i < x + d1; i++)
            for (j = 0; j <= y; j++) {
                if (check[i][j] == 5)
                    continue;
                check[i][j] = 1;
            }
        // 2번 선거구
        for (int i = 0; i <= x + d2; i++)
            for (j = y + 1; j < N; j++) {
                if (check[i][j] == 5)
                    continue;
                check[i][j] = 2;
            }
        // 3번 선거구
        for (int i = x + d1; i < N; i++)
            for (j = 0; j <= y - d1 + d2; j++) {
                if (check[i][j] == 5)
                    continue;
                check[i][j] = 3;
            }

        // 4번 선거구
        for (int i = x + d2 + 1; i < N; i++)
            for (j = y - d1 + d2; j < N; j++) {
                if (check[i][j] == 5)
                    continue;
                check[i][j] = 4;
            }

        int[] pop = new int[5];
        for (int i = 0; i < N; i++)
            for (j = 0; j < N; j++)
                pop[check[i][j] - 1] += city[i][j];
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            if (pop[i] > max)
                max = pop[i];
            if (pop[i] < min)
                min = pop[i];
        }
        if (result > max - min)
            result = max - min;

    }
}