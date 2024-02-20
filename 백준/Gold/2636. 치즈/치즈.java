import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[][] board;
	static boolean[][] vis;
	static int time; // 치즈가 모두 녹아 없어지는 데 걸리는 시간
	static int remain; // 치즈가 모두 녹기 한 시간 전에 남아있는 개수
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상-하-좌-우
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		vis = new boolean[N][M];
		findOutside(0, 0); // 치즈 외부에 있는 공기 구분하기
		
		while (true) {
			remain = 0;
			vis = new boolean[N][M];
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (board[i][j] == 1) {
						remain++;
					}
				}
			}
			meltCheese();	// 공기와 닿은 치즈 녹이기
			time++;		// 한 시간 경과
			
			// 치즈구멍(0) 외부인지 체크해주기
			vis = new boolean[N][M];
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (board[i][j] == 0) {
					for (int dir =0; dir < 4; dir++) {
						int nx = i + deltas[dir][0];
						int ny = j + deltas[dir][1];
						if (isOut(nx, ny)) continue;
						if (board[nx][ny] == -1) {
							checkHole(i,j);
						}
					}
					}
				}
			}
			
			vis = new boolean[N][M];
			int tmpCheese = 0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (vis[i][j])	continue;
					if (board[i][j] == 1) {
						countCheese(i, j);	// 치즈 녹은 후 바뀐 치즈 개수 카운트
						tmpCheese++;
					}
				}
			}
			if (tmpCheese == 0)	break;
		}
		System.out.println(time + "\n" + remain);
	}
	static void checkHole(int x, int y) {
		vis[x][y] = true;
		board[x][y] = -1;
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + deltas[dir][0];
			int ny = y + deltas[dir][1];
			if (isOut(nx, ny))	continue;
			if (vis[nx][ny])	continue;
			if (board[nx][ny] == 0) {
				checkHole(nx, ny);
			}
		}
	}
	private static void countCheese(int x, int y) {
		vis[x][y] = true;
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + deltas[dir][0];
			int ny = y + deltas[dir][1];
			if (isOut(nx, ny))	continue;
			if (vis[nx][ny])	continue;
			if (board[nx][ny] != 1)	continue;
			countCheese(nx, ny);
		}
	}
	private static void meltCheese() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1) {	// 치즈면 4방탐색
					for (int dir=0; dir < 4; dir++) {
						int nx = i + deltas[dir][0];
						int ny = j + deltas[dir][1];
						if (isOut(nx, ny))	continue;
						if (board[nx][ny] == -1) {
							board[i][j] = 2;
							break;
						}
					}
				}
			}
		}
		for (int i=0; i<N; i++) {	// 녹은 부분 -1로 바꿔주기
			for (int j=0; j<M; j++) {
				if (board[i][j] == 2) {
					board[i][j] = -1;
				}
			}
		}
	}

	private static void findOutside(int x, int y) {
		vis[x][y] = true;
		board[x][y] = -1; // 치즈 내부와 구분하기 위해 0을 -1로 바꿔줌
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + deltas[dir][0];
			int ny = y + deltas[dir][1];
			if (isOut(nx, ny))
				continue;
			if (vis[nx][ny])
				continue;
			if (board[nx][ny] != 0)
				continue;
			findOutside(nx, ny);
		}
	}

	static boolean isOut(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}
}