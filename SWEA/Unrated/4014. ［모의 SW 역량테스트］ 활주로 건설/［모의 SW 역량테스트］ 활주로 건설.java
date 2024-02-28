import java.util.*;
import java.io.*;

// 활주로 건설 
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N, X;
	static int[][] map;
	static int res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지도 한 변의 크기
			X = Integer.parseInt(st.nextToken()); // 경사로의 길이
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			res = 0;
			for (int i = 0; i < N; i++) {
				int std = map[i][0]; // 현재 기준값
				int cnt = 1; // 현재보다 큰 수 만났을 때 지나온 만큼을 X와 비교하기 위해
//				int length = 1;	// 현재보다 작은 수 만났을 때 최소 길이를 X와 비교하기 위해 
				boolean runway = false;
				boolean isPossible = true;

				for (int j = 1; j < N; j++) {
					if (map[i][j] == std) {
						cnt++;
						if (runway && cnt == X) {
							runway = false;
							cnt = 0;
						}
						if ((j == N - 1) && runway) {
							if (cnt < X) {
								isPossible = false;
							}
						}

					} else if (map[i][j] == std - 1) { // 기준보다 작은애 만나면
						if (runway) {
							if (cnt < X) { // 경사로 길이에 미치지 못하고 보다 작은 높이 나오면 경사로 설치 불가능
								isPossible = false;
								break;
							} else { // 경사로 하나 설치가능한 상태
								std = map[i][j];
								cnt = 1;
							}
						} else {
							cnt = 1;
							std = map[i][j];
							runway = true;
							if (j == N - 1) {
								if (cnt < X) {
									isPossible = false;
								}
							}
						}
					} else if (map[i][j] == std + 1) { // 기준보다 큰 애 만나면
						if (cnt >= X) {
							std = map[i][j];
							cnt = 1;
						} else {
							isPossible = false;
							break;
						}
					} else {
						isPossible = false;
						break;
					}
				}

				if (isPossible) {
					res++;
				}
			}
			
			for (int c = 0; c < N; c++) {
				int std = map[0][c]; // 현재 기준값
				int cnt = 1; // 현재보다 큰 수 만났을 때 지나온 만큼을 X와 비교하기 위해
				boolean runway = false;
				boolean isPossible = true;

				for (int r = 1; r < N; r++) {
					if (map[r][c] == std) {
						cnt++;
						if (runway && cnt == X) {
							runway = false;
							cnt = 0;
						}
						if ((r == N - 1) && runway) {
							if (cnt < X) {
								isPossible = false;
							}
						}

					} else if (map[r][c] == std - 1) { // 기준보다 작은애 만나면
						if (runway) {
							if (cnt < X) { // 경사로 길이에 미치지 못하고 보다 작은 높이 나오면 경사로 설치 불가능
								isPossible = false;
								break;
							} else { // 경사로 하나 설치가능한 상태
								std = map[r][c];
								cnt = 1;
							}
						} else {
							cnt = 1;
							std = map[r][c];
							runway = true;
							if (r == N - 1) {
								if (cnt < X) {
									isPossible = false;
								}
							}
						}
					} else if (map[r][c] == std + 1) { // 기준보다 큰 애 만나면
						if (cnt >= X) {
							std = map[r][c];
							cnt = 1;
						} else {
							isPossible = false;
							break;
						}
					} else {
						isPossible = false;
						break;
					}
				}

				if (isPossible) {
					res++;
				}
			}

			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}

}