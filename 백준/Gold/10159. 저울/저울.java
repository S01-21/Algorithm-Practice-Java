import java.util.*;
import java.io.*;

// 골드 4. 저울 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static boolean[][] stuff;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		stuff = new boolean[N+1][N+1];
		
		StringTokenizer st = null;
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			stuff[a][b] = true;
		}
		
		for (int k=1; k <=N; k++) {
			for (int i=1; i<=N; i++) {
				for (int j=1; j<=N; j++) {
					if (stuff[i][k] && stuff[k][j]) {
						stuff[i][j] = true;
					}
				}
			}
		}
		
		for (int i=1; i<=N; i++) {
			int cnt = 0;
			for (int j=1; j<=N; j++) {
				if (!stuff[i][j] && !stuff[j][i]) {
					cnt++;
				}
			}
			sb.append(cnt-1).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
}