import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String A = br.readLine();
		String B = br.readLine();
		
		dp = new int[A.length()+1][B.length()+1];

		for (int r = 1; r <= A.length(); r++) {
			for (int c = 1; c <= B.length(); c++) {
				if (A.charAt(r-1) == B.charAt(c-1)) {
					dp[r][c] = dp[r-1][c-1] + 1;
				} else {
					dp[r][c] = Math.max(dp[r-1][c], dp[r][c-1]);
				}
			}
		}
		
		System.out.println(dp[A.length()][B.length()]);
		br.close();
	}
}
