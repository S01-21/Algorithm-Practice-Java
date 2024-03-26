import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] activeM;
	static int[] activeC;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	
		M = Integer.parseInt(st.nextToken());	
		activeM = new int[N+1];
		activeC = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			activeM[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			activeC[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N+1][10001];	
		
		for (int i=1; i<=N; i++) {	
			for (int j=0; j < 10001; j++) {
				int c = activeC[i];
				int m = activeM[i];
				
				if (j < c) {
					dp[i][j] = dp[i-1][j];
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j-c] +m , dp[i-1][j]);
				}
			}
		}
		
		int res = Integer.MAX_VALUE;
		for (int j=0; j<10001; j++) {
			if (dp[N][j] >= M) {
				res = Math.min(res, j);
			}
		}
		
		System.out.println(res);
	}
}