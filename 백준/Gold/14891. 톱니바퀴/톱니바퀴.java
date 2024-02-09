import java.util.*;
import java.io.*;

public class Main {
	static int K;
	static int[][] wheel = new int[4][8];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheel[i][j] = str.charAt(j) - '0';
			}
		}

		K = Integer.parseInt(br.readLine()); // 회전 횟수
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());

			check(num, dir, 0);
		}

		// 톱니바퀴 점수 계산
		int score = 0;
		for (int i = 0; i < 4; i++) {
			if (wheel[i][0] == 1) { // 12시방향이 S극이면 득점
				score += Math.pow(2, i);
			}
		}
		System.out.println(score);
	}

	static void check(int num, int rotateDir, int checkDir) {
		if (checkDir == 0) {	// 회전 시작할 바퀴이면 양쪽 방향 다 체크 
			if (num > 0) {
				if (wheel[num][6] != wheel[num - 1][2]) {
					check(num - 1, rotateDir * -1, -1);
				}
			}
			if (num < 3) {
				if (wheel[num][2] != wheel[num + 1][6]) {
					check(num + 1, rotateDir * -1, 1);
				}
			}
		} else if (checkDir == -1) {	// 왼쪽만 체크 
			if (num > 0) {
				if (wheel[num][6] != wheel[num - 1][2]) {
					check(num - 1, rotateDir * -1, -1);
				}
			}
		} else {	// 오른쪽만 체크 
			if (num < 3) {
				if (wheel[num][2] != wheel[num + 1][6]) {
					check(num + 1, rotateDir * -1, 1);
				}
			}
		}
		rotate(num, rotateDir);	// 현재 바퀴 회전 
		
	}

	static void rotate(int num, int dir) {
		Deque<Integer> deq = new ArrayDeque<>();
		for (int i = 0; i < 8; i++) {
			deq.offer(wheel[num][i]);
		}
		if (dir == -1) {
			deq.addLast(deq.removeFirst());
		} else {
			deq.addFirst(deq.removeLast());
		}
		for (int i = 0; i < 8; i++) {
			wheel[num][i] = deq.pollFirst();
		}
	}
}