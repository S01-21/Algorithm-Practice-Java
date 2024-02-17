import java.util.*;
import java.io.*;

// 달이 차오른다, 가자.
public class Main {
	static int N, M;
	static char[][] maze;
	static int startX, startY; // 민식이의 현재 위치
	static int res; // 이동횟수 최솟값 (결과)
	static boolean[][][] vis;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상-하-좌-우
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = str.charAt(j);
				if (maze[i][j] == '0') {
					startX = i;
					startY = j;
				}
			}
		}

		res = Integer.MAX_VALUE;
		vis = new boolean[N][M][64];	// 6개 key 있으니까 2^6개의 방문체크배열 
		
		bfs(startX, startY);
		if (res == Integer.MAX_VALUE)
			res = -1;
		
		System.out.println(res);
	}

	private static void bfs(int x, int y) {
		Queue<Pair> queue = new ArrayDeque<>();
		queue.offer(new Pair(x, y, 0, 0));
		vis[x][y][0] = true;
		
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + deltas[dir][0];
				int ny = cur.y + deltas[dir][1];
				if (isOut(nx, ny))	continue;	// 범위체크
				if (vis[nx][ny][cur.flag])	continue;	// 방문체크
				
				char c = maze[nx][ny];
				if (c == '#')
					continue; // 벽은 못지나감
				if (c == '1') { // 탈출
					res = Math.min(res, cur.dist+1);
					return;
				}
				if (c == '.' || c == '0') { // 빈칸이면 이동
					queue.offer(new Pair(nx, ny, cur.dist + 1, cur.flag));
					vis[nx][ny][cur.flag] = true;
					continue;
				}
				if ('a' <= c && c <= 'f') { // 열쇠면 flag 세팅 후 이동
					queue.offer(new Pair(nx, ny, cur.dist + 1, cur.flag | 1 << (c - 'a')));
					vis[nx][ny][cur.flag | 1 << (c - 'a')] = true;
					continue;
				}
				if ('A' <= c && c <= 'F') {// 문이면
					if ((cur.flag & 1 << (c - 'A')) != 0) { // 열쇠가 있으면 지나감
						queue.offer(new Pair(nx, ny, cur.dist + 1, cur.flag));
						vis[nx][ny][cur.flag] = true;
						continue;
					}
				}
			}
		}
	}

	private static boolean isOut(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}

	static class Pair {
		int x, y;
		int dist;
		int flag;

		public Pair(int x, int y, int dist, int flag) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.flag = flag;
		}
	}
}