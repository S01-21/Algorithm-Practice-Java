import java.util.*;
import java.io.*;
// 실버 1. 경로 찾기 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] adjMatrix;
	static int[] dist;
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
		for (int i=0; i<N; i++) {
			dist = new int[N];
			dist[i] = 0;
			dfs(i);	// 모든 정점에서 시작 
			
			for (int j=0; j<N; j++) {
				if (dist[j] > 0) {	// 가는 길이가 양수인 경로 존재할 경우 
					sb.append(1).append(" ");
				} else {
					sb.append(0).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	private static void dfs(int num) {
		for (int i=0; i<N; i++) {
			if (num==i)	continue;
			if (dist[i] > 0)	continue;	// 방문체크 
			if (adjMatrix[num][i] == 1) {	// 경로존재 
				dist[i] = dist[num] +1 ;
				dfs(i);
			}
		}
	}
}