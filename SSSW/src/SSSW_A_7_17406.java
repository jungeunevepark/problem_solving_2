import java.util.*;
import java.io.*;

class Node {
    public int x;
    public int y;
    public int d;

    Node(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

public class SSSW_A_7_17406 {
    static int N;
    static int M;
    static int K;
    static int[][] A;
    static int[][] rotate;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        rotate = new int[K][];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            rotate[i] = new int[] { r - s - 1, c - s - 1, 2 * s + 1, 0 };
        }
        result = Integer.MAX_VALUE;
        dfs(0);
        sb.append(result).append("\n");
        System.out.println(sb.toString());
    }

    static void dfs(int count) {
        if (count == K) {
            int c = 0;
            int[][] check = new int[N][M];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < M; j++)
                    check[i][j] = A[i][j];
            while (c < K) {
                for (int i = 0; i < K; i++)
                    if (rotate[i][3] == c)
                        rotation(rotate[i][0], rotate[i][1], rotate[i][2], check);
                c++;
            }
            for (int i = 0; i < N; i++) {
                int sum = 0;
                for (int j = 0; j < M; j++)
                    sum += check[i][j];
                if (result > sum)
                    result = sum;
            }
            return;
        }

        for (int i = 0; i < K; i++) {
            if (rotate[i][3] != 0)
                continue;
            rotate[i][3] = count;
            dfs(count + 1);
            rotate[i][3] = 0;
        }
    }

    static void rotation(int r, int c, int s, int[][] A) {
        int now = 0;
        int[][] B = new int[s][s];
        while (now < s / 2) {
            for (int j = now; j < s - now - 1; j++)
                B[now][j + 1] = A[now + r][j + c];

            for (int i = now; i < s - now - 1; i++)
                B[i + 1][s - now - 1] = A[i + r][s - now + c - 1];

            for (int j = s - now - 1; j > now; j--)
                B[s - now - 1][j - 1] = A[s - now - 1 + r][j + c];

            for (int i = s - now - 1; i > now; i--)
                B[i - 1][now] = A[i + r][now + c];
            now++;
        }
        if (s % 2 == 1)
            B[s / 2][s / 2] = A[s / 2 + r][s / 2 + c];
        for (int i = 0; i < s; i++)
            for (int j = 0; j < s; j++)
                A[i + r][j + c] = B[i][j];

    }
}