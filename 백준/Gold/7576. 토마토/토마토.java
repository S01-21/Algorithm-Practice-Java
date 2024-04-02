import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] dist;
	static class Pair{
		int x, y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static Queue<Pair> queue;
	static int res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		dist = new int[M][N];
		for (int i=0; i<M; i++) {
			Arrays.fill(dist[i], -1);
		}
		queue = new ArrayDeque<>();
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					queue.offer(new Pair(i,j));
					dist[i][j] = 0;
				}
			}
		}
		
		bfs();
		
		int max = -1;
		boolean flag = true;
		for (int i=0; i<M; i++) {
			for (int j=0; j<N; j++) {
				if (dist[i][j] == -1 && map[i][j] != -1) {
					res = -1;
					flag = false;
					break;
				}
				if (max < dist[i][j]) {
					max = dist[i][j];
				}
			}
		}
		
		System.out.println(flag ? max : res);
		br.close();
	}
	private static void bfs() {
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			
			for (int dir = 0 ; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (nx < 0 || nx >= M || ny < 0 || ny >= N)	continue;
				if (dist[nx][ny] >= 0 || map[nx][ny] == -1)	continue;
				dist[nx][ny] = dist[cur.x][cur.y] + 1;
				queue.offer(new Pair(nx, ny));
			}
		}
	}
}