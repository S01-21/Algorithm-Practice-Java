import java.util.*;
import java.io.*;

public class Main {
	static int R, C, T;
	static int[][] air;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static ArrayList<Pair> cleaner = new ArrayList<>();
	static Queue<Pair> queue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		air = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				air[i][j] = Integer.parseInt(st.nextToken());
				if (air[i][j] == -1) { // 공기청정기 위치 저장
					cleaner.add(new Pair(i, j));
				}
			}
		}

		for (int i = 0; i < T; i++) {
			// 미세먼지 있는 칸 저장
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					if (air[j][k] > 0) {
						queue.offer(new Pair(j, k, air[j][k]));
					}
				}
			}
			spread(); // 미세먼지 있는 칸 모두 확산시키기
			cleanAbove(); // 위쪽 공기청정기 작동
			cleanBelow(); // 아래쪽 공기청정기 작동
		}

		// 남아있는 미세먼지 양 계산 
		int res = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (air[i][j] != 0 && air[i][j] != -1) {
					res += air[i][j];
				}
			}
		}
		System.out.println(res);
	}

	static void spread() {
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			int amount = cur.dust / 5;	// 초기 미세먼지 양 기준 5분의 1 해줌 
			int spreadCnt = 0;
			if (amount == 0)
				continue; // 확산시킬 양 0이면 패스
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + deltas[dir][0];
				int ny = cur.y + deltas[dir][1];
				if (isOut(nx, ny))
					continue;
				if (air[nx][ny] == -1)
					continue;
				spreadCnt++;
				air[nx][ny] += amount;
			}
			air[cur.x][cur.y] -= amount * spreadCnt;
		}
	}

	static void cleanAbove() {
		Pair above = cleaner.get(0);
		int curX = above.x;
		int curY = above.y;
		int nx = curX - 1;
		int ny = curY;
		int dir = 0; // 위쪽으로 먼저 이동
		while (nx != above.x || ny != above.y) {
			switch (dir) { // 다음 가져올 방향 지정
			case 0:	// 위쪽으로 이동 
				curX = nx;
				nx = nx - 1;
				if (nx < 0) {
					dir = 1;
					nx = 0;
					ny = curY + 1;
				}
				break;
			case 1:	// 오른쪽으로 이동 
				curY = ny;
				ny = ny + 1;
				if (ny >= C) {
					dir = 2;
					nx = curX + 1;
					ny = C - 1;
				}
				break;
			case 2:	// 아래로 이동 
				curX = nx;
				nx = nx + 1;
				if (nx > above.x) {
					dir = 3;
					nx = above.x;
					ny = curY - 1;
				}
				break;
			case 3:	// 왼쪽으로 이동 
				curY = ny;
				ny = ny - 1;
				if (ny == above.y) {	// 공기청정기 만나면 현재칸 정화 후 종료 
					air[curX][curY] = 0;
					continue;
				}
				break;
			}
			air[curX][curY] = air[nx][ny];	// 다음칸의 미세먼지를 현재칸으로 옮기기 

		}
	}

	static void cleanBelow() {
		Pair below = cleaner.get(1);
		int curX = below.x;
		int curY = below.y;
		int nx = curX + 1;
		int ny = curY;
		int dir = 0; // 아랫쪽으로 먼저 이동
		while (nx != below.x || ny != below.y) {
			switch (dir) { // 다음 가져올 방향 지정
			case 0:	// 아래로 이동 
				curX = nx;
				nx = nx + 1;
				if (nx >= R) {
					dir = 1;
					nx = R - 1;
					ny = curY + 1;
				}
				break;
			case 1:	// 오른쪽으로 이동 
				curY = ny;
				ny = ny + 1;
				if (ny >= C) {
					dir = 2;
					nx = curX - 1;
					ny = C - 1;
				}
				break;
			case 2:	// 위로 이동 
				curX = nx;
				nx = nx - 1;
				if (nx < below.x) {
					dir = 3;
					nx = below.x;
					ny = curY - 1;
				}
				break;
			case 3:	// 왼쪽으로 이동 
				curY = ny;
				ny = ny - 1;
				if (ny == below.y) {
					air[curX][curY] = 0;
					continue;
				}
				break;
			}
			air[curX][curY] = air[nx][ny];

		}
	}

	static boolean isOut(int x, int y) {
		return x < 0 || x >= R || y < 0 || y >= C;
	}

	static class Pair {
		int x, y;
		int dust;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Pair(int x, int y, int dust) {
			super();
			this.x = x;
			this.y = y;
			this.dust = dust;
		}
	}
}