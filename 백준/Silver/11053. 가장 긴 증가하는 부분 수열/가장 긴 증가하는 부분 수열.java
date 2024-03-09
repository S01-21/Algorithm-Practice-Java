import java.util.*;
import java.io.*;

// 가장 긴 증가하는 부분 수열
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] arr;
	static int res;
	static int[] dp;	// 해당 인덱스를 마지막으로 하는 가장 긴 증가하는 부분수열의 길이 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N];
		for (int i = 0; i<N; i++) {
			dp[i] = 1;
			for (int j = 0; j<i; j++) {	// 이전 값들 돌면서 현재보다 작은 수 찾기 
				if (arr[j] < arr[i]) {
					if (dp[i] < dp[j] + 1) {	// 기존에 저장된 값보다 커질 때 갱신 
						dp[i] = dp[j] + 1;
					}
				}
			}
		}
		
		res = 0;
		for (int i=0; i<N; i++) {
			if (res < dp[i]) {
				res = dp[i];
			}
		}
		
		System.out.println(res);
		
	}
}