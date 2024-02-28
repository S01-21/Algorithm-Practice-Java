import java.util.*;
import java.io.*;

// 홈 방범 서비스 
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int homeCnt;
	static int maxCnt;
	static boolean[][] vis;
	static class Pair{
		int x, y, cnt;
		public Pair(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// NxN 크기 
			M = Integer.parseInt(st.nextToken());	// 하나의 집이 지불할 수 있는 비용 
			map = new int[N][N];
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			maxCnt = Integer.MIN_VALUE;
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					for (int k=1; k<=N+1; k++) {
						homeCnt = 0;
						func(i, j, k);
						int profit = homeCnt*M - (k*k + (k-1)*(k-1));
						if (profit >= 0) {
							maxCnt = Math.max(maxCnt, homeCnt);
						}
					}
				}
			}
			sb.append(maxCnt).append("\n");
		}
		System.out.println(sb);
	}
	private static void func(int x, int y, int size) {
		vis = new boolean[N][N];
		if (map[x][y] == 1) {
			homeCnt++;
		}
		vis[x][y] = true;
		Queue<Pair> queue = new ArrayDeque<>();
		queue.offer(new Pair(x, y, 1));
		
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			if (cur.cnt == size) {
				return;
			}
			for (int dir = 0; dir <4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (isOut(nx, ny))	continue;
				if (vis[nx][ny])	continue;
				if (map[nx][ny] == 1)	homeCnt++;
				vis[nx][ny] = true;
				queue.offer(new Pair(nx, ny, cur.cnt + 1));
			}
		}
	}
	static boolean isOut(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}
