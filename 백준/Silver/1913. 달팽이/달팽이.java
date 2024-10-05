import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, r, c;
	static int[][] res;
	static int[] dx = {-1, 0, 1, 0};	// 상 - 우 - 하 - 좌
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
//		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		res = new int[N][N];
		int num = 1;
		int x = N/2;
		int y = N/2;
		int dir = 0;
		int repeat = 1;
		int cnt = 0;
		res[x][y] = num;
		while (num <= N*N) {
			
			if (cnt == 2) {
				repeat++;
				cnt = 0;
			}
			for (int i = 0; i< repeat; i++) {
				num++;
				x += dx[(dir)%4];
				y += dy[(dir)%4];
				if (num > N*N) break;
				res[x][y] = num;
				
				
			}
			dir++;
			cnt++;
			
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N ; j++) {
				sb.append(res[i][j]).append(" ");
				if (res[i][j] == M) {
					r = i+1;
					c = j+1;
				}
			}
			sb.append("\n");
		}
		sb.append(r).append(" ").append(c);
		System.out.println(sb);
		br.close();
	}
}