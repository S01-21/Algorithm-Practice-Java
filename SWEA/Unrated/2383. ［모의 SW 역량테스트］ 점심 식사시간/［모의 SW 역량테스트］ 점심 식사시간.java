
import java.util.*;
import java.io.*;

// 점심 식사시간 
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] map;
	static int res; // 이동 완료되는 최소시간 (결과)
	static ArrayList<Person> people;
	static Stairs[] stairs;
	static boolean[] isSelected; // True면 1번계단으로 가는 사람들
	static boolean[][] vis;
	static ArrayList<Person> peopleA, peopleB;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			people = new ArrayList<>();
			stairs = new Stairs[2];

			int idx = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						people.add(new Person(i, j));
					} else if (map[i][j] > 1) {
						stairs[idx++] = new Stairs(i, j, map[i][j]);
					}
				}
			}
			res = Integer.MAX_VALUE;
			isSelected = new boolean[people.size()];
			divide(0);

			sb.append(res + 1).append("\n");
		}
		System.out.println(sb);
	}

	private static void divide(int cnt) {
		if (cnt == people.size()) {
			peopleA = new ArrayList<>();
			peopleB = new ArrayList<>();
			for (int i = 0; i < people.size(); i++) {
				if (isSelected[i]) {
					peopleA.add(people.get(i));
				} else {
					peopleB.add(people.get(i));
				}
			}
			int timeA = move(0);
			int timeB = move(1);
			int max = Math.max(timeA, timeB);
			res = Math.min(max, res);
			return;
		}

		isSelected[cnt] = true;
		divide(cnt + 1);
		isSelected[cnt] = false;
		divide(cnt + 1);
	}

	private static int move(int num) {
		int time = 0;
		switch(num) {
		case 0:
			for (int i=0; i<peopleA.size(); i++) {
				peopleA.get(i).moveCnt = 0;
			}
			break;
		case 1:
			for (int i=0; i<peopleB.size(); i++) {
				peopleB.get(i).moveCnt = 0;
			}
			break;
		}
		while (true) {
			time++;

			// 계단의 대기열에 있던 사람들 계단안으로 이동
			while (!stairs[num].queue.isEmpty() && stairs[num].moving.size() < 3) {
				stairs[num].moving.add(stairs[num].queue.poll());
			}
			
			switch (num) {
			// 계단입구로 이동 가능한 사람들 계단 대기열(큐)에 추가
			case 0:
				for (int i = 0; i < peopleA.size(); i++) {
						int d = Math.abs(stairs[num].x - peopleA.get(i).x) + Math.abs(stairs[num].y - peopleA.get(i).y);
						if (d == time) {
							stairs[num].queue.offer(peopleA.get(i));
						}
					
				}
				break;
			case 1:
				for (int i = 0; i < peopleB.size(); i++) {
						int d = Math.abs(stairs[num].x - peopleB.get(i).x) + Math.abs(stairs[num].y - peopleB.get(i).y);
						if (d == time) {
							stairs[num].queue.offer(peopleB.get(i));
						}
					
				}
				break;
			}

			// 계단 내려가고 있는 사람들 이동시간 ++
			// 계단길이만큼 이동했으면 계단에서 빼주기
			for (int i = stairs[num].moving.size() - 1; i >= 0; i--) {
				stairs[num].moving.get(i).moveCnt++;
				if (stairs[num].moving.get(i).moveCnt == stairs[num].length) {
					stairs[num].moving.remove(i);
				}
			}

			
			// 모든 사람들이 계단 내려갔으면 반복종료
			boolean flag = true;
			switch(num) {
			case 0:
				for (int i = 0; i < peopleA.size(); i++) {
					if (peopleA.get(i).moveCnt < stairs[num].length) { // 이동횟수가 계단길이보다 작은 사람이 하나라도 있으면 종료불가능
						flag = false;
						break;
					}
				}
				break;
			case 1:
				for (int i = 0; i < peopleB.size(); i++) {
					if (peopleB.get(i).moveCnt < stairs[num].length) { // 이동횟수가 계단길이보다 작은 사람이 하나라도 있으면 종료불가능
						flag = false;
						break;
					}
				}
				break;
			}

			if (flag) {
				break;
			}
		}
		return time;
	}

	static class Stairs {
		int x, y;
		int length;
		Queue<Person> queue;
		ArrayList<Person> moving;

		public Stairs(int x, int y, int length) {
			super();
			this.x = x;
			this.y = y;
			this.length = length;
			queue = new ArrayDeque<>();
			moving = new ArrayList<>();
		}

        /*
		@Override
		public String toString() {
			return "Stairs [x=" + x + ", y=" + y + ", length=" + length + ", queue=" + queue + ", moving=" + moving
					+ "]";
		}
        */
	}

	static class Person {
		int x, y;
		int moveCnt;

		public Person(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

        /*
		@Override
		public String toString() {
			return "Person [x=" + x + ", y=" + y + ", moveCnt=" + moveCnt + "]";
		}
        */

	}
}