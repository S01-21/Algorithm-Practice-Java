import java.util.*;
import java.io.*;

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parents = new int[N+1];
			
			
			makeSet();
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				switch(op) {
				case 0:	// 합집합
					union(a, b);
					break;
				case 1:	// 같은집합 확인
					boolean flag = (findSet(a) == findSet(b));
					if (flag) {
						sb.append(1);
					} else {
						sb.append(0);
					}
					break;
				}
			}
			
			sb.append("\n");
		}
		System.out.println(sb);
	}
	private static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		
		if (rootA == rootB) {
			return false;
		}
		
		parents[rootA] = rootB;
		return true;
	}
	static int findSet(int a) {	// 집합 대표자 찾기
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = findSet(parents[a]);
	}
	static void makeSet() {
		for (int i=0; i<N; i++) {
			parents[i] = i;
		}
	}
}