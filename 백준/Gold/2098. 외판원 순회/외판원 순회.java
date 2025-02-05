import java.util.*;
import java.io.*;

public class Main {
	static int N, res;
	static int[][] adjMatrix;
	static int[][] dp;
	static final int INF = 1_000_000_000;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		adjMatrix = new int[N][N];
		dp = new int[N][1<<N];		//dp[현재도시][방문상태]
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		System.out.println(func(0, 1));
		br.close();
	}
	private static int func(int cur, int vis) {
		
		// 모두 방문했다면 출발점으로 다시 돌아갈 수 있는지 확인
		if (vis == (1<<N) - 1) {
			return (adjMatrix[cur][0] != 0) ? adjMatrix[cur][0] : INF;
		}
		
		// 메모이제이션 한 값 사용
		if (dp[cur][vis] != -1)		return dp[cur][vis];
		
		// 모든 경로 탐색하며 최솟값 찾아야하니까 초기값 크게 세팅
		dp[cur][vis] = INF;
		
		for (int nxt = 0; nxt < N; nxt++) {
			
			// 이미 방문했거나 길이 없으면 패스
			if ((vis & (1 << nxt)) != 0 || adjMatrix[cur][nxt] == 0)	continue;
			
			// 다음 도시 방문했을 때의 비용 계산
			int newCost = adjMatrix[cur][nxt] + func(nxt, vis | (1 << nxt));
			
			dp[cur][vis] = Math.min(dp[cur][vis], newCost);
		}
		
		return dp[cur][vis];
	}
}
