import java.util.*;
import java.io.*;
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static long[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			dp = new long[101];
			
			dp[1] = 1L;
			dp[2] = 1L;
			dp[3] = 1L;
			dp[4] = 2L;
			dp[5] = 2L;
			
			for (int i=6; i<=N; i++) {
				dp[i] = dp[i-5] + dp[i-1];
			}
			
			
			sb.append(dp[N]).append("\n");
		}
		
		

		System.out.println(sb);
	}
}