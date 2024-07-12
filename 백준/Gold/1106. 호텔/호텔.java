import java.util.*;
import java.io.*;

// 골드 4. 호텔 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int C, N;
	static int[][] city;
	static int[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());	// 목표 최소 고객 
		N = Integer.parseInt(st.nextToken());	// 도시 개수 
		city = new int[N+1][2];
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			city[i][0] = Integer.parseInt(st.nextToken());	// 홍보할 때 드는 비용 
			city[i][1] = Integer.parseInt(st.nextToken());	// 비용으로 얻을 수 있는 고객 수 
		}
		dp = new int[C+101];	// j명 고객을 모으는 최소 비용 
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i=1; i<=N; i++) {
			for (int j=city[i][1]; j<C+101; j++) {
				if (dp[j-city[i][1]] == Integer.MAX_VALUE) continue;
				dp[j] = Math.min(dp[j], dp[j-city[i][1]] + city[i][0]);
			}
		}
		int res = Integer.MAX_VALUE;
		for (int i=C; i<C+101; i++) {
			res = Math.min(dp[i], res);
		}
		System.out.println(res);
		br.close();
	}
}