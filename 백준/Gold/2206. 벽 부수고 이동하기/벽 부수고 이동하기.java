import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][][] dist;
	static int res;

	static class Pair {
		int x, y;
		int flag;

		public Pair(int x, int y, int flag) {
			super();
			this.x = x;
			this.y = y;
			this.flag = flag;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		dist = new int[N][M][2];	// 벽 부술 경우, 안부술 경우 나눠서 거리 저장 

		res = Integer.MAX_VALUE;
		bfs(0, 0);

		if (res == Integer.MAX_VALUE) {
			res = -1;
		}
		System.out.println(res);
	}

	private static void bfs(int x, int y) {
		Queue<Pair> queue = new ArrayDeque<>();
		dist[x][y][0] = 1; // 방문 표시(및 거리 기록)
		dist[x][y][1] = 1;
		queue.offer(new Pair(x, y, 0));

		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			if (cur.x == N-1 && cur.y == M-1) {
				res = dist[cur.x][cur.y][cur.flag];
				return;
			}
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if (nx == N - 1 && ny == M - 1) {
					res = dist[cur.x][cur.y][cur.flag] + 1;
					return;
				}
				
				if (cur.flag == 0) {	// 벽 부순적 없는 경우 
					if (dist[nx][ny][1] == 0 && map[nx][ny] == 1) {	// 방문한적 없는 벽이면 부수고 이동
						dist[nx][ny][1] = dist[cur.x][cur.y][cur.flag] + 1;
						queue.offer(new Pair(nx, ny, 1));
					}
				}
				
				// 방문한적 없는 빈칸은 그냥 이동 (현재 상태 그대로)
				if (map[nx][ny] == 0 && dist[nx][ny][cur.flag] == 0) {
					dist[nx][ny][cur.flag] = dist[cur.x][cur.y][cur.flag] + 1;
					queue.offer(new Pair(nx, ny, cur.flag));
				}
			}
		}
	}
}