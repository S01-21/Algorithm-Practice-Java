import java.util.*;
import java.io.*;

// 특이한 자석
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int K;
	static int res; // 회전 끝난 후 점수 총 합
	static int[][] magnet; // 자석 자성정보

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			K = Integer.parseInt(br.readLine()); // 회전 횟수
			magnet = new int[4][8];

			StringTokenizer st = null;
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < K; i++) { // K번 회전
				st = new StringTokenizer(br.readLine());
				int rotateNum = Integer.parseInt(st.nextToken()) - 1;
				int rotateDir = Integer.parseInt(st.nextToken());

				check(rotateNum, 0, rotateDir);
			}

			res = 0;
			for (int i = 0; i < 4; i++) {
				if (magnet[i][0] == 1) {
					res += Math.pow(2, i);
				}
			}
			sb.append(res).append("\n");
		}

		System.out.println(sb);
	}

	private static void check(int num, int checkDir, int dir) {
		if (checkDir == 0) { // 시작점
			if (num > 0) {
				if (magnet[num][6] != magnet[num - 1][2]) { // 왼쪽체크

					check(num - 1, -1, dir * -1);
				}
			}
			if (num < 3) {
				if (magnet[num][2] != magnet[num + 1][6]) { // 오른쪽 체크
					check(num + 1, 1, dir * -1);
				}
			}
		} else if (checkDir == -1) {
			if (num > 0) {
				if (magnet[num][6] != magnet[num - 1][2]) { // 왼쪽체크

					check(num - 1, -1, dir * -1);
				}
			}
		} else {
			if (num < 3) {
				if (magnet[num][2] != magnet[num + 1][6]) { // 오른쪽 체크
					check(num + 1, 1, dir * -1);
				}
			}
		}
		rotate(num, dir);
	}

	private static void rotate(int num, int dir) {
		Deque<Integer> dq = new ArrayDeque<>();
		for (int i = 0; i < 8; i++) {
			dq.add(magnet[num][i]);
		}
		if (dir == -1) {
			dq.addLast(dq.removeFirst());
		} else {
			dq.addFirst(dq.removeLast());
		}
		for (int i = 0; i < 8; i++) {
			magnet[num][i] = dq.pollFirst();
		}
	}

}