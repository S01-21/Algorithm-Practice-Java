
import java.util.*;
import java.io.*;

// 프로세서 연결하기 
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] map;
	static ArrayList<Core> list;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int coreCnt; // Core 총 개수
	static int res; // 전선 길이 합 최소 (결과)
	static int maxCnt; // 연결된 Core 개수 최대

	static class Core {
		int x, y;

		public Core(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<>();

			coreCnt = 0;
			StringTokenizer st = null;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) { // Core 위치
						coreCnt++;
						if (i == N - 1 || j == N - 1 || i == 0 || j == 0) {
							continue;
						} else {
							list.add(new Core(i, j));
						}
					}
				}
			}

			maxCnt = Integer.MIN_VALUE;
			res = Integer.MAX_VALUE;
			func(0, 0, 0);

			sb.append(res).append("\n");
		}

		System.out.println(sb);
	}

	// num: 코어 인덱스, core: 연결된 코어 개수, wireSum: 사용된 전선 길이 합 
	private static void func(int num, int core, int wireSum) {

		if (num == list.size()) {
			if (maxCnt < core) {	// Core 최대 개수로 
				maxCnt = core;
				res = wireSum;
			} else if (maxCnt == core) {	// 전선 최소 길이로 
				res = Math.min(res, wireSum);
			}
			return;
		}
		
		Core cur = list.get(num);
		for (int dir = 0; dir < 4; dir++) {
			int cnt = 0; // 전선길이 카운트
			int nx = cur.x;
			int ny = cur.y;
			while (true) {
				nx += dx[dir];
				ny += dy[dir];

				if (isOut(nx, ny))	break; // 전선연결 성공

				if (map[nx][ny] == 1) {
					// 다른 코어or전선 만나서 전선 못만들면 패스 (전선길이 리셋)
					cnt = 0;
					break;
				}

				cnt++; // 지나갈 수 있으면 전선길이 증가시키면서 계속 확인
			}

			// cnt(전선길이) 만큼 map에 반영
			int tx = cur.x;
			int ty = cur.y;
			for (int i = 0; i < cnt; i++) {
				tx += dx[dir];
				ty += dy[dir];
				map[tx][ty] = 1;
			}

			if (cnt == 0) { // 전선 연결 못했을 경우
				func(num + 1, core, wireSum);
			} else { // 전선 연결 성공했을 경우
				func(num + 1, core + 1, wireSum + cnt);
			}

			// cnt(전선길이) 만큼 map 원상복귀 
			tx = cur.x;
			ty = cur.y;
			for (int i = 0; i < cnt; i++) {
				tx += dx[dir];
				ty += dy[dir];
				map[tx][ty] = 0;
			}

		}
	}

	static boolean isOut(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}
