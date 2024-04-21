import java.util.*;
import java.io.*;

// 골드 2. 문제집
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static Node[] adjList;
	static int[] inDegree;
	static class Node implements Comparable<Node>{
		int num;
		Node next;
		public Node(int num, Node next) {
			super();
			this.num = num;
			this.next = next;
		}
		@Override
		public int compareTo(Node o) {	// num 작은 문제 (쉬운문제) 우선 
			return this.num - o.num;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 문제 수 
		M = Integer.parseInt(st.nextToken());	// 문제에 대한 정보 수
		
		adjList = new Node[N+1];
		inDegree = new int[N+1];
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a] = new Node(b, adjList[a]);
			inDegree[b]++;
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i=1; i<=N; i++) {
			if (inDegree[i] == 0) {
				pq.offer(new Node(i, null));
			}
		}
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			sb.append(cur.num).append(" ");
			
			for (Node tmp = adjList[cur.num]; tmp != null; tmp = tmp.next) {
				if (--inDegree[tmp.num] == 0) {
					pq.offer(new Node(tmp.num, null));
				}
			}
		}
		System.out.println(sb);
		br.close();
	}
}