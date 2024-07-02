import java.util.*;
import java.io.*;

// 실버 2. 안녕 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] info, dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		info = new int[N+1][2];		// [0]: 잃는 체력, [1]: 얻는 기쁨 
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			info[i][0] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			info[i][1] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N+1][100];
		for (int i=1; i<=N; i++) {	// 남은 체력 
			for (int j=0; j<100; j++) {
				// 인사 가능 
				if (j >= info[i][0]) {
					int include = dp[i-1][j-info[i][0]] + info[i][1];
					int exclude = dp[i-1][j];
					dp[i][j] = Math.max(include, exclude);
				}
				// 인사 불가능 
				else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		int max = -1;
		for (int i=0; i<100; i++) {
			max = Math.max(max, dp[N][i]);
		}
		System.out.println(max);
		br.close();
	}
}