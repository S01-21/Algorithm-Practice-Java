import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int M, N;
	static int res;
	static int[] dx = {-1,1,0,0};	// 상하좌우
	static int[] dy = {0,0,-1,1};
	static int[][] map;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());	// 세로의 크기 
		N = Integer.parseInt(st.nextToken());	// 가로의 크기
		map = new int[M][N];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[M][N];	// 해당 위치에서 도착지까지 가는 경우의 수 
		for (int i=0; i<M; i++) {
			Arrays.fill(dp[i], -1);		// 경우의 수 -1로 초기화 
		}
		res = dfs(0, 0);
		
		System.out.println(res);
		br.close();
	}
	private static int dfs(int x, int y) {
		if (x == M-1 && y == N-1) {
			return 1;
		}
		
		if (dp[x][y] != -1) {	// 이미 경우의 수 구해져있으면 그 값 리턴 
			return dp[x][y];
		}
		dp[x][y] = 0;
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (nx <0 || nx >= M || ny < 0 || ny >= N)	continue;
			if (map[nx][ny] >= map[x][y])	continue;
			dp[x][y] += dfs(nx, ny);	// 이동가능하면 이동한 곳의 경우의 수만큼 추가 
		}
		
		return dp[x][y];
	}
}