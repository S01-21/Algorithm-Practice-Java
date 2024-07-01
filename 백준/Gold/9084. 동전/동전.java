import java.util.*;
import java.io.*;

// 골드 5. 동전 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] coins, dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());	// 동전의 가지 수
			coins = new int[N];		// 동전의 각 금액 
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			M = Integer.parseInt(br.readLine());	// 만들어야 할 금액 
			dp = new int[M+1];		// i원을 만들 수 있는 경우의 수 
			
			for (int i=0; i<N; i++) {	// 동전금액 고정 
				for (int j=1; j<=M; j++) {
					if (j == coins[i]) {	// 해당 금액과 같을 때 경우의수 +1
						dp[j]++;
					} else if (j > coins[i]) {
						dp[j] += dp[j-coins[i]];
					}
				}
				
			}
			sb.append(dp[M]).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}