import java.util.*;
import java.io.*;
// 실버 2. 헌내기는 친구가 필요해 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, res;
	static char[][] map;
	static boolean[][] vis;
	static int ix, iy;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		vis = new boolean[N][M];
		for (int i=0 ;i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'I') {
					ix = i;
					iy = j;
				}
			}
		}
		vis[ix][iy] = true;
		dfs(ix, iy);
		if (res == 0) {
			System.out.println("TT");
		} else {
			System.out.println(res);
		}
		br.close();
	}
	private static void dfs(int x, int y) {
		for (int dir = 0; dir <4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M)	continue;
			if (vis[nx][ny])	continue;
			if (map[nx][ny] == 'X')	continue;
			if (map[nx][ny] == 'P') {
				res++;
				map[nx][ny] = 'O';
			}
			vis[nx][ny] = true;
			dfs(nx, ny);
			//vis[nx][ny] = false;
		}
	}
}