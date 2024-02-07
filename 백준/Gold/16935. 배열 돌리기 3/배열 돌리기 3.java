import java.util.*;
import java.io.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int N, M, R;
	static int[][] board;
	static int[] modes;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		modes = new int[R];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			modes[i] = Integer.parseInt(st.nextToken()); // 수행할 연산 번호
		}

		// 연산 수행 
		for (int i = 0; i < R; i++) {
			func(modes[i]);
		}
		
		// 결과 출력
		for (int i = 0 ; i < N; i++) {
			for (int j = 0 ; j < M; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void func(int mode) {
		int t = 0;
		int[][] tmp;
		int temp;
		switch (mode) {
		case 1:
			for (int j = 0; j < M; j++) {
				t = 0;
				for (int i = 0; i < N / 2; i++) {
					swap(i, j, N-1 - t, j);
					t++;
				}
			}
			break;
		case 2:
			for (int i = 0; i < N; i++) {
				t = 0;
				for (int j = 0; j < M / 2; j++) {
					swap(i, j, i, M-1 - t);
					t++;
				}
			}
			break;
		case 3:
			tmp = new int[M][N];
			for (int i = 0 ; i<N; i++) {
				for (int j = 0 ; j < M; j++) {
					tmp[j][N-1-i] = board[i][j];
				}
			}
			temp = N;
			N = M; 
			M = temp;
			board = tmp;
			break;
		case 4:
			tmp = new int[M][N];
			for (int i = 0 ; i<N; i++) {
				for (int j = 0 ; j < M; j++) {
					tmp[M-1-j][i] = board[i][j];
				}
			}
			temp = N;
			N = M; 
			M = temp;
			board = tmp;
			break;
		case 5:
			tmp = new int[N][M];
			for (int i = 0; i<N/2; i++) {	// 1번 -> 2번
				for (int j = 0; j<M/2; j++) {
					tmp[i][j + M/2] = board[i][j];
				}
			}
			for (int i = 0; i<N/2; i++) {	// 2번 -> 3번
				for (int j = M/2; j<M; j++) {
					tmp[i + N/2][j] = board[i][j];
				}
			}
			for (int i = N/2; i<N; i++) {	// 3번 -> 4번
				for (int j = M/2; j<M; j++) {
					tmp[i][j - M/2] = board[i][j];
				}
			}
			for (int i = N/2; i<N; i++) {	// 4번 -> 1번
				for (int j = 0; j<M/2; j++) {
					tmp[i - N/2][j] = board[i][j];
				}
			}
			board = tmp;
			break;
		case 6:
			tmp = new int[N][M];
			for (int i = 0; i<N/2; i++) {	// 1번 -> 4번
				for (int j = 0; j<M/2; j++) {
					tmp[N/2 + i][j] = board[i][j];
				}
			}
			for (int i = N/2; i<N; i++) {	// 4번 -> 3번
				for (int j =0; j<M/2; j++) {
					tmp[i][j+ M/2] = board[i][j];
				}
			}
			for (int i = N/2; i<N; i++) {	// 3번 -> 2번
				for (int j = M/2; j<M; j++) {
					tmp[i - N/2][j] = board[i][j];
				}
			}
			for (int i = 0; i<N/2; i++) {	// 2번 -> 1번
				for (int j = M/2; j<M; j++) {
					tmp[i][j - M/2] = board[i][j];
				}
			}
			
			board = tmp;
			break;
		}
	}

	public static void swap(int x1, int y1, int x2, int y2) {
		int tmp = board[x1][y1];
		board[x1][y1] = board[x2][y2];
		board[x2][y2] = tmp;
	}

}