import java.util.*;
import java.io.*;

// 미생물 격리
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N, M, K;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 }; // 상(0)-하(1)-좌(2)-우(3)
	static Bug[] bugs;
	static int res; // M시간 후 남아 있는 미생물 수 총합 (결과)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 셀의 개수
			M = Integer.parseInt(st.nextToken()); // 격리 시간
			K = Integer.parseInt(st.nextToken()); // 미생물 군집의 개수

			bugs = new Bug[K];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int amount = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				bugs[i] = new Bug(x, y, amount, dir, true);
			}
			for (int i = 0; i < M; i++) {
				moveBug();
				checkConflict();
			}

			res = 0;
			countBug();
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}

	private static void checkConflict() {

		for (int i = 0; i < K; i++) {
			if (!bugs[i].alive)
				continue;
			int max = i;
			Bug bugA = bugs[i];
			int sum = bugA.amount;

			for (int j = i + 1; j < K; j++) {
				Bug bugB = bugs[j];
				if (!bugB.alive) continue;
				if (bugA.x == bugB.x && bugA.y == bugB.y) { // 충돌지점
					sum += bugB.amount;
					if (bugs[max].amount < bugB.amount) {
						max = j;
					}
				}
			}

			for (int j = i; j < K; j++) {
				Bug bugB = bugs[j];
				if (bugA.x == bugB.x && bugA.y == bugB.y) {
					if (!bugB.alive) continue;
					if (j == max) {
						bugB.amount = sum;
					}
					if (j != max) {
						bugB.alive = false;
					}
				}

			}
		}

	}

	private static void countBug() {
		for (Bug bug : bugs) {
			if (!bug.alive)
				continue;
			res += bug.amount;
		}
	}

	private static void moveBug() {
		for (Bug bug : bugs) {
			if (!bug.alive)
				continue; // 없어진 미생물이면 패스
			int nx = bug.x + dx[bug.dir];
			int ny = bug.y + dy[bug.dir];
			if (isOut(nx, ny))
				continue;
			bug.x = nx;
			bug.y = ny;
			if (isBoundary(nx, ny)) { // 약품에 닿았을 경우
				bug.amount /= 2; // 미생물 수 절반으로 줄이기
				// 반대방향으로
				switch (bug.dir) {
				case 0:
				case 2:
					bug.dir++;
					break;
				case 1:
				case 3:
					bug.dir--;
					break;
				}
				if (bug.amount == 0) {
					bug.alive = false;
					continue;
				}
				continue;
			}
		}
	}

	static boolean isOut(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}

	static boolean isBoundary(int x, int y) {
		return x == 0 || x == N - 1 || y == 0 || y == N - 1;
	}

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static class Bug {
		int x, y, amount, dir;
		boolean alive;

		public Bug(int x, int y, int amount, int dir, boolean alive) {
			super();
			this.x = x;
			this.y = y;
			this.amount = amount;
			this.dir = dir;
			this.alive = alive;
		}

		@Override
		public String toString() {
			return "Bug [(" + x + ", " + y + "), amount=" + amount + ", dir=" + dir + ", " + alive + "] ";
		}
	}
}