import java.util.*;
import java.io.*;

// 등산로 조성 
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N, K;
	static int[][] map;
	static int res; // 만들 수 있는 가장 긴 등산로의 길이 (결과)
	static int maxHeight; // 가장 높은 봉우리 높이
	static ArrayList<Pair> peaks; // 가장 높은 봉우리 위치 좌표
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상-하-좌-우
	static boolean[][] vis;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지도 한 변의 길이
			K = Integer.parseInt(st.nextToken()); // 최대 공사 가능 깊이
			map = new int[N][N];
			maxHeight = Integer.MIN_VALUE;
			res = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > maxHeight) {
						maxHeight = map[i][j]; // 봉우리 높이의 최댓값 저장
					}
				}
			}
			peaks = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == maxHeight) {
						peaks.add(new Pair(i, j)); // 가장 높은 봉우리 위치 저장
					}
				}
			}

			for (int i = 0; i < peaks.size(); i++) {
				vis = new boolean[N][N];
				dfs(peaks.get(i).x, peaks.get(i).y, false, 0); // 각 봉우리마다 dfs
			}

			sb.append(res + 1).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int x, int y, boolean flag, int dist) {
		vis[x][y] = true;
		
		res = Math.max(res, dist);
		
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + deltas[dir][0];
			int ny = y + deltas[dir][1];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;
			if (vis[nx][ny])	continue;
			if (map[nx][ny] >= map[x][y]) {
				if (map[nx][ny] - map[x][y] + 1 <= K && !flag) {
					int tmp = map[nx][ny];
					map[nx][ny] = map[x][y] - 1;
					dfs(nx, ny, !flag, dist+ 1);
					map[nx][ny] = tmp;
					vis[nx][ny] = false;
					
				} else {
					continue;
				}
			} else {
				dfs(nx, ny, flag, dist + 1);
				vis[nx][ny] = false;
			}
		}

	}

	static class Pair {
		int x, y;
		int dist;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}