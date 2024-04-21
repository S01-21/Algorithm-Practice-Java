import java.util.*;
import java.io.*;

// 골드 3. 욕심쟁이 판다 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] map;
	static int[][] dp;
	static int res;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st = null;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		res = -1;
		dp = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (dp[i][j] == 0) {
					dp[i][j] = dfs(i, j);
					res = Math.max(res, dp[i][j]);
				}
			}
		}
		
		System.out.println(res);
		br.close();
	}
	private static int dfs(int x, int y) {
		if (dp[x][y] != 0) {
			return dp[x][y];
		}
		
		dp[x][y] = 1;
		int max = 0;
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (isOut(nx, ny)) continue;
			if (map[nx][ny] <= map[x][y])	continue;
			dp[nx][ny] = dfs(nx, ny);
			max = Math.max(max, dp[nx][ny]);
		}
		
		return max + dp[x][y];
	}
	private static boolean isOut(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}