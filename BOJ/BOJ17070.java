import java.io.*;
import java.util.*;

public class Main {
	static int[][] board;
	static int N;
	static int cnt;
	
	// 기존 파이프의 상태에 따른 다음 이동 가능 방향 
	static int[][] dHorizontal = {{0, 1}, {1,1}};
	static int[][] dVertical = {{1, 0}, {1,1}};
	static int[][] dDiagonal = {{0, 1},{1, 0}, {1,1}};
	
	static boolean isBoundary(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
	
	//(x, y) -> (n-1, n-1) 이동방법계산 함수 
	// type == 0 : 현재 상태 가로
	// type == 1 : 현재 상태 세로
	// type == 2 : 현재 상태 대각선
	static void func(int x, int y, int type) {	// 파이프의 오른쪽 좌표 (x, y), 파이프가 놓여진 상태 type
		if (x == N-1 && y == N-1) {	// (N-1, N-1) 도달하면 리턴 
			cnt++;
			return;
		}
		if (type == 0) {	// 현재 파이프 상태가 가로일 경우 
			for (int dir = 0; dir < 2; dir++) {	// 오른쪽, 오른쪽대각선아래 순서로 이동 탐색 
				int nx = x + dHorizontal[dir][0];
				int ny = y + dHorizontal[dir][1];
				if (isBoundary(nx, ny))	continue;
				if (board[nx][ny] == 1)	continue;	// 옮길 칸이 벽이면 스킵 
				if (dir == 1) {
					if (board[nx-1][ny] != 1 && board[nx][ny-1] != 1)	// 대각선으로 가려면 인접한 부분도 벽이 아니어야 함 
						func(nx, ny, 2);	//대각선 호출
				} else {
					func(nx, ny, 0);	//가로 호출
				}
			}
		}else if (type == 1) {	// 현재 파이프 상태가 세로일 경우 
			for (int dir = 0; dir < 2; dir++) {
				int nx = x + dVertical[dir][0];
				int ny = y + dVertical[dir][1];
				if (isBoundary(nx, ny))	continue;
				if (board[nx][ny] == 1)	continue;
				if (dir == 1) {
					if (board[nx-1][ny] != 1 && board[nx][ny-1] != 1)
						func(nx, ny, 2);	//대각선 호출
				} else {
					func(nx, ny, 1);	//세로 호출
				}
			}
		}else if (type ==2) {	// 현재 파이프 상태가 대각선일 경우 
			for (int dir = 0; dir < 3; dir++) {
				int nx = x + dDiagonal[dir][0];
				int ny = y + dDiagonal[dir][1];
				if (isBoundary(nx, ny))	continue;
				if (board[nx][ny] == 1)	continue;
				if (dir == 2) {
					if (board[nx-1][ny] == 1 || board[nx][ny-1] == 1)
						continue;
				}
				func(nx, ny, dir);
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		func(0, 1, 0);
		System.out.println(cnt);
	}

}
