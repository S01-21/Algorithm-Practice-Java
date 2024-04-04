import java.util.*;
import java.io.*;

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N, B;
	static int[] heights;
	static int res;
	static boolean[] isSelected;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 직원 수
			B = Integer.parseInt(st.nextToken());	// 선반 높이
			heights = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}
			res = Integer.MAX_VALUE;
			
			isSelected = new boolean[N];
			subset(0,0);
			
			sb.append("#").append(tc).append(" ").append(res - B).append("\n");
		}
		

		System.out.println(sb);
	}
	private static void subset(int cnt, int sum) {
		if (sum >= res) {
			return;
		}
		if (cnt == N) {
			if (sum >= B) {
				res = Math.min(res, sum);
			}
			return;
		}
		isSelected[cnt] = true;
		subset(cnt + 1, sum + heights[cnt]);
		isSelected[cnt] = false;
		subset(cnt + 1, sum);
	}
}
