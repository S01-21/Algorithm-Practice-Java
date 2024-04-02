import java.util.*;
import java.io.*;
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int D, W, K;
	static int[][] film;
	static int[] inject;
	static int res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());	// 행 
			W = Integer.parseInt(st.nextToken());	// 열  
			K = Integer.parseInt(st.nextToken());	// 합격기준
			film = new int[D][W];
			inject = new int[D];
			
			for (int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			res = Integer.MAX_VALUE;
			subset(0,0);
			
			
			
			sb.append(res).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	private static void subset(int row, int cnt) {	// row: 현재 볼 행, cnt: 약품투입횟수 
		if (cnt >= res) {
			return;
		}
		if (row == D) {
			if (test()) {
				res = Math.min(res, cnt);
			}
			return;
		}
		inject[row] = -1;	// 주입 x
		subset(row + 1, cnt);
		inject[row] = 0;	// A
		subset(row + 1, cnt + 1);
		inject[row] = 1;	// B
		subset(row + 1, cnt + 1);
	}
	private static boolean test() {
		for (int j = 0 ; j < W; j++) {
			int cnt = 1;
			for (int i = 0; i < D-1; i++) {
				int cur = inject[i] == -1 ? film[i][j] : inject[i];
				int nxt = inject[i+1] == -1 ? film[i+1][j] : inject[i+1];
				if (cnt == K) {
					break;
				}
				if (cur == nxt) {
					cnt++;
					continue;
				}
				
				cnt = 1;
			}
			if (cnt < K) {
				return false;
			}
		}
		
		return true;
	}
}
