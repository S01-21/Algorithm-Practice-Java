import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K, res;
	static int[][] info, dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		info = new int[N+1][2];
		dp = new int[N+1][K+1];
		for (int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i<=N; i++) {	// 물품 고정 
			for (int j = 1; j <= K; j++) {
				int w = info[i][0];
				int v = info[i][1];
				
				
				int exclude = dp[i-1][j];
				if (w > j) {	// 가방에 담을 수 없음
					dp[i][j] = exclude;
				} else {
					int include = dp[i-1][j-w] + v;
					dp[i][j] = Math.max(include, exclude);
				}
			}
		}
		
		System.out.println(dp[N][K]);
		br.close();
	}
}