import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int minCost;
	static boolean[] vis;
	static ArrayList<Node>[] adjList;
	static class Node{
		int num, weight;
		public Node(int num, int weight) {
			super();
			this.num = num;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "Node [num=" + num + ", weight=" + weight + "]";
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adjList = new ArrayList[N];
		
		for (int i=0; i<N; i++) {
			adjList[i] = new ArrayList<>();
		}
		StringTokenizer st = null;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 0)	continue;
				adjList[i].add(new Node(j, tmp));
			}
		}
		minCost = Integer.MAX_VALUE;
		
		for (int i=0; i<N; i++) {
			vis = new boolean[N];
			vis[i] = true;
			func(i, i, 0, 1);
			vis[i] = false;
		}
		
		System.out.println(minCost);
	}
	private static void func(int n, int init, int sum, int cnt) {
		if (cnt == N) {
			for (Node nxt : adjList[n]) {
				if (nxt.num == init) {
					minCost = Math.min(minCost, sum + nxt.weight);
				}
			}
			return;
		}
		for (Node nxt : adjList[n]) {
			if (vis[nxt.num])	continue;
			vis[nxt.num] = true;
			func(nxt.num, init, sum + nxt.weight, cnt + 1);
			vis[nxt.num] = false;
		}
	}
}