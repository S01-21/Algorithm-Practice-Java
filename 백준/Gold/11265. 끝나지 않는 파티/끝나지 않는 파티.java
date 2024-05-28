import java.util.*;
import java.io.*;

// 골드 5. 끝나지 않는 파티 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[][] floyd;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 파티장 크기 
		M = Integer.parseInt(st.nextToken());	// 손님 수 
		
		floyd = new int[N+1][N+1];	// i에서 j로 이동 시간 
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				floyd[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int k=1; k<=N; k++) {
			for (int i=1; i<=N; i++) {
				if (i==k)	continue;
				for (int j=1; j<=N; j++) {
					if (i==j)	continue;
					floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
				}
			}
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());	// 출발위치 
			int B = Integer.parseInt(st.nextToken());	// 목표위치 
			int C = Integer.parseInt(st.nextToken());	// 시간 
			
			if (floyd[A][B] <= C) {
				sb.append("Enjoy other party");
			} else {
				sb.append("Stay here");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
}