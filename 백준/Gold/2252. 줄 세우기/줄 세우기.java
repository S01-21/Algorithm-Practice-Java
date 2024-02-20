import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static Node[] stdList;
	static Queue<Node> queue = new ArrayDeque<>();
	static ArrayList<Integer> order = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		stdList= new Node[N+1];
		
		
		for (int i=0; i<N+1; i++) {
			stdList[i] = new Node(i);
		}
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int stdA = Integer.parseInt(st.nextToken());
			int stdB = Integer.parseInt(st.nextToken());
			stdList[stdB].from.add(stdA);
			stdList[stdA].to.add(stdB);
		}
		
		for (int i=1; i<=N; i++) {
			if (stdList[i].from.size() == 0) {
				queue.offer(stdList[i]);
			}
		}
		
		bfs();
		
		for (int n : order) {
			sb.append(n).append(" ");
		}
		System.out.println(sb);
	}
	static void bfs() {
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			order.add(cur.num);
			for (int i = cur.to.size()-1 ; i >= 0; i--) {
				int nxt = cur.to.get(i);
				stdList[nxt].from.remove(Integer.valueOf(cur.num));
				stdList[cur.num].to.remove(Integer.valueOf(nxt));
				if (stdList[nxt].from.size() == 0) {
					queue.offer(stdList[nxt]);
				}
			}
		}
	}
	static class Node{
		int num;
		ArrayList<Integer> to, from;
		public Node(int num) {
			super();
			this.num = num;
			to = new ArrayList<>();
			from = new ArrayList<>();
		}
	}
}