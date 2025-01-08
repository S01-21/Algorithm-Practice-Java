import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		dp = new int[12];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= 11; i++) {
			for (int j = 1; j <= 3; j++) {
				if (j > i)	continue;
				dp[i] += dp[i-j];
			}
		}
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			sb.append(dp[N]).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
}
