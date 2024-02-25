import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static ArrayList<Integer>[] course;
	static int[] cnt;
	static int[] minCnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		course = new ArrayList[N + 1];
		cnt = new int[N + 1];
		minCnt = new int[N + 1];
		for (int i=0; i<=N; i++) {
			course[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			course[a].add(b);
			cnt[b]++;
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i=1; i<=N; i++) {
			if (cnt[i] == 0) {
				queue.offer(i);
				minCnt[i] = 1;
			}
		}
		

		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i=0; i < course[cur].size(); i++) {
				int nxt = course[cur].get(i);
				cnt[nxt]--;
				
				if (cnt[nxt] == 0) {
					queue.offer(nxt);
					minCnt[nxt] = minCnt[cur] + 1;
				}
			}
		}
		
		for (int i=1; i<=N; i++) {
			sb.append(minCnt[i]).append(" ");
		}
		System.out.println(sb);
	}
}