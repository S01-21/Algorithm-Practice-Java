import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static char[][] map;
	static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};	// 상-하-좌-우
	static boolean[][] visA, visB;
	static int resA, resB;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visA = new boolean[N][N];	// 적록색약 아닌 사람용
		visB = new boolean[N][N];	// 적록색양인 사람용
		
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		for (int i=0; i< N; i++) {
			for (int j=0; j<N; j++) {
				if (visA[i][j])	continue;
				bfsA(i, j);
			}
		}
		for (int i=0; i< N; i++) {
			for (int j=0; j<N; j++) {
				if (visB[i][j])	continue;
				bfsB(i, j);
			}
		}
		
		System.out.println(resA + " " + resB);
	}
	static void bfsA(int x, int y) {
		Queue<Pair> queue = new ArrayDeque<>();
		visA[x][y] = true;
		queue.offer(new Pair(x, y));
		char c = map[x][y];
		
		while(!queue.isEmpty()) {
			Pair cur = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + deltas[dir][0];
				int ny = cur.y + deltas[dir][1];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)	continue;
				if (visA[nx][ny])	continue;
				if (c != map[nx][ny]) continue;
				visA[nx][ny] = true;
				queue.offer(new Pair(nx, ny));
			}
		}
		resA++;
	}
	static void bfsB(int x, int y) {
		Queue<Pair> queue = new ArrayDeque<>();
		visB[x][y] = true;
		queue.offer(new Pair(x, y));
		char c = map[x][y];
		
		while(!queue.isEmpty()) {
			Pair cur = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + deltas[dir][0];
				int ny = cur.y + deltas[dir][1];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)	continue;
				if (visB[nx][ny])	continue;
				if (c == 'B') {	// B 구역 찾고있을 경우
					if (c != map[nx][ny]) continue;
					else {
						visB[nx][ny] = true;
						queue.offer(new Pair(nx, ny));
					}
				} else {	// R, G 구역 찾고있을 경우
					if (map[nx][ny] == 'B') continue;
					else {
						visB[nx][ny] = true;
						queue.offer(new Pair(nx, ny));
					}
				}
			}
		}
		resB++;
	}
	static class Pair{
		int x, y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}