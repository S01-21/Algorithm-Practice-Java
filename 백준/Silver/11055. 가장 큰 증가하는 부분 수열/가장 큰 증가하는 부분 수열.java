import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, max;
	static int[] dp, arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		dp = new int[N+1];
		arr = new int[N+1];
		for (int i =1 ; i<= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = 0;
		for (int i = 1; i <= N; i++) {
			int cur = arr[i];
			int tmp = 0;
			for (int j = i-1; j >= 1; j--) {
				if (arr[j] >= cur)	continue;
				tmp = Math.max(tmp, dp[j]);
			}
			dp[i] = tmp + cur;
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
		br.close();
	}
}
