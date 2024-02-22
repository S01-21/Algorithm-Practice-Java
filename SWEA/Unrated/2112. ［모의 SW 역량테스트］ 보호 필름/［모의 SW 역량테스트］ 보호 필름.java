import java.util.*;
import java.io.*;

// 보호 필름
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int D, W, K;
	static int[][] film; // 0: A특성, 1: B특성
	static int[][] copyFilm;
	static int res; // 성능검사 통과할 수 있는 약품의 최소 투입 횟수 (결과)
	static boolean[] isSelected;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			film = new int[D][W];
			copyFilm = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i=0; i<D; i++) {
				copyFilm[i] = film[i].clone();
			}

			res = Integer.MAX_VALUE;
			isSelected = new boolean[D];
			divide(0);

			if (res == Integer.MAX_VALUE) {
				res = 0;
			}
			sb.append(res).append("\n");
		}

		System.out.println(sb);
	}

	static void divide(int cnt) {
		if (cnt == D) {
			func(0, 0);
			for (int i=0; i<D; i++) {
				film[i] = copyFilm[i].clone();
			}
			return;
		}
		isSelected[cnt] = true;
		divide(cnt + 1);
		isSelected[cnt] = false;
		divide(cnt + 1);
	}

	// row: 현재 행, cnt: 약품 투입 횟수
	private static void func(int row, int cnt) {
		if (cnt >= res)
			return;
		if (row == D) {
			if (test()) {
				res = Math.min(cnt, res);
			}
			return;
		}
		if (isSelected[row]) {
			// A적용
			Arrays.fill(film[row], 0);
			func(row + 1, cnt + 1);

			// B적용
			Arrays.fill(film[row], 1);
			func(row + 1, cnt + 1);
		} else {
			// 적용x
			func(row + 1, cnt);
		}

	}

	static boolean test() {
		boolean isPass = false;
		for (int c = 0; c < W; c++) {
			isPass = false;
			int cnt = 1;
			for (int r = 1; r < D; r++) {
				if (film[r - 1][c] == film[r][c]) {
					cnt++;
					if (cnt == K) {
						isPass = true;
						break;
					}
				} else {
					cnt = 1;
				}
			}
			if (!isPass)
				return false;
		}
		return isPass;
	}
}