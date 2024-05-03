import java.util.*;
import java.io.*;

// 골드 4. 뱀 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K, L;
	static int[][] map;
	static int dir;
	static int[] dx = {-1,0,1,0};	// 상[0] 우[1] 하[2] 좌[3] 
	static int[] dy = {0,1,0,-1};
	static Deque<Pair> snake;
	static Queue<Rotate> queue;
	static class Rotate{
		int x;
		char c;
		public Rotate(int x, char c) {
			super();
			this.x = x;
			this.c = c;
		}
	}
	static class Pair{
		int x, y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + "]";
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	
		K = Integer.parseInt(br.readLine());	// 사과 개수 
		map = new int[N+1][N+1];
		queue = new ArrayDeque<>();
		snake = new ArrayDeque<>();
		StringTokenizer st = null;
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 1;	// 사과 위치 1로 표시 
		}
		L = Integer.parseInt(br.readLine());	// 방향 변환 횟수
		for (int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			queue.offer(new Rotate(x, c));
		}
		
		dir = 1;	//초기 뱀의 방향은 오른쪽 
		int time = 1;
		map[1][1] = 2;	// 초기 뱀의 위치 2로 표시 
		snake.addFirst(new Pair(1, 1));
		while (true) {
			int nx = snake.getFirst().x + dx[dir];
			int ny = snake.getFirst().y + dy[dir];
			if (nx <= 0 || nx > N || ny <= 0 || ny > N)	break;
			if (map[nx][ny] == 2)	break;
			
			
			if (map[nx][ny] == 0){	// 사과 없으면 
				Pair tail = snake.getLast();
				map[tail.x][tail.y] = 0;
				snake.removeLast();		// 꼬리 없애기 
			}
			
			map[nx][ny] = 2;
			snake.addFirst(new Pair(nx, ny));	// 머리 추가 
			
			if (!queue.isEmpty() && queue.peek().x == time) {
				Rotate cur = queue.poll();
				if (cur.c == 'D') {	// 오른쪽 90도 회전 
					if (dir == 3) {
						dir = 0;
					} else {
						dir++;
					}
				} else if (cur.c == 'L') {	// 왼쪽 90도 회전 
					if (dir == 0) {
						dir = 3;
					} else {
						dir--;
					}
				}
			}
			time++;
		}
		System.out.println(time);
		br.close();
	}
}