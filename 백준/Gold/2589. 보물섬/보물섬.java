import java.util.*;
import java.io.*;

// 골드 5. 보물섬 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int H, W, res;
	static char[][] map;
	static int[][] dist;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Pair{
		int x, y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		for (int i=0; i<H; i++) {
			String str = br.readLine();
			for (int j=0; j<W; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		res = -1;
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				if (map[i][j] == 'L') {
					dist = new int[H][W];
					bfs(i, j);
					findMax();	// dist[][] 에서 최댓값 찾기 
				}
			}
		}
		System.out.println(res);
		br.close();
	}
	private static void findMax() {
		int tmp = -1;
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				if (tmp < dist[i][j]) {
					tmp = dist[i][j];
				}
			}
		}
		tmp--;	// dist 방문체크를 1부터 했으니까 1 빼주기
		if (res < tmp) {
			res = tmp;
		}
	}
	private static void bfs(int x, int y) {
		Queue<Pair>	queue = new ArrayDeque<>();
		dist[x][y] = 1;
		queue.offer(new Pair(x, y));
		while(!queue.isEmpty()) {
			Pair cur = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (nx < 0 || nx >= H || ny < 0 || ny >= W)	continue;
				if (dist[nx][ny] > 0)	continue;
				if (map[nx][ny] == 'W')	continue;
				dist[nx][ny] = dist[cur.x][cur.y] + 1;
				queue.offer(new Pair(nx, ny));
			}
		}
	}
}