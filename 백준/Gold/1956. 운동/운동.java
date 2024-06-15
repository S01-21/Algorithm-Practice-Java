import java.util.*;
import java.io.*;

// 골드 4. 운동 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int V, E, res;
	static int[][] floyd;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());	// 마을 개수 
		E = Integer.parseInt(st.nextToken());	// 도로 개수 
		floyd = new int[V+1][V+1];
		for (int i=1; i<=V; i++) {
			for (int j=1; j<=V; j++) {
				floyd[i][j] = 100_000_000;
			}
		}
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	// from 
			int b = Integer.parseInt(st.nextToken());	// to
			int c = Integer.parseInt(st.nextToken());	// dist
			floyd[a][b] = c;
		}
		
		for (int k=1; k<=V; k++) {
			for (int i=1; i<=V; i++) {
//				if (i==k)	continue;
				for (int j=1; j<=V; j++) {
//					if (i==j || k==j) continue;
					floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
				}
			}
		}
		
		res = Integer.MAX_VALUE;
		for (int i=1; i<=V; i++) {
			if (floyd[i][i] == 100_000_000)	continue;
			res = Math.min(res, floyd[i][i]);
		}
		if (res == Integer.MAX_VALUE) {
			res = -1;
		}
		System.out.println(res);
		br.close();
	}
}