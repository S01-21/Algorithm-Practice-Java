import java.util.*;
import java.io.*;
import java.io.*;
import java.math.*;

public class Main {
	static int N, res;
	static boolean[] dp;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		dp = new boolean[N+1];	// 돌이 i개 남았을 때 상근이 승패여부
		dp[1] = true;
		if (N > 2)
			dp[3] = true;
		for (int i = 4; i <= N; i++) {
			dp[i] = !dp[i-1] || !dp[i-3];
		}
		
		if (dp[N]) {
			System.out.println("SK");
		} else {
			System.out.println("CY");
		}
		
		
		br.close();
	}
}
