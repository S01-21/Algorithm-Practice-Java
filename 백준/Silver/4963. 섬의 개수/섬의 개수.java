import java.util.*;
import java.io.*;

// 실버 2. 섬의 개수 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int W, H;
	static int[][] map;
	static int res;
	static int[] dx = {-1,1,0,0, -1,1,-1,1};
	static int[] dy = {0,0,-1,1, -1,1,1,-1};
	static boolean[][] vis;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());	// 열 개수 
			H = Integer.parseInt(st.nextToken());	// 행 개수
			if (W==0 && H==0)	break;
			map = new int[H][W];
			for (int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			res = 0;	// 섬의 개수 
			vis = new boolean[H][W];
			for (int i=0; i<H; i++) {
				for (int j=0; j<W; j++) {
					if (vis[i][j])	continue;
					if (map[i][j] == 1) {
						vis[i][j] = true;
						dfs(i,j);
						res++;
					}
				}
			}
			
			sb.append(res).append("\n");
			
		}
		System.out.println(sb);
		br.close();
	}
	private static void dfs(int x, int y) {
		for (int dir = 0; dir < 8; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (nx < 0 || nx >= H || ny < 0 || ny >= W)	continue;
			if (vis[nx][ny])	continue;
			if (map[nx][ny] == 0)	continue;
			vis[nx][ny] = true;
			dfs(nx, ny);
		}
	}
}