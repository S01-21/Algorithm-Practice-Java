import java.util.*;
import java.io.*;

// 골드 3. 음악프로그램 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	
	static Queue<Integer> queue;
	static int[] inDegree;
	static Node[] adjList;
	static class Node{
		int num;
		Node next;
		public Node(int num, Node next) {
			super();
			this.num = num;
			this.next = next;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 가수의 수 
		M = Integer.parseInt(st.nextToken());	// 보조 PD의 수 
		adjList = new Node[N+1];
		inDegree = new int[N+1];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int first = Integer.parseInt(st.nextToken());
			for (int j=0; j<num-1; j++) {
				int second = Integer.parseInt(st.nextToken());
				adjList[first] = new Node(second, adjList[first]);
				inDegree[second]++;
				first = second;
			}
		}
		
		queue = new ArrayDeque<>();
		for (int i=1; i<=N; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			sb.append(cur).append("\n");
			for (Node tmp = adjList[cur]; tmp != null; tmp = tmp.next) {
				inDegree[tmp.num]--;
				if (inDegree[tmp.num] == 0) {
					queue.offer(tmp.num);
				}
			}
		}
		boolean flag = true;
		for (int i=1; i<=N; i++) {
			if (inDegree[i] != 0) {
				flag = false;
				break;
			}
		}
		if (!flag) {
			System.out.println(0);
		} else {
			System.out.println(sb);
		}
		br.close();
	}
}