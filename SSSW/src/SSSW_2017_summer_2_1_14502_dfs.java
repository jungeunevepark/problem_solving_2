import java.util.*;
import java.io.*;

class Pair {
	public int x;
	public int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

public class SSSW_2017_summer_2_1_14502_dfs {
	static int N;
	static int M;
	static int[][] school;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		school = new int[N][M]; // 배열의 크기를 입력받은 뒤에 생성하기

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < M; j++)
				school[i][j] = Integer.parseInt(st.nextToken());
		}

		dfs(0, 0, 0);
		sb.append(result).append("\n");
		System.out.print(sb.toString()); // 출력
		bf.close();
	}

	static void dfs(int well, int x, int y) {
		if (well == 3) {
			count();
			return;
		}
		for (int i = x; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i == x && j < y)
					continue;
				if (school[i][j] == 0) {
					school[i][j] = 1;
					dfs(well + 1, i, j);
					school[i][j] = 0;
				}
			}
		}
	}

	static void count() {
		/*
		 * x+1열, y+1행과 상하좌우로 연결된 모든 구역을 조사한다.
		 * 이때 바이러스가 존재하면 0을 리턴한다.
		 * 바이러스가 없으면 0으로 표시된 (안전지대) 위치의 개수를 리턴한다.
		 * visited를 통해 중복체크를 한다.
		 * 
		 */
		ArrayDeque<Pair> q = new ArrayDeque<>();
		int[][] check = new int[N][M];
		int[][] visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (school[i][j] == 2) {
					check[i][j] = 2;
					visited[i][j] = 1;
					q.add(new Pair(i, j));
				} else if (school[i][j] == 1)
					check[i][j] = 1;
			}
		}
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { 1, -1, 0, 0 };

		while (!(q.isEmpty())) {
			Pair p = q.poll();
			int i = p.x;
			int j = p.y;

			k: for (int k = 0; k < 4; k++) {
				int nx = i + dx[k];
				int ny = j + dy[k];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] == 1 || check[nx][ny] == 1)
					continue k;
				visited[nx][ny] = 1;
				check[nx][ny] = 2;
				q.add(new Pair(nx, ny));
			}
		}
		int c = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (check[i][j] == 0)
					c++;

		if (result < c)
			result = c;

	}
}
