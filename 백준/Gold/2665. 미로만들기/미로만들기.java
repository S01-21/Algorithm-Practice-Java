import java.util.*;
import java.io.*;

// 골드4. 미로만들기 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int res;
	static int[][] map;
	static int[][] minDist;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Pair implements Comparable<Pair>{
		int x, y, weight;
		public Pair(int x, int y, int weight) {
			super();
			this.x = x;
			this.y = y;
			this.weight = weight;
		}
		@Override
		public int compareTo(Pair o) {
			return this.weight - o.weight;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		minDist = new int[N][N];
		String str = null;
		for (int i=0; i<N; i++) {
			str = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = str.charAt(j) - '0';
				if (map[i][j] == 0) {
					map[i][j] = 1;
				} else {
					map[i][j] = 0;
				}
				minDist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		dijkstra();
		
		System.out.println(minDist[N-1][N-1]);
	}
	private static void dijkstra() {
		minDist[0][0] = map[0][0];
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(0,0,minDist[0][0]));
		
		while (!pq.isEmpty()) {
			Pair cur = pq.poll();
			
			if (minDist[cur.x][cur.y] != cur.weight)	continue;
			
			if (cur.x == N-1 && cur.y == N-1)	break;
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)	continue;
				if (minDist[nx][ny] > minDist[cur.x][cur.y]+ map[nx][ny]) {
					minDist[nx][ny] = minDist[cur.x][cur.y]+ map[nx][ny];
					pq.offer(new Pair(nx, ny, minDist[nx][ny]));
				}
			}
		}
		
	}
}