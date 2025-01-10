import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K;
	static int[] units, dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		units = new int[N];
		dp = new int[K+1];
		for (int i = 0 ; i < N; i++) {
			units[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = 1;
		
		for (int unit : units) {
            for (int i = unit; i <= K; i++) {
                dp[i] += dp[i - unit];
            }
        }

		System.out.println(dp[K]);
		br.close();
	}
}
