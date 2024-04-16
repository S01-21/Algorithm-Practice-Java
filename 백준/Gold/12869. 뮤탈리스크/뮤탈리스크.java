import java.util.*;
import java.io.*;

// 골드 4. 뮤탈리스크 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][][] dp;
	static int[] svc;
	static int[][] attackOrder = {{9,3,1}, {9,1,3}, {3,9,1}, {3,1,9}, {1,9,3}, {1,3,9}};
	static class Svc{
		int p1, p2, p3;
		int cnt;
		public Svc(int p1, int p2, int p3, int cnt) {
			super();
			this.p1 = p1;
			this.p2 = p2;
			this.p3 = p3;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		svc = new int[3];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			svc[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[svc[0]+1][svc[1]+1][svc[2]+1];
		dp[svc[0]][svc[1]][svc[2]] = 0;
		int res = bfs(new Svc(svc[0], svc[1], svc[2], dp[svc[0]][svc[1]][svc[2]]));
		
		System.out.println(res);
		br.close();
	}
	
	private static int bfs(Svc start) {
		Queue<Svc> queue = new ArrayDeque<>();
		queue.offer(start);
		while (!queue.isEmpty()) {
			Svc cur = queue.poll();
			
			if (cur.p1 == 0 && cur.p2 == 0 && cur.p3 == 0) {
				return cur.cnt;
			}
			for (int d=0; d<6; d++) {
				int[] nxt = attackOrder[d];		// 공격 순서 
				int p1 = cur.p1 - nxt[0] > 0 ? cur.p1 - nxt[0] : 0;
				int p2 = cur.p2 - nxt[1] > 0 ? cur.p2 - nxt[1] : 0;
				int p3 = cur.p3 - nxt[2] > 0 ? cur.p3 - nxt[2] : 0;
				if (dp[p1][p2][p3] == 0) {	// 바뀐 적 없는(방문한적 없는) 경우에만 
					dp[p1][p2][p3] = cur.cnt + 1;
					queue.offer(new Svc(p1, p2, p3, dp[p1][p2][p3]));
				}
			}
		}
		return -1;
	}
}