import java.io.*;
import java.util.*;

class Tree {
	public int x;
	public int y;
	public int age;

	Tree(int x, int y, int age) {
		this.x = x;
		this.y = y;
		this.age = age;
	}

}

public class SSSW_2018_winter_2_1_16234 {
	static int N;
	static int M;
	static int K;
	static int[][] A;
	static int[][] garden;
	static ArrayDeque<Tree> t;
	static ArrayDeque<Tree> add;
	static int[] dx = { 0, 0, 1, -1, 1, -1, 1, -1 };
	static int[] dy = { 1, -1, 0, 0, -1, 1, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		garden = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				garden[i][j] = 5;
			}
		}
		t = new ArrayDeque<>();
		add = new ArrayDeque<>();
		ArrayList<Tree> temp = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			temp.add(new Tree(x - 1, y - 1, z));
		}
		Collections.sort(temp, (t1, t2) -> Integer.compare(t1.age, t2.age));
		for (int i = 0; i < temp.size(); i++)
			t.add(temp.get(i));
		for (int k = 0; k < K; k++) {
		}
		if (t.isEmpty())
			sb.append(0).append("\n");
		else
			sb.append(t.size()).append("\n");
		System.out.println(sb.toString());
	}

	static void count() {
		int[][] plus = new int[N][N];
		// spring + fall
		int nx = 0;
		int ny = 0;
		int size = t.size();
		while (size-- > 0) {
			Tree tree = t.poll();
			if (garden[tree.x][tree.y] >= tree.age) {
				garden[tree.x][tree.y] -= tree.age;
				tree.age += 1;
				if (tree.age % 5 == 0) {
					for (int d = 0; d < 8; d++) {
						nx = tree.x + dx[d];
						ny = tree.y + dy[d];
						if (nx < 0 || ny < 0 || nx >= N || ny >= N)
							continue;
						add.add(new Tree(nx, ny, 1));
					}
				}
				t.add(tree);
			} else {
				plus[tree.x][tree.y] += tree.age / 2;
			}
		}
		// fall
		while (!add.isEmpty()) {
			Tree tr = add.poll();
			t.addFirst(tr);
		}
		// winter
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				garden[i][j] += A[i][j] + plus[i][j];
	}
}