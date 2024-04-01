import java.util.*;
import java.io.*;

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int res;
	static int startX, startY, endX, endY;
	static boolean[][] vis;
	static class Pair{
		int x, y, dist;
		public Pair(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			StringTokenizer st = null;
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			
			res = -1;
			vis = new boolean[N][N];
			bfs();
			
			
			sb.append(res).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	private static void bfs() {
		Queue<Pair> queue = new ArrayDeque<>();
		vis[startX][startY] = true;
		queue.offer(new Pair(startX, startY, 0));
		
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			
			if (cur.x == endX && cur.y == endY) {	// 목적지 도착 
				res = cur.dist;
				return;
			}
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)	continue;	// 범위 체크 
				if (vis[nx][ny] || map[nx][ny]==1)	continue;	// 	방문했거나 섬이면 패스 
				if (map[nx][ny] == 0) {	// 바다면 이동 
					vis[nx][ny] = true;
					queue.offer(new Pair(nx, ny, cur.dist + 1));
				}
				if (map[nx][ny] == 2) {	// 소용돌이 
					if (cur.dist % 3 == 2) {	// 이동 가능 
						vis[nx][ny] = true;
						queue.offer(new Pair(nx, ny, cur.dist + 1));
					} else {	// 이동 못함. 그 자리에서 대기 
						queue.offer(new Pair(cur.x, cur.y, cur.dist + 1));
					}
				}
			}
		}
		
	}
}
