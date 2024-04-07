

import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/17136
public class Main{

	private static int[][] map;  // 종이 10 X 10
	private static int[] paper;  // 크기별 색종이 카운터
	private static int totalCnt;  // 가려야 하는 칸의 수
	private static int answer;

	// 결과를 한 번에 출력하기 위한 StringBuilder
	private static StringBuilder sb = new StringBuilder();

	public static void main(String args[]) throws Exception {

		/**
		 * 1. 입력파일 읽어들이기
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		/**
		 * 2. 입력 파일 객체화
		 */
		totalCnt = 0;  // 가려야 하는 칸의 수
		map = new int[10][10];
		for (int i = 0; i < 10; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(split[j]);
				if (map[i][j] == 1) {
					totalCnt++;
				}
			}
		}

		answer = Integer.MAX_VALUE;
		paper = new int[5];

		/**
		 * 3. 알고리즘 풀기
		 */
		dfs(0, 0, 0);
		if (answer == Integer.MAX_VALUE) {
			answer = -1;
		}

		/**
		 * 4. 정답 출력
		 */
		System.out.println(answer);
	}

	// 현재위치, 가려진 영역의 수
	private static void dfs(int startX, int startY, int cnt) {

		// 백트래킹
		int sum = paper[0] + paper[1] + paper[2] + paper[3] + paper[4];
		if (sum >= answer) {  // 기존 최솟값보다 크면 가지치기
			return;
		}

		// 기저부분
		if (cnt == totalCnt) {
			if (sum < answer) {
				answer = sum;  // 최소값 갱신
			}
			return;
		}

		// 오른쪽 끝까지 탐색했다면 한칸아래의 맨 처음부터 탐색함
		if (startY == 10) {
			dfs(startX + 1, 0, cnt);
			return;
		}

		// 유도부분
		// 1을 발견하면 색종이 크기 5부터 1까지 사용해보기
		if (map[startX][startY] == 1) {
			for (int pSize = 4; pSize >= 0; pSize--) {
				if (paper[pSize] < 5 && isAttach(startX, startY, pSize)) {
					// 색종이 사용하기
					int tempCnt = fill(startX, startY, pSize, 0);
					paper[pSize]++;

					// 다음 색종이 사용하러 이동
					dfs(startX, startY, cnt + tempCnt);

					// 여기 도착했다면 하나의 경우의 수 완료되었으므로 이전상태 복원
					fill(startX, startY, pSize, 1);
					paper[pSize]--;
				}
			}
			return;
		}
		else {
			dfs(startX, startY + 1, cnt);
		}
	}

	// 경계 체크
	private static boolean isInBound(int x, int y) {
		return 0 <= x && x < 10 && 0 <= y && y < 10;
	}

	private static boolean isAttach(int x, int y, int size) {

		// 경계를 벗어나면 색종이를 붙일 수 없다.
		if (!isInBound(x + size, y + size)) {
			return false;
		}

		for (int i = 0; i <= size; i++) {
			for (int j = 0; j <= size; j++) {

				// 0이면 색종이를 붙일 수 없다.
				if (map[x + i][y + j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	private static int fill(int startX, int startY, int pSize, int value) {

		int cnt = 0;
		for (int x = startX; x <= startX + pSize; x++) {
			for (int y = startY; y <= startY + pSize; y++) {
				map[x][y] = value;
				cnt++;
			}
		}
		return cnt;
	}

}
