import java.util.*;
import java.io.*;

// 	스도쿠 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int[][] board;
	static boolean[] check; // 1~9 숫자사용했는지 체크 (0번 인덱스 사용x)
	static ArrayList<Pair> pos;
	static class Pair {
		int x, y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Pair (" + x + ", " + y + ")";
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		pos = new ArrayList<>();
		board = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String str = br.readLine();
			for (int j = 0; j < 9; j++) {
				board[i][j] = str.charAt(j) - '0';
				if (board[i][j] == 0) {
					pos.add(new Pair(i, j));
				}
			}
		}
		
		
		find(0);
	}
	private static void find(int cnt) {
		if (cnt == pos.size()) {	// 모든 0 자리 숫자 채워지면 출력 후 종료 
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(board[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);
		}
		
		for (int j = 1; j <= 9; j++) {
			board[pos.get(cnt).x][pos.get(cnt).y] = j;
			if (check(pos.get(cnt).x, pos.get(cnt).y)) {	// 조건 만족하면 다음 탐색 
				find(cnt + 1);
			}
			board[pos.get(cnt).x][pos.get(cnt).y] = 0;	// 원래대로 
		}
	}
	private static boolean check(int x, int y) {
		// 가로 확인
		check = new boolean[10];
		for (int j = 0; j < 9; j++) {
			if (check[board[x][j]])
				return false;
			if (board[x][j] != 0) {
				check[board[x][j]] = true;
			}
		}
		// 세로 확인
		check = new boolean[10];
		for (int i = 0; i < 9; i++) {
			if (check[board[i][y]])
				return false;
			if (board[i][y] != 0) {
				check[board[i][y]] = true;
			}
		}
		// 사각형 확인
		int startX = 0;
		int startY = 0;
		if (x <= 2) {
			if (y <= 2) {
			} else if (y > 2 && y <= 5) {
				startY = 3;
			} else {
				startY = 6;
			}
		} else if (x > 2 && x <= 5) {
			startX = 3;
			if (y <= 2) {
			} else if (y > 2 && y <= 5) {
				startY = 3;
			} else {
				startY = 6;
			}
		} else {
			startX = 6;
			if (y <= 2) {
			} else if (y > 2 && y <= 5) {
				startY = 3;
			} else {
				startY = 6;
			}
		}
		check = new boolean[10];
		for (int i = startX; i < startX + 3; i++) {
			for (int j = startY; j < startY + 3; j++) {
				if (check[board[i][j]])
					return false;
				if (board[i][j] != 0) {
					check[board[i][j]] = true;
				}
			}
		}
		
		// 모두 통과했을 경우 
		return true;
	}
}