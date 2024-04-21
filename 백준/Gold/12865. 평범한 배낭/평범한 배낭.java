import java.util.*;
import java.io.*;

// 골드 5. 평범한 배낭 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K;
	static int[][] info;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 물품 수 
		K = Integer.parseInt(st.nextToken());	// 최대 무게 
		info = new int[N+1][2];		// [0]: 무게, [1]: 가치
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());	// W: 무게 
			info[i][1] = Integer.parseInt(st.nextToken());	// V: 가치 
		}
		
		dp = new int[N+1][K+1];	// 1~i까지 물품의 무게 j에서의 최대 가치 
		
		for (int i=1; i<=N; i++) {	// 모든 물건에 대해 
			for (int j=1; j<=K; j++) {
				int W = info[i][0];		// 무게 
				int V = info[i][1];		// 가치 
				
				if (W > j) {	// 현재 최대무게보다 무거우면 포함X -> 그전까지에서의 최적해 저장 
					dp[i][j] = dp[i-1][j];
				} else {
					int include = V + dp[i-1][j-W];	// 현재 물품 포함 시켰을 때의 가치 
					int exclude = dp[i-1][j];		// 현재 물품 포함 안했을 때의 가치 
					dp[i][j] = Math.max(include, exclude);
					
				}
			}
		}
		
		System.out.println(dp[N][K]);
		br.close();
		
	}
}