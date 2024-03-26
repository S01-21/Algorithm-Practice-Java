import java.util.*;
import java.io.*;
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K;
	static int[][] inp;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 물품의 개수
		K = Integer.parseInt(st.nextToken());	// 최대 허용 무게
		inp = new int[2][N+1];	//[0]: 무게, [1]: 가치
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			inp[0][i] = Integer.parseInt(st.nextToken());
			inp[1][i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N+1][K+1];	// 1~i까지 물품의 무게 j 에서 최대 가치
		// 0번행, 0번열은 0으로 초기화 된 상태
		
		for (int i= 1; i<= N; i++) { // 물품 고정
			for (int j = 1; j <= K; j++) {
				int w = inp[0][i];	// 물품의 무게
				int v = inp[1][i];	// 물품의 가치
				
				if (w > j) {
					// 현재 무게가 제한 무게보다 크면, 선택할 수 없으니까 그전 물품까지에서의 최적해
					dp[i][j] = dp[i-1][j];
				} else {
					// 현재 물품을 포함시켰을 경우와 시키지 않을 경우 중 최대 가치를 저장 
					dp[i][j] = Math.max(v + dp[i-1][j-w], dp[i-1][j]);
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}

}