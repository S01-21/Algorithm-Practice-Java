import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] board;
	static int mx = Integer.MIN_VALUE;
	static int[][] deltas = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				mx = Math.max(mx, board[i][j]); // 합쳐지지 못할 경우 대비 현재 최댓값 저장
			}
		}
		func(0, board);
		System.out.println(mx);
	}

	private static void func(int cnt, int[][] prevBoard) { // cnt: 현재까지 이동 횟수
		if (cnt == 5) { // 5번 이동했으면 최댓값 갱신 후 리턴
			int tmp = prevBoard[0][0];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (tmp < prevBoard[i][j]) {
						tmp = prevBoard[i][j];
					}
				}
			}
			mx = Math.max(tmp, mx);
			return;
		}

		// 유도부분: 4방향으로 이동 후 다음 방향 정하고 이동하러
		int[][] curBoard;
		for (int dir = 0; dir < 4; dir++) {
			curBoard = move(dir, prevBoard);
			func(cnt + 1, curBoard);
		}
	}

	private static int[][] move(int dir, int[][] prevBoard) {
		// 2차원배열 깊은 복사 
		int[][] tmp = new int[N][N];
		for (int i = 0; i < N; i++) {
			tmp[i] = prevBoard[i].clone();
		}

		if (dir == 0) { // 위로 스와이프
			for (int c = 0; c < N; c++) { // 각 열마다 위로 스와이프
				for (int r = 0; r < N; r++) { // 위에서 아래로 내려오면서
					if (tmp[r][c] == 0)	continue;
					int cnt=1;
					while (true) {
						int nr = r + deltas[dir][0]*cnt;
						int nc = c + deltas[dir][1]*cnt;
						if (isOut(nr, nc))	break;
						if (tmp[nr][nc] == 0){
							cnt++;
							continue;
						}
						if (tmp[r][c] != tmp[nr][nc])	break;
						if (tmp[r][c] == tmp[nr][nc]) {
							tmp[nr][nc] = 0;
							tmp[r][c] *= 2;
							break;
						}
					}
				}
				
				// 0 자리 메꾸기 
				for (int r = 0; r< N-1; r++) {
					if (tmp[r][c] == 0) {	// 0과 
						int nr = r+1;
						while (nr < N) {
							if (tmp[nr][c] != 0) {	// 처음 만나는 0이아닌수 자리 바꾸기 
								tmp[r][c] = tmp[nr][c];
								tmp[nr][c] = 0;
								break;
							}
							nr++;
						}
					}
				}
				
			}
		} else if (dir == 1){ // 오른쪽으로 스와이프
			for (int r = 0; r < N; r++) {
				for (int c = N-1; c >=0 ; c--) {
					if (tmp[r][c] == 0)	continue;
					int cnt = 1;
					while (true) {
						int nr = r + deltas[dir][0]*cnt;
						int nc = c + deltas[dir][1]*cnt;
						if (isOut(nr, nc))
							break;
						if (tmp[nr][nc] == 0){
							cnt++;
							continue;
						}
						if (tmp[r][c] != tmp[nr][nc])	break;
						if (tmp[r][c] == tmp[nr][nc]) {
							tmp[nr][nc] = 0;
							tmp[r][c] *= 2;
							break;
						}
					}
				}
				for (int c = N-1; c > 0; c--) {
					if (tmp[r][c] == 0) {
						int nc = c - 1;
						while (nc >= 0) {
							if (tmp[r][nc] != 0) {
								tmp[r][c] = tmp[r][nc];
								tmp[r][nc] = 0;
								break;
							}
							nc--;
						}
					}
				}
			}
		} else if (dir == 2) { // 아래로 스와이프
			for (int c = 0; c < N; c++) {
				for (int r = N-1; r >=0; r--) {
					if (tmp[r][c] == 0)	continue;
					int cnt =1;
					while (true) {
						int nr = r + deltas[dir][0]*cnt;
						int nc = c + deltas[dir][1]*cnt;
						if (isOut(nr, nc))
							break;
						if (tmp[nr][nc] == 0){
							cnt++;
							continue;
						}
						if (tmp[r][c] != tmp[nr][nc])	break;
						if (tmp[r][c] == tmp[nr][nc]) {
							tmp[nr][nc] = 0;
							tmp[r][c] *= 2;
							break;
						}
					}
				}
				
				for (int r = N-1; r > 0; r--) {	
					if (tmp[r][c] == 0) {
						int nr = r - 1;
						while (nr >= 0) {
							if (tmp[nr][c] != 0) {
								tmp[r][c] = tmp[nr][c];
								tmp[nr][c] = 0;
								break;
							}
							nr--;
						}
					}
				}
			}
		} else if (dir == 3) { // 왼쪽으로 스와이프
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (tmp[r][c] == 0)	continue;
					int cnt = 1;
					while (true) {
						int nr = r + deltas[dir][0]*cnt;
						int nc = c + deltas[dir][1]*cnt;
						if (isOut(nr, nc))
							break;
						if (tmp[nr][nc] == 0){
							cnt++;
							continue;
						}
						if (tmp[r][c] != tmp[nr][nc])	break;
						if (tmp[r][c] == tmp[nr][nc]) {
							tmp[nr][nc] = 0;
							tmp[r][c] *= 2;
							break;
						}
					}
				}
				
				for (int c = 0; c < N-1; c++) {
					if (tmp[r][c] == 0) {
						int nc = c+1;
						while (nc < N) {
							if (tmp[r][nc] != 0) {
								tmp[r][c] = tmp[r][nc];
								tmp[r][nc] = 0;
								break;
							}
							nc++;
						}
					}
				}
			}
		}
		return tmp;
	}
	static boolean isOut(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}