import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int res;
	static boolean[] isSelected;
	static int[] pop;
	static boolean[] connect;
	static ArrayList<Integer>[] adjList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pop = new int[N];
		adjList = new ArrayList[N];
		for (int i=0; i<N; i++) {
			adjList[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			pop[i] = Integer.parseInt(st.nextToken());
		}
		for (int from=0; from<N; from++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j=0; j<num; j++) {
				int to = Integer.parseInt(st.nextToken()) - 1;
				adjList[from].add(to);
			}
		}
		
		
		res = Integer.MAX_VALUE;
		isSelected = new boolean[N];
		subs(0, 0);
		
		if (res == Integer.MAX_VALUE) {
			res = -1;
		}
		sb.append(res);
		System.out.println(sb);
		br.close();
	}

	// idx: 결정할 인덱스, cnt: true로 뽑은 개수 
	private static void subs(int idx, int cnt) {
		if (idx == N) {
			if (cnt != 0 && cnt != N) {
				if (check()) {
					int popT = 0;
					int popF = 0;
					for (int i=0; i<N; i++) {
						if (isSelected[i]) {
							popT += pop[i];
						} else {
							popF += pop[i];
						}
					}
					int diff = Math.abs(popT - popF);
					res = Math.min(res, diff);
				}
			}
			return;
		}
		
		isSelected[idx] = true;
		subs(idx+1, cnt+1);
		isSelected[idx] = false;
		subs(idx+1, cnt);
	}

	private static boolean check() {
		connect = new boolean[N];
		for (int i=0; i<N; i++) {
			if (isSelected[i]) {
				bfs(i, true);
				break;
			}
		}
		for (int i=0; i<N; i++) {
			if (isSelected[i] && !connect[i]) {
				return false;
			}
		}
		
		connect = new boolean[N];
		for (int i=0; i<N; i++) {
			if (!isSelected[i]) {
				bfs(i, false);
				break;
			}
		}
		for (int i=0; i<N; i++) {
			if (!isSelected[i] && !connect[i]) {
				return false;
			}
		}
		
		return true;
	}
	static void bfs(int start, boolean value) {
		Queue<Integer>	queue = new ArrayDeque<>();
		connect[start] = true;
		queue.offer(start);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int nxt : adjList[cur]) {
				if (isSelected[nxt] == value && !connect[nxt]) {
					connect[nxt] = true;
					queue.offer(nxt);
				}
			}
		}
	}
}