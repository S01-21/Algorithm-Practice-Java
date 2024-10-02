import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, res;
	static int[][] map;
	static int[][] minDist;
	static boolean[][] vis;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static class Pair implements Comparable<Pair>{
		int x, y, weight;
		public Pair(int x, int y, int weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}
		public int compareTo(Pair o) {
			return this.weight - o.weight;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
//		M = Integer.parseInt(st.nextToken());
		int tc = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			sb.append("Problem ").append(tc).append(": ");
			
			map = new int[N][N];
			vis = new boolean[N][N];
			minDist = new int[N][N];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					minDist[i][j] = Integer.MAX_VALUE;
				}
			}
			
			res = dijkstra();
			sb.append(res).append("\n");
			tc++;
		}
		
		System.out.println(sb);
		br.close();
	}
	private static int dijkstra() {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		minDist[0][0] = map[0][0];
		pq.offer(new Pair(0, 0, minDist[0][0]));
		
		while (!pq.isEmpty()) {
			Pair cur = pq.poll();
			if (vis[cur.x][cur.y])	continue;
			
			vis[cur.x][cur.y] = true;
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (vis[nx][ny])	continue;
				if (minDist[nx][ny] > minDist[cur.x][cur.y]+ map[nx][ny]) {
					minDist[nx][ny] = minDist[cur.x][cur.y]+ map[nx][ny];
					pq.offer(new Pair(nx, ny, minDist[nx][ny]));
				}
			}
			
		}
		
		return minDist[N-1][N-1];
	}
	
	
}