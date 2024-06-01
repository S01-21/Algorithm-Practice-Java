import java.util.*;
import java.io.*;

// 골드 4. 은하철도 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] planet, parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 은하 수 
		M = Integer.parseInt(st.nextToken());	// 철도 수 
		planet = new int[N+1];		// 각 은하에 존재하는 행성 수 
		for (int i=1; i<=N; i++) {
			planet[i] = Integer.parseInt(br.readLine());
		}
		
		makeSet();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int res = union(a, b);
			if (res == 0) {	// 이미 연결 됨 
				sb.append(Math.max(planet[find(a)], planet[find(b)])).append("\n");
			} else {
				sb.append(res).append("\n");
			}
		}
		System.out.println(sb);
		br.close();
	}
	private static void makeSet() {
		parents = new int[N+1];
		for (int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}
	private static int find(int a) {
		if (parents[a] == a)	return a;
		return parents[a] = find(parents[a]);
	}
	private static int union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA < rootB) {
			parents[rootB] = rootA;	// 더 작은 은하에 철도 이용가능 행성 수 합산
			planet[rootA] += planet[rootB];
			planet[rootB] = 0;
			return planet[rootA];
		} else if (rootA > rootB){
			parents[rootA] = rootB;	// 더 작은 은하에 철도 이용가능 행성 수 합산
			planet[rootB] += planet[rootA];
			planet[rootA] = 0;
			return planet[rootB];
		} else {
			return 0;
		}
	}
}