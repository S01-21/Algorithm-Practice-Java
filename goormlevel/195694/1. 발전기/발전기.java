import java.io.*;
import java.util.*;

class Main {
	static StringBuilder sb = new StringBuilder();
		static int N, res;
		static int[][] map;
		static boolean[][] vis;
		static int[] dx = {-1,1,0,0};
		static int[] dy = {0,0,-1,1};
		static class Pair {
			int x, y;
			public Pair(int x, int y){
				this.x = x;
				this.y = y;
			}
		}
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		vis = new boolean[N][N];
		StringTokenizer st = null;
		for (int i=0; i<N; i++){
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=0; i<N; i++){
			for (int j=0; j<N; j++){
				if (!vis[i][j] && map[i][j] == 1){
					bfs(i, j);
					res++;
				}
			}
		}
		
		System.out.println(res);
	}
	static void bfs(int x, int y){
		Queue<Pair> queue = new ArrayDeque<>();
		queue.offer(new Pair(x, y));
		vis[x][y] = true;
		
		while (!queue.isEmpty()){
			Pair cur=  queue.poll();
			
			for (int dir = 0; dir < 4; dir++){
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)	continue;
				if (vis[nx][ny])	continue;
				if (map[nx][ny] == 0)	continue;
				vis[nx][ny] = true;
				queue.offer(new Pair(nx, ny));
			}
		}
	}
}