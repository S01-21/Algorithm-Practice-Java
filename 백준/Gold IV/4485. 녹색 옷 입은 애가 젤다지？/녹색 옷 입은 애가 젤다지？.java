import java.util.*;
import java.io.*;

// 녹색 옷 입은 애가 젤다지?
public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] cave;
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // 우-하-좌-상
	static int[][] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			sb.append("Problem ").append(tc++).append(": ");
			cave = new int[N][N];
			dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = Integer.MAX_VALUE; // 최단거리 테이블 최대로 초기화
				}
			}

			dijkstra();

			sb.append(dist[N-1][N-1]).append("\n");
		}

		System.out.println(sb);
	}

	private static void dijkstra() {
		dist[0][0] = cave[0][0];
		PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) ->  o1.dist - o2.dist);
		pq.offer(new Pair(0, 0, dist[0][0]));
		
		while (!pq.isEmpty()) {
			Pair cur = pq.poll();
			if (dist[cur.x][cur.y] != cur.dist)	continue;
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + deltas[dir][0];
				int ny = cur.y + deltas[dir][1];
				if (isOut(nx, ny))
					continue;
				if (dist[cur.x][cur.y] + cave[nx][ny] < dist[nx][ny]) {
					dist[nx][ny] = dist[cur.x][cur.y] + cave[nx][ny];
					pq.offer(new Pair(nx, ny, dist[nx][ny]));
				}
			}
		}
	}

	static boolean isOut(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}

	static class Pair {
		int x, y;
		int dist;
		public Pair(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}