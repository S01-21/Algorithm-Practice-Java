import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] cost;
	static int[][] D;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cost = new int[N+1][3];
		
		StringTokenizer st = null;
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		D = new int[N + 1][3];
		
		D[1][0] = cost[1][0];
		D[1][1] = cost[1][1];
		D[1][2] = cost[1][2];
		
		for (int i=2; i<=N; i++) {
			D[i][0] = Math.min(D[i-1][1], D[i-1][2]) + cost[i][0];
			D[i][1] = Math.min(D[i-1][0], D[i-1][2]) + cost[i][1];
			D[i][2] = Math.min(D[i-1][0], D[i-1][1]) + cost[i][2];
		}
		int tmp = Math.min(D[N][0], D[N][1]);
		System.out.println(Math.min(tmp, D[N][2]));
	}
}