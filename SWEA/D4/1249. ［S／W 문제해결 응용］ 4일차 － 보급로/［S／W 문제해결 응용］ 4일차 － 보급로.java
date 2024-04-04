import java.util.*;
import java.io.*;

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int res;
	static int[][] map;
	static boolean[][] vis;
	static int[][] minDist;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Node implements Comparable<Node>{
		int x, y, dist;
		public Node(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T ;tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			minDist = new int[N][N];
			vis = new boolean[N][N];

			for (int i=0; i<N; i++) {
				String str = br.readLine();
				for (int j=0 ;j<N; j++) {
					map[i][j] = str.charAt(j)-'0';
					minDist[i][j] = Integer.MAX_VALUE;
				}
			}
			
			//dijkstra();
			dijkstraPQ();
			
			sb.append("#").append(tc).append(" ").append(res).append("\n");
		}
		
		System.out.println(sb);
	}
	private static void dijkstraPQ() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		minDist[0][0] = map[0][0];
		pq.offer(new Node(0,0,minDist[0][0]));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (minDist[cur.x][cur.y] != cur.dist)	continue;
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)	continue;
				if (minDist[nx][ny] > minDist[cur.x][cur.y] + map[nx][ny] ) {
					minDist[nx][ny] = minDist[cur.x][cur.y] + map[nx][ny];
					pq.offer(new Node(nx, ny, minDist[nx][ny]));
				}
			}
		}
		res = minDist[N-1][N-1];
	}
	private static void dijkstra() {
		minDist[0][0] = 0;
		vis[0][0] = true;
		
		for (int dir = 0; dir < 4; dir++) {
			int nx = dx[dir];
			int ny = dy[dir];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)	continue;
			if (vis[nx][ny])	continue;
			minDist[nx][ny] = map[nx][ny];
		}
		
		for (int i=0; i < N*N; i++) {
			int min = Integer.MAX_VALUE;
			int minX = -1;
			int minY = -1;
			
			for (int j=0; j<N; j++) {
				for (int k=0; k<N; k++) {
					if (vis[j][k])	continue;
					if (minDist[j][k] < min) {
						min = minDist[j][k];
						minX = j;
						minY = k;
					}
				}
			}
			
			vis[minX][minY] = true;
			
			if (minX == N-1 && minY == N-1)	break;
			
			for (int dir = 0 ; dir < 4; dir++) {
				int nx = minX + dx[dir];
				int ny = minY + dy[dir];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)	continue;
				if (vis[nx][ny])	continue;
				if (minDist[nx][ny] > minDist[minX][minY] + map[nx][ny]) {
					minDist[nx][ny] = minDist[minX][minY] + map[nx][ny];
				}
			}
		}
		res = minDist[N-1][N-1];
	}
	
}