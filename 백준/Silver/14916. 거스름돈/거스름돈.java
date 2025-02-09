import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	static int N, res;
	static final int INF = Integer.MAX_VALUE;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];
		int[] coin = {2, 5};
		
		Arrays.fill(dp, INF);
		dp[0] = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = coin[i]; j <= N; j++) {
				if (dp[j-coin[i]] == INF)	continue;
				dp[j] = Math.min(dp[j], dp[j-coin[i]]+ 1);
			}
		}
		
		if (dp[N]==INF) {
			System.out.println(-1);
		} else {
			System.out.println(dp[N]);
		}
		br.close();
	}
}
