import java.util.*;
import java.io.*;

// 골드 3. 불우이웃돕기 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] adjMatrix;
	static int[] minEdge;
	static boolean[] vis;
	static int before;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adjMatrix = new int[N][N];
		
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<N; j++) {
				char c = str.charAt(j);
				if (c == '0') {
					adjMatrix[i][j] = 0;
				} else if (c >= 'a' && c <= 'z'){
					adjMatrix[i][j] = c - 'a' + 1;
				} else {
					adjMatrix[i][j] = c - 'A' + 27;
				}
				before += adjMatrix[i][j];
			}
		}
		vis = new boolean[N];
		minEdge = new int[N];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		int result = 0;
		minEdge[0] = 0;		
		
		int i = 0;
		for (i=0; i<N; i++) {	// 모든 정점 연결될때까지 반복 
			int min = Integer.MAX_VALUE;
			int minIdx = -1;
			
			for (int j = 0; j<N; j++) {
				if (vis[j])	continue;
				if (minEdge[j] < min) {
					min = minEdge[j];
					minIdx = j;
				}
			}
			
			if (minIdx == -1) {
				break;
			}
			
			vis[minIdx] = true;
			result += min;

			for (int j=0; j<N; j++) {
				if (vis[j])	continue;
				if (adjMatrix[minIdx][j] != 0 && minEdge[j] > adjMatrix[minIdx][j]) {
					minEdge[j] = adjMatrix[minIdx][j];
				}
				if (adjMatrix[j][minIdx] != 0 && minEdge[j] > adjMatrix[j][minIdx]) {
					minEdge[j] = adjMatrix[j][minIdx];
				}
			}
		}
		
		if (i != N) {
			System.out.println(-1);
		} else {
			System.out.println(before - result);
		}
		
		br.close();
	}
}