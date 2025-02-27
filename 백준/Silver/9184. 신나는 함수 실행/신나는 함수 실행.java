import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	static int N, A, B, C, res;
	static int[][][] dp;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dp = new int[21][21][21];
		
		ready();
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if (A == -1 && B == -1 && C == -1)	break;
			
			sb.append("w(").append(A).append(", ").append(B);
			sb.append(", ").append(C).append(") = ");
			sb.append(func(A, B, C)).append("\n");
			
		}
		System.out.println(sb);
		br.close();
	}
	private static void ready() {
		for (int i = 0; i <= 20; i++) {
			for (int j = 0 ; j <= 20; j++) {
				for (int k = 0 ; k <=20; k++) {
					if (i <= 0 || j <= 0 || k <= 0) {
						dp[i][j][k] = 1;
					} else if (i < j && j < k) {
						dp[i][j][k] = dp[i][j][k-1] + dp[i][j-1][k-1] - dp[i][j-1][k];
					} else {
						dp[i][j][k] = dp[i-1][j][k] + dp[i-1][j-1][k]
								+ dp[i-1][j][k-1] - dp[i-1][j-1][k-1];
					}
				}
			}
		}
	}
	private static int func(int a, int b, int c) {
		if (a <= 0 || b <= 0 || c <= 0)	return 1;
		if (a > 20 || b > 20 || c > 20)	return dp[20][20][20];
		return dp[a][b][c];
	}
}
