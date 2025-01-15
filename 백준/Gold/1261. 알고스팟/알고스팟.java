import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, min;
	static int[][] map, minDist;
	static boolean[][] vis;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Pair implements Comparable<Pair>{
		int x, y, dist;
		public Pair(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		public int compareTo(Pair o) {
			return this.dist - o.dist;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		vis = new boolean[N][M];
		minDist = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0 ; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
				minDist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		dijkstra(0, 0);
		
		System.out.println(minDist[N-1][M-1]);
		br.close();
	}
	private static void dijkstra(int x, int y) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		minDist[x][y] = 0;
		pq.offer(new Pair(x, y, minDist[x][y]));
		
		while (!pq.isEmpty()) {
			Pair cur = pq.poll();
			
			if (vis[cur.x][cur.y])	continue;
			
			vis[cur.x][cur.y] = true; 
			if (cur.x == N-1 && cur.y == M-1)	break;
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M)	continue;
				if (vis[nx][ny])	continue;
				if (map[nx][ny] == 1) {
					if (minDist[nx][ny] > minDist[cur.x][cur.y]+ 1) {
						minDist[nx][ny] = minDist[cur.x][cur.y] + 1;
						pq.offer(new Pair(nx, ny, minDist[nx][ny]));
					}
				} else {
					if (minDist[nx][ny] >= minDist[cur.x][cur.y]) {
						minDist[nx][ny] = minDist[cur.x][cur.y];
						pq.offer(new Pair(nx, ny, minDist[nx][ny]));
					}
				}
			}
		}
	}
}
