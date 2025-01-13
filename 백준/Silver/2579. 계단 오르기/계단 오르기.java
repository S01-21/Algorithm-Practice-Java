import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] dp;
	static int[] stairs;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		stairs = new int[N+1];
		dp = new int[N+1][3];	// OO, OX, XO 로 시작
		
		for (int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		
		dp[1][0] = stairs[1];
		dp[1][1] = stairs[1];
		if (N > 1) {
			dp[2][0] = dp[1][0] + stairs[2];	// OO
			dp[2][1] = dp[1][1];	// OX
			dp[2][2] = stairs[2];	// XO
		}
		
		for (int i = 3; i <= N; i++) {
			dp[i][0] = dp[i-1][2] + stairs[i];	// XOO
			dp[i][1] = Math.max(dp[i-1][0], dp[i-1][2]);	// OOX, XOX
			dp[i][2] = dp[i-1][1] + stairs[i];	// OXO
		}
		if (N > 1) {
			System.out.println(Math.max(dp[N][0], dp[N][2]));
		} else {
			System.out.println(dp[N][0]);
		}
		br.close();
	}
}
