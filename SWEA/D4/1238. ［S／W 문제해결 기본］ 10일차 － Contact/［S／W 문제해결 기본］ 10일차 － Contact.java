import java.util.*;
import java.io.*;

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N, start;
	static ArrayList<Integer>[] students;
//	static boolean[] vis;
	static int[] dist;
	static int res;		// 가장 나중에 연락받을 사람 중 번호가 가장 큰사람
	static int maxDist;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 입력받을 데이터 개수
			start = Integer.parseInt(st.nextToken()); // 시작점(당번)

			students = new ArrayList[101];
//			vis = new boolean[101];
			dist = new int[101];
			
			for (int i = 0; i < 101; i++) {
				students[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				students[from].add(to);
			}

			Arrays.fill(dist, -1);
			
			// 시작점(당번)부터 탐색시작
			maxDist = -1;
			res = -1;
//			func(start, 0);
			bfs(start);
			
			int tmp = dist[0];
			for (int i=1; i<101; i++) {
				if (tmp <= dist[i]) {
					res = i;
					tmp = dist[i];
				}
			}
			sb.append(res).append("\n");
		}

		System.out.println(sb);
	}
	static void bfs(int st) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(st);
		dist[st] = 0;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for (int nxt : students[cur]) {
				if (dist[nxt] >= 0) continue;
				dist[nxt] = dist[cur] +1;
				queue.offer(nxt);
			}
		}
	}

}