import java.util.*;
import java.io.*;

// 골드 4. 비밀 모임 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, K, min, res;
	static int[] pos, minDist;
	static boolean[] vis;
	static Node[] adjList;
	static class Node{
		int num, weight;
		Node next;
		public Node(int num, int weight, Node next) {
			super();
			this.num = num;
			this.weight = weight;
			this.next = next;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 방의 개수 
			M = Integer.parseInt(st.nextToken());	// 비밀통로의 개수 
			adjList = new Node[N+1];
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, dist, adjList[from]);
				adjList[to] = new Node(from, dist, adjList[to]);
			}
			K = Integer.parseInt(br.readLine());	// 모임에 참여하는 친구 수 
			pos = new int[K];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<K; i++) {
				pos[i] = Integer.parseInt(st.nextToken());
			}
			minDist = new int[N+1];
			// 1~N번을 모임장소로 설정 후
			// K명 각 위치에서부터 해당장소까지의 최단경로들을 합산
			min = Integer.MAX_VALUE;
			for (int end=1; end<=N; end++) {
				int sum = 0;
				for (int start=0; start<K; start++) {
					sum += dijkstra(pos[start], end);
				}
				// 최솟값보다 작을 때만 갱신 (같을 때는 x)
				if (min > sum)	{
					min = sum;
					res = end;
				}
			}
			sb.append(res).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	private static int dijkstra(int start, int end) {
		Arrays.fill(minDist, Integer.MAX_VALUE);
		vis = new boolean[N+1];
		minDist[start] = 0;
		for (int i = 0; i < N; i++) {
			int idx = -1;
			int min = Integer.MAX_VALUE;
			
			for (int j = 1; j <= N; j++) {
				if (vis[j]) continue;
				if (min > minDist[j]) {
					min = minDist[j];
					idx = j;
				}
			}
			if (idx == -1) break;
			
			vis[idx] = true;
			if (idx == end) break;
			for (Node tmp = adjList[idx]; tmp != null; tmp = tmp.next) {
				if (vis[tmp.num])	continue;
				if (minDist[tmp.num] > minDist[idx] + tmp.weight) {
					minDist[tmp.num] = minDist[idx] + tmp.weight;
				}
			}
		}

		return minDist[end];
	}
}