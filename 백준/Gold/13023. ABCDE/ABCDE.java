import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static ArrayList<ArrayList<Integer>> friends = new ArrayList<>();
	static boolean[] vis;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i=0; i<N; i++) {
			friends.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			friends.get(a).add(b);
			friends.get(b).add(a);
		}
		
		for (int i=0; i<N; i++) {
			vis = new boolean[N];
			dfs(i, 0);
		}
		
		System.out.println(0);
	}

	private static void dfs(int idx, int sum) {
		vis[idx] = true;
		if (sum == 4) {
			System.out.println(1);
			System.exit(0);
		}
		for (int nxt : friends.get(idx)) {
			if (vis[nxt])	continue;
			dfs(nxt, sum+1);
			vis[nxt] = false;
		}
	}
	
}