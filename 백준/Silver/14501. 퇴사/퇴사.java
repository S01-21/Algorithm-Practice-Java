import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, max;
	static int[] dp;
	static int[][] info;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		dp = new int[N+2];
		info = new int[N+1][2];
		for (int i = 1; i<= N; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());	// 기간 
			info[i][1] = Integer.parseInt(st.nextToken());	// 수익
		}
		
		if (info[N][0] > 1)	dp[N] = 0;
		else	dp[N] = info[N][1];
		
		for (int i = N-1; i >= 1; i--) {
			int time = info[i][0];
			int profit = info[i][1];
			if (i + time > N+1) {
				dp[i] = dp[i+1];
			} else {
				dp[i] = Math.max(dp[i+time] + profit, dp[i+1]);
			}
		}
		
		System.out.println(dp[1]);
		br.close();
	}
}
