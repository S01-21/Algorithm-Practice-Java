import java.util.*;
import java.io.*;

public class Main {
	static int N, res;
	static int[][] cost, dp;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		cost = new int[N+1][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		res = Integer.MAX_VALUE;
		for (int i = 0 ; i <3; i++) {
			calc(i);
		}
		
		System.out.println(res);
		br.close();
	}
	private static void calc(int num) {
		dp = new int[N+1][3];
		for (int j = 0; j < 3; j++) {
			if (num == j)	{
				dp[1][j] = cost[1][j];
			} else {
				dp[1][j] = 1_000_001;
			}
		}
		
		for (int i = 2; i <= N; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
		}
		
		
		for (int j = 0 ; j < 3; j++) {
			if (j != num) {
				res = Math.min(res, dp[N][j]);
			}
		}
	}
}
