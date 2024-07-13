import java.util.*;
import java.io.*;

// 골드 4. 숨바꼭질 2
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K, cnt;
	static int[] dist;
	static int[] d = { -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치

		dist = new int[100001];
		Arrays.fill(dist, -1);

		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(N);
		dist[N] = 0;
		if (N == K)
			cnt++;
		else {
			while (!queue.isEmpty()) {
				int cur = queue.poll();
				if (cur == K)	cnt++;
				for (int dir = 0; dir < 3; dir++) {
					int nxt = 0;
					if (dir == 2)
						nxt = cur * 2;
					else
						nxt = cur + d[dir];
					if (nxt < 0 || nxt > 100000)	continue;
					if (dist[nxt] != -1 && dist[nxt] < dist[cur] + 1)	continue;
					queue.offer(nxt);
					dist[nxt] = dist[cur] + 1;
				}
			}
		}
		sb.append(dist[K]).append("\n").append(cnt);
		System.out.println(sb);
		br.close();
	}
}