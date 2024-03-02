import java.util.*;
import java.io.*;

// 핀볼 게임
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] board;
	static int[] dx = { -1, 0, 1, 0 }; // 상(0)-우(1)-하(2)-좌(3)
	static int[] dy = { 0, 1, 0, -1 };

	static class Pair {
		int x, y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static ArrayList<Pair>[] wormhole;
	static int maxScore;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine().trim());
			board = new int[N][N];
			wormhole = new ArrayList[11];
			for (int i = 0; i <= 10; i++) {
				wormhole[i] = new ArrayList<>();
			}
			StringTokenizer st = null;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] >= 6 && board[i][j] <= 10) { // 웜홀
						wormhole[board[i][j]].add(new Pair(i, j));
					}
				}
			}

			maxScore = -1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] != 0)
						continue;
					for (int dir = 0; dir < 4; dir++) {
						play(i, j, dir);
					}
				}
			}

			sb.append(maxScore).append("\n");
		}
		System.out.println(sb);
	}

	private static void play(int initX, int initY, int dir) {
		int score = 0;
		int x = initX;
		int y = initY;

		while (true) {
			// 블록일 경우 방향 전환 
			if (board[x][y] >= 1 && board[x][y] <= 5) {
				score++;
				switch (board[x][y]) {
				case 1:
					if (dir == 3) {
						dir -= 3;
					} else if (dir == 2) {
						dir--;
					} else {
						dir = (dir + 2) % 4;
					}
					break;
				case 2:
					if (dir == 3) {
						dir--;
					} else if (dir == 0) {
						dir++;
					} else {
						dir = (dir + 2) % 4;
					}
					break;
				case 3:
					if (dir == 1) {
						dir++;
					} else if (dir == 0) {
						dir += 3;
					} else {
						dir = (dir + 2) % 4;
					}
					break;
				case 4:
					if (dir == 1) {
						dir--;
					} else if (dir == 2) {
						dir++;
					} else {
						dir = (dir + 2) % 4;
					}
					break;
				case 5:
					dir = (dir + 2) % 4;
					break;
				}
			}

			// 블록인 경우는 방향바뀌고, 아니면 원래 방향으로 감
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			// 벽일 경우
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				score++;
				dir = (dir + 2) % 4;
				nx = x;
				ny = y;
			}

			// 처음 위치로 돌아왔을 경우 or 블랙홀일 경우 종료
			if ((nx == initX && ny == initY) || board[nx][ny] == -1) {
				break;
			}

			// 웜홀일 경우
			if (board[nx][ny] >= 6 && board[nx][ny] <= 10) {
				for (Pair tmp : wormhole[board[nx][ny]]) {
					if (tmp.x == nx && tmp.y == ny) continue;
					nx = tmp.x;
					ny = tmp.y;
					break;
				}
			}
			// 다음칸 이동 
			x = nx;
			y = ny;
		}
		maxScore = Math.max(maxScore, score);
	}
}