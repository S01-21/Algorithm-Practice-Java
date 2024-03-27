import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static char[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] vis;
	static int res;
	static int[] selected;
	static Pair[] input;
	static class Pair{
		int x, y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[5][5];
		input = new Pair[25];
		int idx = 0;
		for (int i=0; i<5; i++) {
			String str = br.readLine();
			for (int j=0; j<5; j++) {
				map[i][j] = str.charAt(j);
				input[idx] = new Pair(i, j);
				idx++;
			}
		}
		
		selected = new int[7];
		comb(0, 0, 0, 0);
		
		System.out.println(res);
	}
	private static void comb(int cnt, int st, int scnt, int ycnt) {
		if (ycnt > 3)	return;
		if (cnt == 7) {
			if (check()) {
				res++;
			}
			return;
		}
		
		for (int i=st; i<25; i++) {
			selected[cnt] = i;
			if (map[input[i].x][input[i].y] == 'S') {
				comb(cnt + 1, i + 1, scnt +1, ycnt);
			} else {
				comb(cnt + 1, i + 1, scnt, ycnt +1);
			}
		}
		
	}
	private static boolean check() {
		int[][] tmpMap = new int[5][5];
		for (int idx : selected) {
			Pair tmp = input[idx];
			tmpMap[tmp.x][tmp.y] = 1; 
		}  // 선택된 7명 위치만 1로 표시 
		
		Queue<Pair> queue = new ArrayDeque<>();
		vis = new boolean[5][5];
		boolean flag = true;
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				if (tmpMap[i][j] == 1) {	// 시작점 찾기 
					queue.offer(new Pair(i, j));
					vis[i][j] = true;
					flag = false;
					break;
				}
			}
			if (!flag) break;
		}
		
		// bfs (선택된 7명)
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5)	continue;
				if (tmpMap[nx][ny]== 0 || vis[nx][ny])	continue;
				vis[nx][ny] = true;
				queue.offer(new Pair(nx, ny));
			}
		}
		
		// tmpMap에 방문하지 않은 1이 있으면 인접하지 않은것
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				if (!vis[i][j] && tmpMap[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}
}