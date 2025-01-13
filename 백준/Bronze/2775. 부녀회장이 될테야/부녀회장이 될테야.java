import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K;
	static int[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(br.readLine());
		
		dp = new int[15][15];
		for (int j = 1; j <= 14; j++) {
			dp[0][j] = j;
		}
		for (int i = 1; i <= 14; i++) {
			for (int j = 1; j <= 14; j++) {
				dp[i][j] = dp[i][j-1] + dp[i-1][j];
			}
		}
		
		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine());	// 층
			N = Integer.parseInt(br.readLine());	// 호
			
			sb.append(dp[K][N]).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
}
