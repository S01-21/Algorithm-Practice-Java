import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static long res;
	static int[][] map;
	static long[][] dp;
	static int[] dx = {0, 1};
	static int[] dy = {1, 0};
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		dp = new long[N][N];	// (x, y) -> (N-1, N-1) 경로 수
		StringTokenizer st = null;
		for (int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		res = dfs(0, 0);
		
		System.out.println(res);
		br.close();
	}
	private static long dfs(int x, int y) {
		if (x == N-1 && y == N-1) return 1;
		if (dp[x][y] != -1)	return dp[x][y];
		
		dp[x][y] = 0;
		for (int dir = 0; dir < 2; dir++) {
			int nx = x;
			int ny = y;
			for (int t = 0; t < map[x][y]; t++) {
				nx += dx[dir];
				ny += dy[dir];
			}
			if (nx >= N || ny >= N)	continue;
			dp[x][y] += dfs(nx, ny);
		}
		
		return dp[x][y];
	}
}
