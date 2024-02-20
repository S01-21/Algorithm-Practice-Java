
import java.io.*;
import java.util.*;

// 벽돌 깨기
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N, W, H;
	static int[][] map;
	static int res; // 최대한 많은 벽돌을 제거한 후 남은 벽돌의 개수 (결과)
	static int maxSum; // 벽돌 한 번 떨어트릴 때 제거할 수 있는 최대 벽돌 개수
	static int maxIdx; // 벽돌 한 번 떨어트릴 때 최대한 많은 벽돌 제거할 수 있는 열 인덱스
	static int[] order; // 벽돌 떨어트릴 열 순서
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상-하-좌-우

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 떨어트릴 벽돌 개수
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			order = new int[N];
			res = Integer.MAX_VALUE;
			getOrder(0); // 떨어트릴 순서 정한 후 벽돌 떨어트리기

			sb.append(res).append("\n");
		}

		System.out.println(sb);
	}

	static void getOrder(int cnt) { // cnt: 뽑은 개수
		if (cnt == N) {
			int[][] tmpMap = new int[H][W];
			for (int i = 0; i < H; i++) {
				tmpMap[i] = map[i].clone();
			}
			// 벽돌 N개 떨어트리기
			for (int i = 0; i < N; i++) {
				// 해당 열 벽돌 떨어트리기
				tmpMap = dropBrick(0, order[i], tmpMap);

				// 0자리 채우기
				tmpMap = fillBlank(tmpMap);
			}
			// 남은 벽돌 개수 구하기
			int sum = 0;
			for (int r = 0; r < H; r++) {
				for (int c = 0; c < W; c++) {
					if (tmpMap[r][c] != 0) {
						sum++;
					}
				}
			}
			res = Math.min(res, sum);
			return;
		}

		for (int i = 0; i < W; i++) {
			order[cnt] = i;
			getOrder(cnt + 1);
		}
	}

	private static int[][] fillBlank(int[][] prevMap) {
		int[][] tmpMap = new int[H][W];
		for (int i = 0; i < H; i++) {
			tmpMap[i] = prevMap[i].clone();
		}

		for (int j = 0; j < W; j++) {
			for (int i = H - 1; i > 0; i--) {
				if (tmpMap[i][j] == 0) {
					for (int k = i - 1; k >= 0; k--) {
						if (tmpMap[k][j] != 0) {
							tmpMap[i][j] = tmpMap[k][j];
							tmpMap[k][j] = 0;
							break;
						}
					}
				}
			}
		}
		return tmpMap;
	}

	private static int[][] dropBrick(int x, int y, int[][] prevMap) {
		int[][] tmpMap = new int[H][W];
		for (int i = 0; i < H; i++) {
			tmpMap[i] = prevMap[i].clone();
		}

		Queue<Pair> queue = new ArrayDeque<>();
		for (int r = 0; r < H; r++) {
			if (tmpMap[r][y] != 0) { // 해당 행에서 첫번째로 만난 0이 아닌 수가 벽돌 (= 탐색 시작점)
				queue.offer(new Pair(r, y, tmpMap[r][y]));
				break;
			}
		}
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			if (tmpMap[cur.x][cur.y] == 1) {
				tmpMap[cur.x][cur.y] = 0;
				continue;
			}
			tmpMap[cur.x][cur.y] = 0;
			for (int dir = 0; dir < 4; dir++) { // 사방 탐색
				for (int k = 1; k < cur.org; k++) { // 현재값 만큼 해당 방향 탐색
					int nx = cur.x + deltas[dir][0] * k;
					int ny = cur.y + deltas[dir][1] * k;
					if (nx < 0 || nx >= H || ny < 0 || ny >= W)
						break;
					if (tmpMap[nx][ny] == 0)	continue;
					if (tmpMap[nx][ny] != 0) {
						queue.offer(new Pair(nx, ny, tmpMap[nx][ny]));
						tmpMap[nx][ny] = 0;
					}
				}
			}
		}
		return tmpMap;
	}

	static class Pair {
		int x, y;
		int org;
		public Pair(int x, int y, int org) {
			super();
			this.x = x;
			this.y = y;
			this.org = org;
		}
	}
}
