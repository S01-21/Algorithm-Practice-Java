import java.util.*;
import java.io.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int N, M, d, rX, rY, cnt;
	static int[][] deltas = {{-1,0}, {0,1}, {1,0}, {0,-1}};	// 북-동-남-서 방향
	static int[][] map;
	static boolean[][] clean;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		clean = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine());
		rX = Integer.parseInt(st.nextToken());	// 로봇청소기 처음 x좌표
		rY = Integer.parseInt(st.nextToken());	// 로봇청소기 처음 y좌표
		d = Integer.parseInt(st.nextToken());		// 로봇청소기 처음 방향
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());	// 0: 빈칸, 1: 벽
			}
		}	// 입력 받기
		
		func(rX, rY);
		
	}
	
	public static void func(int x, int y) {
		if (!clean[x][y] && map[x][y] == 0) {
			clean[x][y] = true;
			cnt++;
		}
		boolean hasBlank = false;
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + deltas[dir][0];
			int ny = y + deltas[dir][1];
			if (isOut(nx, ny))	continue;
			if (!clean[nx][ny] && map[nx][ny] == 0) {
				hasBlank = true;
				break;
			}
		}
		if (!hasBlank) {	// 주변 4칸 중 청소하지 않은 빈칸 없을 때 
			int nx = x;
			int ny = y;
			switch(d) {
			case 0:
				nx++;
				break;
			case 1:
				ny--;
				break;
			case 2:
				nx--;
				break;
			case 3:
				ny++;
				break;
			}
			if (!isOut(nx, ny) && map[nx][ny] == 0) {	// 후진할 수 있으면 후진 
				func(nx, ny);
			} else if (isOut(nx, ny) || map[nx][ny] == 1){	// 후진할 수 없으면 종료 
				System.out.println(cnt);
				System.exit(0);
			}
		} else {	// 주변 4칸 중 청소하지 않은 빈칸 있을 때 
				d = (d+3) % 4;	// 반시계방향 90도 회전 
				int nx = x +  deltas[d][0];
				int ny = y +  deltas[d][1];
				if (!isOut(nx, ny) && map[nx][ny] == 0 && !clean[nx][ny]) {
					func(nx, ny);	// 앞쪽 칸이 청소되지 않은빈칸이면 한 칸 전진 
				}
				func(x, y);
			}
	}
	public static boolean isOut(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}
}