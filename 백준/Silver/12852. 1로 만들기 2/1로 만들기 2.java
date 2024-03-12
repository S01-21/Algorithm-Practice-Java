import java.util.*;
import java.io.*;

// 1로 만들기 2
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int min;
	static int[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;
		dp = new int[2][1000001];	// [1로 만들기 위한 연산 횟수][연산 수행 후 숫자]
		
		dp[0][1] = 0;
		dp[1][1] = 1;	
		
		dp[0][2] = 1;
		dp[1][2] = 1;
		
		dp[0][3] = 1;
		dp[1][3] = 1;
		
		for (int i=4; i<=N; i++) {			
			dp[0][i] = dp[0][i-1] + 1;
			dp[1][i] = i-1;
			
			if (i%3 == 0) {
				if (dp[0][i/3] + 1 < dp[0][i]) {
					dp[0][i] = dp[0][i/3] + 1;
					dp[1][i] = i/3;
				}
			}
			if (i%2 == 0) {
				if (dp[0][i/2] + 1 < dp[0][i]) {
					dp[0][i] = dp[0][i/2] + 1;
					dp[1][i] = i/2;
				}
			}
		}
		
		int num = N;
		for (int i = 0; i<dp[0][N]; i++) {
			sb.append(num).append(" ");
			num = dp[1][num];
		}
		
		sb.append(1);
		System.out.println(dp[0][N]);
		System.out.println(sb);
	}
}