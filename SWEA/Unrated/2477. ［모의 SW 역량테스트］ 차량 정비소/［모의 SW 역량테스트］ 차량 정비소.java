import java.util.*;
import java.io.*;

// 차량 정비소
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N, M, K, A, B;
	static int[] reception;
	static int[] repair;
	static int[][] curReception;
	static int[][] curRepair;
	static Queue<Person> queue;
	static PriorityQueue<Person>	pq1, pq2;
	static ArrayList<Integer> same;
	static int res;		// A, B를 이용한 고객들의 고객번호의 합
	static class Person implements Comparable<Person>{
		int num, arrival;
		int reception;
		public Person(int num, int arrival) {
			super();
			this.num = num;
			this.arrival = arrival;
		}
		
		public Person(int num, int arrival, int reception) {
			super();
			this.num = num;
			this.arrival = arrival;
			this.reception = reception;
		}

		@Override
		public int compareTo(Person o) {
			if (this.arrival != o.arrival) {
				return this.arrival - o.arrival;
			} else {
				return this.reception - o.reception;
			}
		}

		@Override
		public String toString() {
			return "Person [num=" + num + ", arrival=" + arrival + ", reception=" + reception + "]";
		}
		
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc<= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 접수 창구 개수
			M = Integer.parseInt(st.nextToken());	// 정비 창구 개수
			K = Integer.parseInt(st.nextToken());	// 총 고객 수
			A = Integer.parseInt(st.nextToken());	// 지갑 두고 간 고객 접수 창구 번호
			B = Integer.parseInt(st.nextToken());	// 지갑 두고 간 고객 정비 창구 번호
			reception = new int[N + 1];
			repair = new int[M + 1];
			queue = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=N; i++) {
				reception[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=M; i++) {
				repair[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=K; i++) {
				int arrival = Integer.parseInt(st.nextToken());
				queue.offer(new Person(i, arrival));
			}
			same = new ArrayList<>();
			curReception = new int[N+1][2];	//[0]: 고객번호, [1]: 경과시간 
			curRepair = new int[M+1][2];
			
			int time = 0;
			pq1 = new PriorityQueue<>((o1, o2) -> (o1.num - o2.num));
			pq2 = new PriorityQueue<>();
			
			while (true) {
				// 현재 time에 도착한 사람들 접수 창구 대기열에 추가 
				while (true) {
					if (!queue.isEmpty() && queue.peek().arrival == time) {
						pq1.offer(queue.poll());
					} else {
						break;
					}
				}
				// 접수 데스크 돌면서 처리 끝난 사람들 정비 창구 대기열에 추가
				for (int i=1; i<=N; i++) {
					if (curReception[i][0] == 0)	continue;
					if (curReception[i][1] == reception[i]) {
						pq2.offer(new Person(curReception[i][0], time, i));
						curReception[i][0] = 0;	// 빈자리로 만들어주기
						curReception[i][1] = 0;
					}
				}
				// 빈 접수데스크 자리에 대기열에 있던 사람들 배정
				for (int i=1; i<= N; i++) {
					if (curReception[i][0] == 0) {	// 빈자리
						if (!pq1.isEmpty()) {
							curReception[i][0] = pq1.poll().num;
						}
					}
				}
				// 정비 데스크 돌면서 처리 끝난 사람들 내보내기
				for (int i = 1; i <= M; i++) {
					if (curRepair[i][0] == 0)	continue;
					if (curRepair[i][1] == repair[i]) {
						curRepair[i][0] = 0;	// 빈자리로 만들어주기
						curRepair[i][1] = 0;
					}
				}
				// 빈 정비데스크 자리에 대기열에 있던 사람들 배정
				for (int i=1; i<=M; i++) {
					if (curRepair[i][0] == 0) {
						if (!pq2.isEmpty()) {
							Person tmp = pq2.poll();
							curRepair[i][0] = tmp.num;
							if (tmp.reception == A && i == B) {
								same.add(tmp.num);
							}
						}
					}
				}
				
				
				time++;
				// 접수, 정비데스크 고객 경과시간 반영
				for (int i=1; i<=N; i++) {
					if (curReception[i][0] == 0)	continue;
					curReception[i][1]++;
				}
				for (int i=1; i<=M; i++) {
					if (curRepair[i][0] == 0)	continue;
					curRepair[i][1]++;
				}
				
				if (checkLeft())	break;
			}
			
			res = 0;
			if (same.size() == 0) {
				res = -1;
			} else {
				for (int num : same) {
					res += num;
				}
			}
			sb.append(res).append("\n");
		}
		
		System.out.println(sb);
	}
	private static boolean checkLeft() {
		if (!queue.isEmpty())	return false;
		if (!pq1.isEmpty())	return false;
		if (!pq2.isEmpty())	return false;
		for (int i = 1; i <= N; i++) {	// 접수데스크 남은사람있는지 확인
			if (curReception[i][0] != 0)	return false;
		}
		for (int i=1; i<= M; i++) {
			if (curRepair[i][0] != 0)	return false;
		}
		return true;
	}
}
