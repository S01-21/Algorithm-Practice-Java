import java.util.*;
import java.io.*;

// 게리맨더링 
public class Main {
	
	private static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] ppl;	// 각 구역의 인구 수(population) 
	static int minDiff = Integer.MAX_VALUE;	// 인구 차이 최솟값 (결과)
	static ArrayList<Node> area;
	static ArrayList<Integer> groupA, groupB;
	static boolean[] isSelected;
	static boolean check;
	static boolean[] vis;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ppl = new int[N];
		isSelected = new boolean[N];
		area = new ArrayList<>();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N ; i++) {
			ppl[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = i;
			int adjCnt = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> tmp = new ArrayList<>();
			for (int j = 0 ; j  < adjCnt; j++) {
				tmp.add(Integer.parseInt(st.nextToken()) - 1);
			}
			area.add(new Node(num-1, ppl[i], tmp));
		}
		
		divideGroup(0);
		if (minDiff == Integer.MAX_VALUE) {
			minDiff = -1;
		}
		
		System.out.println(minDiff);
	}
	private static void divideGroup(int cnt) {
		// 기저
		if (check) {	//최소 1개 구역 있는지 체크
			groupA = new ArrayList<>();
			groupB = new ArrayList<>();
			for (int i = 0 ; i < N; i++) {
				if (isSelected[i]) {
					groupA.add(i);
				} else {
					groupB.add(i);
				}
			}
			if (checkConnect()) {	// 연결되어 있으면 인구차이 구하고 최솟값 갱신
				int sumA = 0;
				int sumB = 0;
				for (int i = 0; i<groupA.size(); i++) {
					sumA += area.get(groupA.get(i)).ppl;
				}
				for (int i = 0 ; i < groupB.size(); i++) {
					sumB += area.get(groupB.get(i)).ppl;
				}
				int diff = Math.abs(sumA - sumB);
				minDiff = Math.min(diff, minDiff);
			}
		}
		if (cnt == N-1) return;
		
		// 유도
		isSelected[cnt] = true;
		check = true;
		divideGroup(cnt+1);
		isSelected[cnt] = false;
		check = false;
		divideGroup(cnt+1);
	}
	
	private static boolean checkConnect() {
		// groupA 연결 확인
		vis = new boolean[N];
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(area.get(groupA.get(0)));
		vis[groupA.get(0)] = true;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			for (int d = 0; d < cur.adj.size(); d++) {
				int nxt = cur.adj.get(d);	// 인접 구역 번호
				if (vis[nxt])	continue;
				if (!groupA.contains(nxt))	continue;
				queue.offer(area.get(nxt));
				vis[nxt] = true;
			}
		}
		for (int i = 0 ; i < groupA.size(); i++) {
			if (!vis[groupA.get(i)])	return false;
		}
		
		// groupB 연결 확인
		vis = new boolean[N];
		queue.offer(area.get(groupB.get(0)));
		vis[groupB.get(0)] = true;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			for (int d = 0; d < cur.adj.size(); d++) {
				int nxt = cur.adj.get(d);	// 인접 구역 번호
				if (vis[nxt])	continue;
				if (!groupB.contains(nxt))	continue;
				queue.offer(area.get(nxt));
				vis[nxt] = true;
			}
		}
		for (int i = 0 ; i < groupB.size(); i++) {
			if (!vis[groupB.get(i)])	return false;
		}
		
		return true;
	}
	
	static class Node{
		int num, ppl;
		ArrayList<Integer> adj;
		public Node(int num, int ppl, ArrayList<Integer> adj) {
			super();
			this.num = num;
			this.ppl = ppl;
			this.adj = adj;
		}
	}
}