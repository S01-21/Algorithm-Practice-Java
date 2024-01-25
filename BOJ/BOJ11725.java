import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		boolean[] vis = new boolean[N];
		int[] parent = new int[N];
		
		ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
		for (int i=0; i<N; i++) {
			tree.add(new ArrayList<>());
		}
		
		for (int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 노드들의 연결 구조 저장 (그래프 구현)
			tree.get(a-1).add(b-1);
			tree.get(b-1).add(a-1);
		}
		
		Queue<Integer> queue = new LinkedList<>();
		vis[0] = true;
		queue.add(0);	//루트부터 트리 탐색 
		
		while(!queue.isEmpty()) {
			int p = queue.remove();
			for (int child : tree.get(p)) { 
				if (!vis[child]) {
					vis[child] = true;
					queue.add(child);
					parent[child] = p;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<N; i++) {
			sb.append(parent[i]+1).append("\n");
		}
		System.out.println(sb);
	}
}
