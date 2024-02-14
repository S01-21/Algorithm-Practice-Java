import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0 ; i< N; i++) {
			String str = br.readLine();
			for (int j = 0; j<N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		func(0, 0, N);
		System.out.println(sb);
	}
	static boolean check(int x, int y, int n) {
		for (int i = x; i < x + n; i++) {
			for (int j= y; j < y + n; j++) {
				if (map[i][j] != map[x][y]) {
					return false;
				}
			}
		}
		return true;
	}
	private static void func(int x, int y, int n) {
		if (check(x, y, n)) {
			sb.append(map[x][y]);
			return;
		}
		sb.append('(');
		func(x, y, n/2);
		func(x, y+n/2, n/2);
		func(x+n/2, y, n/2);
		func(x+n/2, y+n/2, n/2);
		sb.append(')');
	}
}