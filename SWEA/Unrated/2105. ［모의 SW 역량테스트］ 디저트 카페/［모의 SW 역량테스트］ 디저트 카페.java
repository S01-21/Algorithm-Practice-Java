import java.util.*;
import java.io.*;

// 디저트 카페 
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] map;
	static int[][] deltas = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } }; // 우하향-좌하향-좌상향-우상향
	static int res; // 디저트를 가장 많이 먹을 때의 디저트 수 (결과)
	static boolean[][] vis;
	static boolean[] check; // 이전에 나온 숫자 방문 못하도록

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			res = Integer.MIN_VALUE;
			// 양옆과 아래로 두칸 여유가 있어야 사각형 만들 수 있음
			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					vis = new boolean[N][N];
					check = new boolean[101];
					dfs(i, j, i, j, 0, 0);
				}
			}

			// 디저트 먹을 수 없는 경우 -1
			if (res == Integer.MIN_VALUE) {
				res = -1;
			}
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int x, int y, int initX, int initY, int prevDir, int sum) {
		check[map[x][y]] = true;
		vis[x][y] = true;
		for (int dir = prevDir; dir < 4; dir++) {	// 이전 방향과 같거나 다음 방향으로만 이동 가능
			int nx = x + deltas[dir][0];
			int ny = y + deltas[dir][1];
			if (isOut(nx, ny))	continue;
			if ((prevDir % 2 == dir % 2) && (prevDir != dir))	continue;	// 왔던 방향 반대로 바로는 못감
			if (nx == initX && ny == initY) { // 한바퀴 돌고 원래 자리 도착
				res = Math.max(res, sum + 1);
				return;
			}
			if (!check[map[nx][ny]] && !vis[nx][ny]) {	// 방문한 적 없고, 이전에 나오지 않은 숫자이면 이동
				dfs(nx, ny, initX, initY, dir, sum + 1);
				check[map[nx][ny]] = false;
				vis[nx][ny] = false;
			}
		}

	}

	static boolean isOut(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}