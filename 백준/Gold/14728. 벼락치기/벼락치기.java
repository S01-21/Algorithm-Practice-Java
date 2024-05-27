import java.util.*;
import java.io.*;

// 골드 5. 벼락치기 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, T;
	static int[][] dp;
	static int[][] info;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		dp = new int[N+1][T+1];
		info = new int[N+1][2];	// [0]: 공부시간, [1]: 배점 
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());	// 공부시간 
			int S = Integer.parseInt(st.nextToken());	// 배점 
			info[i][0] = K;
			info[i][1] = S;
		}
		
		for (int i=1; i<=N; i++	) {	// 단원 고정
			for (int j=1; j<=T;j++) {
				if (info[i][0] > j) {	// 시간 넘으면 
					dp[i][j] = dp[i-1][j];	// 해당 단원 제외 
				} else {
					int include = info[i][1] + dp[i-1][j - info[i][0]];
					int exclude = dp[i-1][j];
					dp[i][j] = Math.max(include, exclude);
				}
			}
			
		}
		
		System.out.println(dp[N][T]);
		br.close();
	}
}