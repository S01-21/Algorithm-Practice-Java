import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][][] dp;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new  InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		dp = new int[N][N][3];
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int j = 1; j < N; j++) {
			if (map[0][j] == 1)	break;
			dp[0][j][0] = 1;
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if (map[i][j] == 1)	continue;
				
				if (map[i][j-1] != 1) {
					dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];					
				}
				
				if (map[i-1][j] != 1) {
					dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
				}
				if (map[i][j-1] != 1 && map[i-1][j] != 1 && map[i-1][j-1] != 1) {
					dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
			}
		}
		
		int res = 0;
		for (int i = 0; i < 3; i++) {
			res += dp[N-1][N-1][i];
		}
		System.out.println(res);
		br.close();
	}
}
