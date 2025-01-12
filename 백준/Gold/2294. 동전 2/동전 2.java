import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K;
	static final int MAX = 10001;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[] coin = new int[N];
		int[] dp = new int[K+1];
		
		for (int i = 0; i < N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
	
		Arrays.fill(dp, MAX);
		dp[0] = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = coin[i]; j<= K; j++) {
				if (dp[j-coin[i]] == MAX)	continue;
				dp[j] = Math.min(dp[j-coin[i]] +1, dp[j]);
			}
		}
		
		if (dp[K] == MAX) {
			dp[K] = -1;
		}
		System.out.println(dp[K]);
		br.close();
	}
}
