import java.util.*;
import java.io.*;

// 골드 3. 게임 개발 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, sum;
	static PriorityQueue<Node> pq;
	static Node[] adjList;
	static class Node implements Comparable<Node>{
		int num, time;
		Node next;
		public Node(int num, int time, Node next) {
			super();
			this.num = num;
			this.time = time;
			this.next = next;
		}
		@Override
		public int compareTo(Node o) {
			return this.time - o.time;
		}
		
	}
	static int[] inDegree, times;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inDegree = new int[N+1];
		adjList = new Node[N+1];
		times = new int[N+1];
		StringTokenizer st = null;
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());	// 건물 짓는 데 걸리는 시간 
			times[i] = time;
			while (true) {
				int num = Integer.parseInt(st.nextToken());
				if (num == -1)	break;
				adjList[num] = new Node(i, time, adjList[num]);
				inDegree[i]++;	// 진입차수 계산 
			}
			
		}
		pq = new PriorityQueue<>();
		for (int j=1; j<=N; j++) {
			if (inDegree[j] == 0) {	// 진입차수 0인 노드 pq에 추가 (시작점)
				pq.offer(new Node(j, times[j], null));
			}
		}
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			for (Node tmp = adjList[cur.num]; tmp != null; tmp = tmp.next) {
				inDegree[tmp.num]--;
				if (inDegree[tmp.num] == 0) {
					times[tmp.num] += times[cur.num];	// 해당 건물의 짓는 시간 + 거쳐온 건물들의 짓는 시간
					pq.offer(new Node(tmp.num, times[tmp.num], null));
				}
			}
		}
		
		for (int i=1; i<=N; i++) {
			sb.append(times[i]).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
}