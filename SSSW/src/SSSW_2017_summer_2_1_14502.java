import java.util.*;
import java.io.*;

class Pair{
	public int x;
	public int y;
	public int turn;									// 저장된 현재값이 몇번째 벽인지
	public int bx;										// 두번째 세워진 벽의 위치를 저장
	public int by;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Pair(int x, int y, int turn) {
		this.x = x;
		this.y = y;
		this.turn = turn;
	}
	public Pair(int bx, int by, int x, int y, int turn) {
		this.x = x;
		this.y = y;
		this.turn = turn;
		this.bx = bx;
		this.by = by;
	}
}

public class SSSW_2017_summer_2_1_14502 {
	static int N;
	static int M;
	static int[][] school;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		school = new int[N][M];												// 배열의 크기를 입력받은 뒤에 생성하기
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j=0; j<M; j++)
				school[i][j] = Integer.parseInt(st.nextToken());
		}
		int result = 0;														// 가장 큰 면적을 담은 변수
		int now = 0;														// 현재 면적을 담은 변수
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(school[i][j] == 0) {										// 현 위치에 벽과 바이러스가 없다면 조사
					now = check(i, j);										// 함수를 통해 현재 위치에 벽이 있을 때의 최대 면적을 반환받음
					if(now > result) result = now;							// 결과값보다 크다면 갱신해줌
				}
			}
		}
		sb.append(result).append("\n");
		System.out.print(sb.toString());									// 출력
		bf.close();
	}
	static int check(int x, int y) {
		/*
		 * x+1열 y+1행에 벽이 세워졌을 때 가능한 최대 면적을 구하여 반환하는 함수
		 * 현재 벽이 세워진 곳보다 더 아래& 더 오른쪽에 벽을 총 세개 세워 가능한 모든 상태를 구현함.
		 * 
		 * */
		
		
		int max = 0;
		ArrayDeque<Pair> q = new ArrayDeque<>();
		q.add(new Pair(x, y, 1));											// 첫번째 세워진 벽이므로 1을 저장
		while (!(q.isEmpty())) {
			Pair p = q.poll();
			int nx = p.x;
			int ny = p.y;
			int turn = p.turn;												// p는 turn번째 세워진 벽의 위치를 저장하고 있다.
			
			if (turn == 3) {												// 세번째 벽까지 세워진 경우
				int r = 0;
				int[][] visited = new int[N][M];							// visited 배열을 통해 중복 체크
				school[x][y] = 1;
				school[p.bx][p.by] = 1;
				school[nx][ny] = 1;											// 벽을 세웠으므로 1로 바꾸어줌
				for(int i=0; i<N; i++) {
					s: for(int j=0; j<M; j++) {
						if(visited[i][j] == 1) continue s;					// 이미 확인한 위치는 확인하지 않음
						visited[i][j] = 1;
						if(school[i][j] != 1) {
							r += count(visited, i, j);						// 현재 안전구역을 함수로부터 반환 받음
						}
					}
				}
				if(max < r) 
					max = r;												// 최대값 갱신
				school[x][y] = 0;
				school[p.bx][p.by] = 0;
				school[nx][ny] = 0;											// 벽을 다시 허물어준다
			}
			else if(turn == 1) {											// 두번째 벽을 세우는 경우
				for(int j=ny+1; j<M; j++)
					if(school[nx][j] == 0)
						q.add(new Pair(nx, j, turn+1));						// 같은 열 오른쪽 위치를 다음에 조사한다.
				for(int i=nx+1; i<N; i++)
					for(int j=0; j<M; j++)
						if(school[i][j] == 0)
							q.add(new Pair(i, j, turn+1));					// 아래 위치를 다음에 조사한다. 그러므로 turn을 증가시켜줌
			}
			else {
				for(int j=ny+1; j<M; j++)									// 세번째 벽을 세울 위치를 조사. 이때 두번째 벽의 위치까지 저장해주어야 함.
					if(school[nx][j] == 0)
						q.add(new Pair(nx, ny, nx, j, turn+1));
				for(int i=nx+1; i<N; i++)
					for(int j=0; j<M; j++)
						if(school[i][j] == 0)
							q.add(new Pair(nx, ny, i, j, turn+1));
			}
		}
		return max;
	}
	static int count(int[][] visited, int x, int y) {
		/*
		 * x+1열, y+1행과 상하좌우로 연결된 모든 구역을 조사한다.
		 * 이때 바이러스가 존재하면 0을 리턴한다.
		 * 바이러스가 없으면 0으로 표시된 (안전지대) 위치의 개수를 리턴한다.
		 * visited를 통해 중복체크를 한다.
		 * 
		 * */
		
		ArrayDeque<Pair> q = new ArrayDeque<>();
		int flag  = 0;													// 1이면 구역에 바이러스가 있음
		q.add(new Pair(x, y));
		int result = 0;
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};
		
		while (!(q.isEmpty())) {
			Pair p = q.poll();
			int i = p.x;
			int j = p.y;
			if(school[i][j] == 0) result++;
			if(school[i][j] == 2) flag = 1;								// break하지 않음 visited를 위해 구역 내 모든 곳을 확인하고 1로 바꿔주어야 함
			
			k: for(int k=0; k<4; k++) {
				int nx = i+dx[k];
				int ny = j+dy[k];
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] == 1 || school[nx][ny] == 1) continue k;
				visited[nx][ny] = 1;
				q.add(new Pair(nx, ny));
			}
		}
		if (flag == 1) return 0;
		return result;
		
	}
}
