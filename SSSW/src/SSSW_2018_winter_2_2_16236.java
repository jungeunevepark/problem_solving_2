import java.io.*;
import java.util.*;

class Node {
    public int x = 0;
    public int y = 0;
    public int count = Integer.MAX_VALUE;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Node(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}

public class SSSW_2018_winter_2_2_16236 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                map[i][j] = sc.nextInt();

        int sx = 0, sy = 0; // 아기상어의 현재 위치를 담은 변수
        int size = 2; // 아기상어의 크기
        int eat = 0; // 아기상어가 먹은 물고기의 수
        int result = 0; // 아기상어가 움직인 총 칸의 수
        int move; // 아기상어가 한마리 물고기를 먹을 때 움직인 칸의 수

        while (true) {
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    if (map[i][j] == 9) {
                        sx = i;
                        sy = j;
                    } // 매번 아기상어의 현재 위치를 갱신한다.
            move = move(sx, sy, N, size, map); // 함수를 통해 아기상어가 몇 칸 움직였는지 return 변수 값을 받는다.
            if (move == 0)
                break; // 변수값이 0인 경우 움직이지 않았으므로 while을 종료하고 결과값을 출력한다.
            result += move;
            eat++;
            if (eat == size) {
                size++;
                eat = 0;
            } // 먹은 물고기의 수와 아기상어의 크기가 같으면 아기상어 크기를 키우고 물고기의 수를 0으로 바꾼다.
        }
        System.out.println(result);
    }

    static int find(int x, int y, int nx, int ny, int N, int size, int[][] map) {
        /*
         * 아기상어가 주어진 위치까지 이동하는 최소 칸의 수를 구하는 함수
         * 
         * Parameter
         * int x, y : 아기상어의 위치
         * int nx, ny : 아기상어가 이동하고자 하는 위치
         * int N : map의 행열 길이
         * int size : 아기상어의 크기
         * int map : 지도 배열
         * 
         * Return
         * int : 아기상어가 움직이는 최소 칸의 수
         */

        int[] di = { 0, 0, -1, 1 };
        int[] dj = { 1, -1, 0, 0 };

        Queue<Node> q = new LinkedList<>();
        int visited[][] = new int[N][N];
        q.add(new Node(x, y, 0)); // count는 (x, y)에서 몇칸 이동했는지를 의미하므로 0으로 넣어 객체 생성
        visited[x][y] = 1;

        while (!q.isEmpty()) {
            Node p = q.poll();
            int i = p.x;
            int j = p.y;
            int count = p.count;
            if (i == nx && j == ny)
                return count; // 현재 위치에 도착한 경우 count값을 리턴하고 함수 종료

            label: for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];
                if (ni < 0 || nj < 0 || N <= ni || N <= nj || visited[ni][nj] == 1 || map[ni][nj] > size)
                    continue label; // 배열을 벗어나거나 이미 방문했거나 물고기의 크기가 아기상어의 크기보다 큰 경우 방문하지 않는다.
                visited[ni][nj] = 1; // Tip : 어느 경로든 이 위치에 가장 먼저 도착한 경로가 현재 위치를 포함한 모든 경로중 가장 최단 시간이므로
                                     // 재방문은 고려하지 않아도 된다.
                q.add(new Node(ni, nj, count + 1)); // 현재 위치에서 다음 위치가 한 칸 이동했으므로 count + 1과 함께 위치를 queue에 저장
            }
        }
        return Integer.MAX_VALUE;
    }

    static int move(int x, int y, int N, int size, int[][] map) {
        /*
         * map을 통해 아기상어가 먹을 수 있는 물고기의 모든 위치를 고려하여
         * 가장 가까운 물고기를 찾아 물고기를 잡아먹고 이동하는 함수
         * 
         * Parameter
         * int x, y : 아기상어의 위치
         * int N : map의 행열 길이
         * int size : 아기상어의 크기
         * int map : 지도 배열
         * 
         * Return
         * int : 아기상어가 움직이는 칸의 수
         */
        // 아기 상어가 이동할 위치를 담은 변수
        Node n = new Node(x, y); // 이 함수는 최단 경로를 통해 아기상어를 이동하게 해야 하므로 count값을 가장 큰 값으로 준다.(즉, 설정하지 않음)

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0 && map[i][j] != 9 && map[i][j] < size) { // 배열의 모든 위치를 조사하여 아기상어보다 크기가 작은 물고기를 발견하면
                    int next = find(x, y, i, j, N, size, map); // 함수를 통해 그 위치까지 가는 최소 칸의 수를 구한다.
                    if (n.count > next && next < N * N) { // n의 칸수 보다 작은 경우 n을 갱신해준다.
                        n = new Node(i, j, next);
                    } // Tip : 이중 loop에서 이미 가장 위 -> 가장 왼쪽 순으로 조사하고 있으므로 같은 경우는 먼저 조사된 경우를 반영
                } // 즉 두 값이 같은 경우는 고려하지 않아도 된다.
            }
        }

        if (n.x == x && n.y == y)
            return 0; // 아기상어가 이동하지 않는다.
        map[n.x][n.y] = 9;
        map[x][y] = 0;
        return n.count; // 배열 갱신후 이동 칸의 수를 return 한다.
    }
}
