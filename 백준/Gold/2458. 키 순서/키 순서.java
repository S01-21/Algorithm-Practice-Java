import java.util.*;
import java.io.*;

// 키 순서 
public class Main {
	static int N, M;
	static int res;			// 자신의 키 순서 정확히 알 수 있는 학생 수 (결과) 
	static boolean[] vis;
	static Node[] std;		// 학생들의 번호와 키 관계 저장 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 학생 수 
		std = new Node[N];
		
		
		M = Integer.parseInt(st.nextToken());	// 두 학생 키 비교한 횟수 
		for (int i = 0 ; i < N; i++) {
			std[i] = new Node(i);		// std배열 초기화 
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int shorter = Integer.parseInt(st.nextToken()) - 1;
			int taller = Integer.parseInt(st.nextToken()) - 1;
			
			// 두 학생 키 순서 양방향 저장 
			std[shorter].high.add(taller);
			std[taller].low.add(shorter);
		}
		
		for (int i = 0 ; i < N ; i++) {
			vis = new boolean[N];
			
			find(i);
		}
		
		System.out.println(res);
	}
	
	
	
	private static void find(int num) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(std[num]);
		vis[num] = true;
		// 해당 학생보다 작은 학생 탐색 
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			for (int d = 0; d < cur.low.size(); d++) {
				int nxt = cur.low.get(d);
				if (vis[nxt])	continue;
				vis[nxt] = true;
				queue.offer(std[nxt]);
			}
		}
		// 해당 학생보다 큰 학생 탐색 
		queue.offer(std[num]);
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			for (int d = 0; d < cur.high.size(); d++) {
				int nxt = cur.high.get(d);
				if (vis[nxt])	continue;
				vis[nxt] = true;
				queue.offer(std[nxt]);
			}
		}
		
		// 모든 학생 방문했는지 체크
		boolean isPossible = true;
		for (int i = 0 ; i<N; i++) {
			if (!vis[i])	isPossible = false;
		}
		if (isPossible)	res++;	// 모든학생방문 == 모든학생과 비교하여 자신의 키 순서 알 수 있음 
	}
	
	static class Node{
		int num;
		ArrayList<Integer> low, high;
		public Node(int num) {
			super();
			this.num = num;
			low = new ArrayList<>();
			high = new ArrayList<>();
		}
	}
}