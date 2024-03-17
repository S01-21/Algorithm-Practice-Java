import java.util.*;
import java.io.*;

// LCA 2 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static ArrayList<Integer>[] tree;
	static int[][] table;
	static int[] depth;
	static final int log = (int) (Math.log10(100000) / Math.log10(2));

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 노드의 개수
		tree = new ArrayList[N + 1];
		table = new int[log + 1][N + 1]; // j번 노드의 2^i 위의 부모노드
		depth = new int[N + 1]; // 노드의 레벨
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>(); // 트리 초기화
		}

		StringTokenizer st = null;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			tree[from].add(to);
			tree[to].add(from);
		}

		findParent(); // 테이블 0번행 초기화
		setTable();

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			findLCA(a, b);
		}
		System.out.println(sb);
	}

	private static void findLCA(int a, int b) {
		if (depth[a] > depth[b]) {	// 항상 a가 b보다 위(depth가 더 작음)
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		for (int i = log; i>=0; i--) {
			if (depth[b] - depth[a] >= Math.pow(2, i)) {	  
				b = table[i][b];	// 두 노드 같은 레벨 되도록하는 b 조상찾아가기
			}
		}
		
		if (a==b) {	// a가 a, b의 lca였다면 바로 리턴 
			sb.append(a).append("\n");
			return;
		}
		
		for (int i=log; i>=0; i--) {
			if (table[i][a] != table[i][b]) {	
				a = table[i][a];
				b = table[i][b];
			}
		}
		sb.append(table[0][a]).append("\n");
	}

	private static void findParent() {
		boolean[] vis = new boolean[N + 1];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(1);
		vis[1] = true;

		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (--size >= 0) {
				int p = queue.poll();
				depth[p] = level;
				for (int c : tree[p]) { // 연결노드 탐색
					if (vis[c])
						continue;
					vis[c] = true;
					queue.offer(c);
					table[0][c] = p; // 바로 위 부모 저장
				}
			}
			level++;
		}
	}

	static void setTable() {
		for (int i = 1; i <= log; i++) {
			for (int j = 1; j <= N; j++) {
				table[i][j] = table[i - 1][table[i - 1][j]];
			}
		}
	}

}