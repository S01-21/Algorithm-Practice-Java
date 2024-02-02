import java.util.*;
import java.io.*;

public class Main {
	static class Pair {
		int x, y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int N, M;
	static int[][] map;
	static int max = Integer.MIN_VALUE;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		setWall(0);
		System.out.println(max);

	}

	private static void setWall(int cnt) {
		if (cnt == 3) {
			func();
			return;
		}
		// 벽 세울지 말지 결정
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					setWall(cnt + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	private static void func() {
		// 해당 상태에서 바이러스 퍼진 상태 구하기
		int[][] tmpArray = new int[N][M];
		for (int i = 0; i<N; i++) {
			tmpArray[i] = map[i].clone();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmpArray[i][j] == 2) {
					tmpArray = bfs(i, j, tmpArray);
				}
			}
		}
		
		int safeArea = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmpArray[i][j] == 0) {
					safeArea++;
				}
			}
		}
		max = Math.max(max, safeArea);
	}

	private static int[][] bfs(int x, int y, int[][] tmp) {
		Queue<Pair> queue = new ArrayDeque<>();
		queue.offer(new Pair(x, y));
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			int curX = cur.x;
			int curY = cur.y;
			for (int dir = 0; dir < 4; dir++) {
				int nx = curX + deltas[dir][0];
				int ny = curY + deltas[dir][1];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M)	continue;
				if (tmp[nx][ny] != 0)	continue;	
				queue.offer(new Pair(nx, ny));
				tmp[nx][ny] = 2;
			}
		}
		return tmp;
	}
}