import java.util.*;
import java.io.*;

public class Main {
	static int N, res;
	static final int MOD = 1_000_000_000;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[N+1][10];
		for (int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= 9; j++) {
				if (j == 0) {
					dp[i][j] = dp[i-1][1] % MOD;
				} else if (j == 9) {
					dp[i][j] = dp[i-1][8] % MOD;
				} else {
					dp[i][j] = (dp[i-1][j-1]%MOD + dp[i-1][j+1]%MOD)%MOD;
				}
			}
		}

		int res = 0;
		for (int i = 0; i <= 9; i++) {
			res = (res%MOD + dp[N][i]%MOD)%MOD;
		}
		System.out.println(res);
		br.close();
	}
}
