import java.util.*;
import java.io.*;

// 골드 5. 집합의 표현 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] parents;
	static boolean[] vis;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		makeSet();
		M = Integer.parseInt(st.nextToken());	// 연산 개수
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (op == 0) {
				union(a, b);
			} else if (op == 1) {
				if (find(a) == find(b)) {
					sb.append("YES").append("\n");
				} else {
					sb.append("NO").append("\n");
				}
			}
		}
		System.out.println(sb);
		br.close();
	}
	private static void makeSet() {
		parents = new int[N+1];
		for (int i=0; i<=N; i++	) {
			parents[i] = i;
		}
	}
	private static int find(int a) {
		if (parents[a] == a) 	return a;
		return parents[a] = find(parents[a]);
	}
	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)	return false;
		parents[rootB] = rootA;
		return true;
	}
}