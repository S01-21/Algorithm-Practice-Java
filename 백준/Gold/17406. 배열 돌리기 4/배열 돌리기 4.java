import java.util.*;
import java.io.*;

// 배열 돌리기 4
public class Main {
	static int N, M, K;
	static int[][] arr;
	static int[][] copyArr;
	static Info[] infos;
	static int[] order;
	static boolean[] isSelected;
	static int minA; // 배열 A의 값의 최솟값
	static class Info {
		int r, c, s;
		public Info(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
		@Override
		public String toString() {
			return "[r=" + r + ", c=" + c + ", s=" + s + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][M + 1];
		minA = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		infos = new Info[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			infos[i] = new Info(r, c, s);
		}

		order = new int[K];
		isSelected = new boolean[K];
		perm(0);

		System.out.println(minA);
	}

	private static void perm(int cnt) {
		if (cnt == K) {
			rotate();
			return;
		}

		for (int i = 0; i < K; i++) {
			if (isSelected[i])
				continue;
			order[cnt] = i;
			isSelected[i] = true;
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}

	private static void rotate() {
		copyArr = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			copyArr[i] = arr[i].clone();
		}

		for (int i = 0; i < K; i++) { // K번 회전
			Info cur = infos[order[i]];
			for (int rotateCnt = 0; rotateCnt < cur.s; rotateCnt++) {
				int startR = cur.r - cur.s+rotateCnt;
				int startC = cur.c - cur.s+rotateCnt;
				int endR = cur.r + cur.s-rotateCnt;
				int endC = cur.c + cur.s-rotateCnt;
				int tmp = copyArr[startR][startC];

				for (int row = startR + 1; row <= endR; row++) {
					copyArr[row-1][startC] = copyArr[row][startC];
				}
				
				for (int col = startC; col <= endC-1; col++) {
					copyArr[endR][col] = copyArr[endR][col+1];
				}
				
				for (int row = endR; row > startR; row--) {
					copyArr[row][endC] = copyArr[row-1][endC];
				}
				
				for (int col = endC; col > startC+1; col--) {
					copyArr[startR][col] = copyArr[startR][col-1];
				}
				
				copyArr[startR][startC+1] = tmp;
			}
		}
		
		calcA();	// 회전 다 했으면 배열 A 값 계산 
	}

	static void calcA() {
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= M; j++) {
				sum += copyArr[i][j];
			}
			minA = Math.min(minA, sum);
		}
	}
}