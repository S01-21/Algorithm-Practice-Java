import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	static int N, D, R, res;
	static boolean[] isDeleted, vis;
	static Node[] adjList;
	static class Node{
		int num;
		Node next;
		public Node(int num, Node next) {
			this.num = num;
			this.next = next;
		}
	}
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		isDeleted = new boolean[N];
		adjList = new Node[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N; i++) {
			int p = Integer.parseInt(st.nextToken());
			if (p == -1) {
				R = i;
				continue;
			}
			adjList[p] = new Node(i, adjList[p]);
		}
		
		D = Integer.parseInt(br.readLine());
		isDeleted[D] = true;
		delete(D);
		
		bfs(R);
		
		System.out.println(res);
		br.close();
	}
	private static void delete(int num) {
		for (Node nxt = adjList[num]; nxt != null; nxt = nxt.next) {
			if (isDeleted[nxt.num])	continue;
			isDeleted[nxt.num] = true;
			delete(nxt.num);
		}
	}
	private static void bfs(int num) {
		Queue<Integer> queue = new ArrayDeque<>();
		vis = new boolean[N];
		vis[num] = true;
		queue.offer(num);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			if (isDeleted[cur])	continue;
			if (adjList[cur] == null || check(cur)) {
				res++;
				continue;
			}
			
			for (Node nxt = adjList[cur]; nxt != null; nxt = nxt.next) {
				if (vis[nxt.num])	continue;
				if (isDeleted[nxt.num])	continue;
				vis[nxt.num] = true;
				queue.offer(nxt.num);
			}
		}
	}
	private static boolean check(int num) {
		for (Node nxt = adjList[num]; nxt != null; nxt = nxt.next) {
			if (!isDeleted[nxt.num])	return false;	
		}
		return true;
	}
}
