import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] dr = {1, 0};
	static int[] dc = {0, 1};
	static int[][] dp;
	static String a, b;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		a = br.readLine();
		b = br.readLine();
		
		dp = new int[a.length()+1][b.length()+1];
		// dp[i]: 현재 원소를 마지막으로 하는 LCS의 길이
		// i, j 문자 같으면 +1
		// i, j 문자 다르면 -1 원소들 중에 최대길이
		
		int fill = 0;
		for (int r = 1; r <= a.length(); r++) {
			if (a.charAt(r-1) == b.charAt(0)) {
				fill = 1;
			}
			dp[r][1] = fill;
		}
		fill = 0;
		for (int c = 1; c <= b.length(); c++) {
			if (b.charAt(c-1) == a.charAt(0)) {
				fill = 1;
			}
			dp[1][c] = fill;
		}
		
		for (int r = 2; r <= a.length(); r++) {
			for (int c = 2; c <= b.length(); c++) {
				if (a.charAt(r-1) == b.charAt(c-1)) {
					dp[r][c] = dp[r-1][c-1] + 1;
				} else {
					dp[r][c] = Math.max(dp[r-1][c], dp[r][c-1]);
				}
			}
		}
		
		sb.append(dp[a.length()][b.length()]).append("\n");
		
		if (dp[a.length()][b.length()] != 0) {
			String res = "";
			int r = a.length();
			int c = b.length();
			while (r > 0 && c > 0) {
				if (a.charAt(r-1) == b.charAt(c-1)) {
					res += a.charAt(r-1);
					r--;
					c--;
				} else if (dp[r-1][c] > dp[r][c-1]) {
					r--;
				} else{
					c--;
				}
			}
			for (int i = res.length()-1; i >= 0; i--) {
				sb.append(res.charAt(i));
			}
		}
		System.out.println(sb);
		br.close();
	}
	
	
}
