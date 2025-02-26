import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	static int N, res;
	static int[] dp, card;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		card = new int[N+1];
		dp = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		// i개 사는 최소 비용 구하기 위해, j개가 들어있는 카드팩 구매하는 경우 고려
		for (int i = 1; i <= N; i++) {	// 목표 개수
			for (int j = 1; j <= i ; j++) {	// 카드팩 선택
				if (dp[i-j] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], dp[i-j] + card[j]); 
				}
			}
		}
		
		System.out.println(dp[N]);
		br.close();
	}
}
