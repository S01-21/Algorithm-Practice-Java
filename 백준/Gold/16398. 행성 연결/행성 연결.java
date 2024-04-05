import java.util.*;
import java.io.*;

// 골드4. 행성 연결 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] adjMatrix;
	static int[] minEdge;
	static boolean[] vis;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adjMatrix = new int[N][N];
		StringTokenizer st = null;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		minEdge = new int[N];
		vis = new boolean[N];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[0] = 0;	// 임의의 시작정점 0
		int cnt = 0;
		long res = 0;
		for (int i=0; i<N; i++) {
			
			int min = Integer.MAX_VALUE;
			int minIdx = -1;
			
			for (int j=0; j<N; j++) {
				if (vis[j])	continue;
				if (min > minEdge[j]) {
					min = minEdge[j];
					minIdx = j;
				}
			}
			
			vis[minIdx] = true;
			res += min;
			if (++cnt == N) {
				break;
			}
			
			
			for (int j=0; j<N; j++) {
				if (vis[j])	continue;
				if (minEdge[j] > adjMatrix[minIdx][j]) {
					minEdge[j] = adjMatrix[minIdx][j];
				}
			}
			
		}
		
		System.out.println(res);
		
	}
}