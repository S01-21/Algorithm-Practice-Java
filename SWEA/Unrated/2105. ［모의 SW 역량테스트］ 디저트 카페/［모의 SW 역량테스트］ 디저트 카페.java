import java.util.*;
import java.io.*;

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int res;
	static int[][] map;
	static boolean[][] vis;
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {1, -1, -1, 1};
	static Set<Integer> route;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			StringTokenizer st = null;
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			res = Integer.MIN_VALUE;
			
			for (int i=0; i < N-2; i++) {	// 아래 2칸 여유 필요 
				for (int j=1 ; j < N-1; j++) {	// 양옆 1칸 여유 필요 
					route = new HashSet<>();
					vis = new boolean[N][N];
					vis[i][j] = true;
					route.add(map[i][j]);
					func(i, j, i, j, 0, 1);
				}
			}
			
			if (res == Integer.MIN_VALUE) {
				res = -1;
			}
			sb.append("#").append(tc).append(" ").append(res).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	private static void func(int startX, int startY, int x, int y, int prevD, int cnt) {
		for (int dir = prevD; dir < 4; dir++) {
			if (prevD == 0) {
				if (dir == 2)	continue;
			} else if (prevD == 1) {
				if (dir == 3)	continue;
			}
			
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			// ㅊㅜㄹㅂㅏㄹㅈㅓㅁ ㄷㅗㅊㅏㄱ 
			if (startX == nx && startY == ny) {
				res = Math.max(res, cnt);
				return;
			}
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			if (vis[nx][ny])	continue;
			if (route.contains(map[nx][ny])) continue;
			vis[nx][ny] = true;
			route.add(map[nx][ny]);
			func(startX, startY, nx, ny, dir, cnt + 1);
			vis[nx][ny] = false;
			route.remove(map[nx][ny]);
		}
	}
}
