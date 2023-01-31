import java.io.*;
import java.util.*;

class Tree{
	int x;
	int y;
	int age;
	
	Tree(int x, int y, int age){
		this.x = x;
		this.y = y;
		this.age = age;
	}
	void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Tree [x=" + x + ", y=" + y + ", age=" + age + "]";
	}
}
class Node{
	int x;
	int y;
	Node(int x, int y){
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static int N;
	static int M;
	static int K;
	static int[][] A;
	static int[][] garden;
	static LinkedList<Tree> t;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j=0; j<N; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		t = new LinkedList<>();
		for(int i =0; i<M; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			Tree tree = new Tree(x-1, y-1, z);
			t.add(tree);
		}
		garden = new int[N][N];
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				garden[i][j] = 5;
		Collections.sort(t, (t1, t2)->-Integer.compare(t1.age, t2.age));
		for(int k = 0; k<K; k++) {
			count();
		}
		if(t.isEmpty()) sb.append(0).append("\n");
		else sb.append(t.size()).append("\n");
		System.out.println(sb.toString());
	}
	static void count() {
		int[] dx = {0, 0, 1, -1, 1, -1, 1, -1};
		int[] dy = {1, -1, 0, 0, -1, 1, 1, -1};
		int[][] add = new int[N][N];
		// spring
		for(int k = t.size()-1; k>=0; k--) {
			Tree tree = t.get(k);
			if(garden[tree.x][tree.y] >= tree.age) {
				garden[tree.x][tree.y] -= tree.age;
				tree.age += 1;
			}else {
				add[tree.x][tree.y] += tree.age/2;
				t.remove(t.get(k));
			}
		}
		//System.out.println();
		//summer
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				if(add[i][j] != 0)
					garden[i][j] += add[i][j];

		//fall
		int size = t.size();
		for(int i=0; i<size; i++) {
			Tree tree = t.get(i);
			if(tree.age % 5 == 0){
				for(int d = 0; d<8; d++) {
					int nx = tree.x + dx[d];
					int ny = tree.y + dy[d];
					if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
						t.add(new Tree(nx, ny, 1));
					}
				}
			}
		}
		//winter
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				garden[i][j] += A[i][j];
		
	}
}
