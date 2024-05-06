import java.util.*;
import java.io.*;

// 골드 5. 회장뽑기 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, minScore, cnt;
	static int[][] dist;
	static int[] score;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dist = new int[N+1][N+1];
		score = new int[N+1];
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				dist[i][j] = 100000;
			}
		}
		
		StringTokenizer st = null;
		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == -1 && b == -1)	break;
			dist[a][b] = dist[b][a] = 1;
		}
		
		
		for (int k=1; k<=N; k++) {
			for (int i=1; i<=N; i++) {	// 출발지 
				for (int j=1; j<=N; j++) {
					if (i==k || j==k || i==j)	continue;
					if (dist[i][j] > dist[i][k] + dist[k][j]) {	
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		
		minScore = 100000;
		list = new ArrayList<>();	// 회장 후보 리스트 
		for (int i=1; i<=N; i++) {
			int max = -1;
			for (int j=1; j<=N; j++) {
				if (i==j)	continue;
				if (dist[i][j] > max) {
					max = dist[i][j];
				}
			}
			score[i] = max;
			minScore = Math.min(minScore, max);
		}
		
		for (int i=1; i<=N; i++) {
			if (score[i] == minScore) {
				list.add(i);
				cnt++;
			}
		}
		sb.append(minScore).append(" ").append(cnt).append("\n");
		for (int n : list) {
			sb.append(n).append(" ");
		}
		System.out.println(sb);
		br.close();
	}
}