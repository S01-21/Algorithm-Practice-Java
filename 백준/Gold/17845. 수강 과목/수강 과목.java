import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static Subject[] list;
	static int[][] dp;
	static class Subject{
		int imp, time;
		public Subject(int imp, int time) {
			this.imp = imp;
			this.time = time;
		}
	}
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new  InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 최대 공부시간
		K = Integer.parseInt(st.nextToken());	// 과목 수
		list = new Subject[K+1];
		dp = new int[K+1][N+1];
		
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int imp = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			list[i] = new Subject(imp, time);
		}
		
		for (int i = 1; i <= K; i++) {
			for (int j = 0; j <= N; j++) {
				int imp = list[i].imp;
				int time = list[i].time;
				
				if (time <= j) {
					dp[i][j] = Math.max(dp[i-1][j-time] + imp, dp[i-1][j]);
				} else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[K][N]);
		br.close();
	}
}
