import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, res;
	static boolean[][] board;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new boolean[N][M];
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<M; j++) {
				char c = str.charAt(j);
				if (c == 'W') {
					board[i][j] = false;
				} else if (c == 'B') {
					board[i][j] = true;
				}
			}
		}
		
		res = Integer.MAX_VALUE;
		for (int i=0; i<N-7; i++) {
			for (int j=0; j<M-7; j++) {
				check(i, j, false);
				check(i, j, true);
			}
		}
		System.out.println(res);
		br.close();
	}
	private static void check(int x, int y, boolean flag) {
		int cnt = 0;
		for (int i = x ; i < x + 8; i++) {
			for (int j = y; j < y + 8; j++) {
				if (board[i][j] != flag) {
					cnt++;
				}
				if (j != y + 7) {
					flag = !flag;
				}
			}
		}
		res = Math.min(res, cnt);
	}
	
	
}