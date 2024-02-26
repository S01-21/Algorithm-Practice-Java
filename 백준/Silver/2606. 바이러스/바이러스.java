import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] parents;
	static int res;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 사람 수
		M = Integer.parseInt(br.readLine());	// 연결 정보 수 
		
		parents = new int[N+1];
		for (int i=1; i<= N; i++) {
			parents[i] = i;
		}
		
		for (int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		
		int first = findSet(1);
		for (int i=2; i<=N; i++) {
			if (first == findSet(i)) {
				res++;
			}
		}
		
		System.out.println(res);

	}
	static int findSet(int a) {
		if (parents[a] == a)	return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if (rootA == rootB)	return false;
		parents[rootB] = rootA;
		return true;
	}
	
}