import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int res;
	static int[][] map;
	static boolean[][] vis;
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
		int tc = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)	break;
			map = new int[N][N];
			minDist = new int[N][N];
			StringTokenizer st = null;
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					minDist[i][j] = Integer.MAX_VALUE;
				}
			}
			
			dijkstra();
			
			res = minDist[N-1][N-1];
			sb.append("Problem ").append(tc).append(": ").append(res).append("\n");
			tc++;
		}
		System.out.println(sb);
		br.close();
	}
	private static void dijkstra() {
		minDist[0][0] = map[0][0];
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(0,0,map[0][0]));
		
		while (!pq.isEmpty()) {
			Pair stopover = pq.poll();
			
			if (minDist[stopover.x][stopover.y]!= stopover.weight)	continue;
			
			if (stopover.x == N-1 && stopover.y == N-1) {
				break;
			}
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = stopover.x + dx[dir];
				int ny = stopover.y + dy[dir];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)	continue;
				if (minDist[nx][ny] > minDist[stopover.x][stopover.y] + map[nx][ny]) {
					minDist[nx][ny] = minDist[stopover.x][stopover.y] + map[nx][ny];
					pq.offer(new Pair(nx, ny, minDist[nx][ny]));
				}
			}
		}
	}
}