import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, X;
	static int[][] map;
	static int res;
	static boolean[] vis;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // map크기 NxN
			X = Integer.parseInt(st.nextToken()); // 경사로 길이
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			res = 0;
			func();
		System.out.println(res);
		br.close();
	}

	private static void func() {
		boolean isPossible = true;
		// 가로 확인
		for (int i = 0; i < N; i++) {
			vis = new boolean[N];
			// 앞->뒤
			isPossible = true;
			boolean flag = false;
			int std = map[i][0];
			int cnt = 0; // 경사로 길이카운트
			for (int j = 1; j < N; j++) {
				if (flag) { // 경사로 설치중
					if (map[i][j] != std - 1) { // 설치 불가
						isPossible = false;
						break;
					}
					cnt++;
					if (cnt == X) { // 경사로 길이 끝
						for (int k=j; k>j-cnt; k--) {
							vis[k] = true;
						}
						flag = false;
						std = map[i][j];
						cnt = 0;
					}
				} else { // 경사로 설치전
					if (map[i][j] == std - 1) { // 기준보다 작은값나오면 경사로(내리막) 설치
						cnt++;
						if (cnt == X) { // 경사로 길이 끝
							for (int k=j; k>j-cnt; k--) {
								vis[k] = true;
							}
							flag = false;
							std = map[i][j];
							cnt = 0;
						} else {
							flag = true;
						}
					} else if (map[i][j] > std) {
						std = map[i][j];
					} else if (map[i][j] < std - 1) {
						isPossible = false;
						break;
					}
				}
			}
			if (flag && cnt < X)
				isPossible = false;
			if (!isPossible)
				continue;
			// 뒤->앞
			flag = false;
			std = map[i][N - 1];
			cnt = 0; // 경사로 길이카운트
			for (int j = N - 2; j >= 0; j--) {
				if (flag) { // 경사로 설치중
					if (map[i][j] != std - 1 || vis[j]) {
						isPossible = false;
						break;
					}
					cnt++;
					if (cnt == X) { // 이전까지 경사로 길이 끝
						flag = false;
						std = map[i][j];
						cnt = 0;
					}
				} else { // 경사로 설치전
					if (map[i][j] == std - 1 && !vis[j]) { // 기준보다 작은값나오면 경사로(내리막) 설치
						cnt++;
						if (cnt == X) { // 이전까지 경사로 길이 끝
							flag = false;
							std = map[i][j];
							cnt = 0;
						} else {
							flag = true;
						}
					} else if (map[i][j] > std) {
						std = map[i][j];
					} else if (map[i][j] < std - 1 || (map[i][j] == std-1 && vis[j])) {
						isPossible = false;
						break;
					}
				}
			}
			if (flag && cnt < X)
				isPossible = false;
			if (isPossible)
				res++;
		}

		// 세로 확인
		for (int c = 0; c < N; c++) {
			vis = new boolean[N];
			// 위->아래
			isPossible = true;
			boolean flag = false;
			int std = map[0][c];
			int cnt = 0; // 경사로 길이카운트
			for (int r = 1; r < N; r++) {
				if (flag) { // 경사로 설치중
					if (map[r][c] != std - 1) {
						isPossible = false;
						break;
					}
					cnt++;
					if (cnt == X) { // 이전까지 경사로 길이 끝
						for (int k=r; k>r-cnt; k--) {
							vis[k] = true;
						}
						flag = false;
						std = map[r][c];
						cnt = 0;

					} 
				} else { // 경사로 설치전
					if (map[r][c] == std - 1) { // 기준보다 작은값나오면 경사로(내리막) 설치
						cnt++;
						if (cnt == X) { // 이전까지 경사로 길이 끝
							for (int k=r; k>r-cnt; k--) {
								vis[k] = true;
							}
							flag = false;
							std = map[r][c];
							cnt = 0;

						} else {
							flag = true;
						}
					} else if (map[r][c] > std) {
						std = map[r][c];
					} else if (map[r][c] < std - 1) {
						isPossible = false;
						break;
					}
				}
			}
			if (flag && cnt < X)
				isPossible = false;
			if (!isPossible)
				continue;
			// 아래->위
			flag = false;
			std = map[N - 1][c];
			cnt = 0; // 경사로 길이카운트
			for (int r = N - 2; r >= 0; r--) {
				if (flag) { // 경사로 설치중
					if (map[r][c] != std - 1 || vis[r]) {
						isPossible = false;
						break;
					}
					cnt++;
					if (cnt == X) { // 이전까지 경사로 길이 끝
						flag = false;
						std = map[r][c];
						cnt = 0;
					} 
				} else { // 경사로 설치전
					if (map[r][c] == std - 1 && !vis[r]) { // 기준보다 작은값나오면 경사로(내리막) 설치
						cnt++;
						if (cnt == X) { // 이전까지 경사로 길이 끝
							flag = false;
							std = map[r][c];
							cnt = 0;
						} else {
							flag = true;
						}
					} else if (map[r][c] > std) {
						std = map[r][c];
					} else if (map[r][c] < std - 1 || (map[r][c] == std-1 && vis[r])) {
						isPossible = false;
						break;
					}
				}
			}
			if (flag && cnt < X)
				isPossible = false;
			if (isPossible)
				res++;
        }
	}
}