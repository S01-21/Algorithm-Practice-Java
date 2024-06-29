import java.util.*;
import java.io.*;

// 골드 5. 수강 과목 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K;
	static int[][] info;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 최대 공부시간 
		K = Integer.parseInt(st.nextToken());	// 과목 수 
		
		info = new int[K+1][2];	// [0]: 중요도, [1]: 필요한 공부시간 
		for (int i=1; i<=K; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());	// 중요도 
			info[i][1] = Integer.parseInt(st.nextToken());	// 필요한 공부시간 
		}
		
		dp = new int[K+1][N+1];
		for (int i=1; i<=K; i++) {	// 모든 과목 
			for (int j=1; j<=N; j++) {
				int I = info[i][0];	// 중요도 
				int T = info[i][1];	// 필요 공부시간 
				
				
				if (T > j) {
					dp[i][j] = dp[i-1][j];
				}
				else {
					int include = I + dp[i-1][j-T];
					int exclude = dp[i-1][j];
					dp[i][j] = Math.max(include, exclude);
				}
			}
		}
		System.out.println(dp[K][N]);
		br.close();
	}
}