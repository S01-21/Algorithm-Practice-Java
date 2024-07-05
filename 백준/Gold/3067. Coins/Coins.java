import java.util.*;
import java.io.*;

// 골드 5. Coins 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] coins, dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());	// 동전 가지 수 
			coins = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());	// 동전 금액 
			}
			M = Integer.parseInt(br.readLine());	// 만들어야 할 금액 
			
			dp = new int[M+1];	// index원을 만드는 방법의 수 
			for (int i=0; i<N; i++) {
				for (int j=1; j<=M; j++) {
					if (j == coins[i]) {
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