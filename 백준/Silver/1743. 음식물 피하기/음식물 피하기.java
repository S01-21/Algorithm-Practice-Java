import java.util.*;
import java.io.*;

// 실버 1. 음식물 피하기 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, K, res, tmp;
	static int[][] map;
	static boolean[][] vis;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 세로 길이 
		M = Integer.parseInt(st.nextToken());	// 가로 길이 
		K = Integer.parseInt(st.nextToken());	// 음쓰 개수 
		map = new int[N+1][M+1];
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 1;
		}
		
		res = -1;
		vis = new boolean[N+1][M+1];
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=M; j++) {
				if (vis[i][j])	continue;
				if (map[i][j] == 1) {
					vis[i][j] = true;
					tmp = 1;
					dfs(i, j);
				}
			}
		}
		System.out.println(res);
		br.close();
	}
	private static void dfs(int x, int y) {
		for (int dir=0; dir<4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (nx <= 0 || nx > N || ny <= 0 || ny > M)	continue;
			if (vis[nx][ny])	continue;
			if (map[nx][ny] == 1) {
				vis[nx][ny] = true;
				tmp++;
				dfs(nx, ny);
			}
		}
		res = Math.max(res, tmp);
	}
}