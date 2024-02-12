import java.io.*;
import java.util.*;

public class Main {
	static int T, N;
	static int[][] profit;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			profit = new int[N][3];
			for (int i = 0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0 ; j < 3; j++) {
					profit[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int sum = 0;
			for (int i = 0 ; i < N ; i++) {
				int mx = 0;
				for (int j = 0 ; j < 3; j++) {
					if (profit[i][j] > mx) {
						mx = profit[i][j];
					}
				}
				sum += mx;
			}
			
			
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}
}