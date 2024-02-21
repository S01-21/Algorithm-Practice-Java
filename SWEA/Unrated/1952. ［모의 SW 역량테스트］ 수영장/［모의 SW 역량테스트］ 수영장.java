import java.util.*;
import java.io.*;

// 수영장
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int[] prices = new int[4]; // 1일, 1달, 3달, 1년 이용권 요금
	static int[] plan = new int[13]; // 12개월 이용 계획 (0인덱스 사용x)
	static int res; // 가장 적게 지출하는 비용 (결과)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}

			res = Integer.MAX_VALUE;
			dfs(0, 0, -1);

			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int n, int sum, int three) {
		int nxt = n + 1;
		if (nxt > 12) {
			int tmp = Math.min(sum, prices[3]);
			res = Math.min(tmp, res);
			return;
		}
		if (three == 2)
			three = -1;

		int min = Math.min(prices[1], prices[0] * plan[nxt]);

		if (three < 0) { // 3달 이용권 쓰고 있는 중이 아닐 때
			dfs(nxt, sum + min, three); // 1일 혹은 1달 이용권 사용
			dfs(nxt, sum + prices[2], three + 1); // 3달 이용권 사용
		} else { // 3달 이용권 쓰는 중이면
			dfs(nxt, sum, three + 1);
		}

	}
}