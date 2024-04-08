
import java.util.*;
import java.io.*;

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N, M, K;
	static int res;
	static int[] dx = { -1, 1, 0, 0 }; // 상-하-좌-우
	static int[] dy = { 0, 0, -1, 1 };
	static PriorityQueue<Microbe> pq, pq2;
	static Microbe[][] map;

	static class Microbe implements Comparable<Microbe> {
		int x, y, c, d;

		public Microbe(int x, int y, int c, int d) {
			super();
			this.x = x;
			this.y = y;
			this.c = c; // 미생물 수
			this.d = d; // 이동 방향
		}

		@Override
		public int compareTo(Microbe o) {
			return Integer.compare(o.c, this.c);
		}

		@Override
		public String toString() {
			return "Microbe [x=" + x + ", y=" + y + ", c=" + c + ", d=" + d + "]";
		}
		
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // NxN map
			M = Integer.parseInt(st.nextToken()); // 격리 시간
			K = Integer.parseInt(st.nextToken()); // 최초 미생물 군집 개수
			

			pq = new PriorityQueue<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1;
				pq.offer(new Microbe(x, y, c, d));
			}

			for (int i = 0; i < M; i++) {
				func();
			}

			res = 0;
			while(!pq.isEmpty()) {
				res += pq.poll().c;
			}

			sb.append('#').append(tc).append(" ").append(res).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	private static void func() {
		pq2 = new PriorityQueue<>();
		map = new Microbe[N][N];
		while (!pq.isEmpty()) {
			// 이동방향으로 이동
			Microbe cur = pq.poll();
			int nx = cur.x + dx[cur.d];
			int ny = cur.y + dy[cur.d];

			// 약품에 닿았을 경우
			if (nx == 0 || nx == N - 1 || ny == 0 || ny == N - 1) {
				cur.c /= 2;
				if (cur.d == 0 || cur.d == 2) {
					cur.d++;
				} else if (cur.d == 1 || cur.d == 3) {
					cur.d--;
				} // 이동방향 반대로
				
				if (cur.c == 0) { // 미생물수 0 되면 군집 사라짐
					continue;
				} else {
					if (map[nx][ny] != null) {	// 이미 다른 군집이 이동했을 경우
						map[nx][ny].c += cur.c;
						continue;
					} else {
						map[nx][ny] = new Microbe(nx, ny, cur.c, cur.d);
						continue;
					}
				}
			} else {	// 약품 아닌 곳
				// 이미 다른 군집이 이동했을 경우
				if (map[nx][ny] != null) {
					map[nx][ny].c += cur.c;
					continue;
				} else {
					// 빈칸에 무사 이동할 경우
					map[nx][ny] = new Microbe(nx, ny, cur.c, cur.d);
				}
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j] != null) {
					pq2.offer(map[i][j]);
				}
			}
		}
		
		pq = pq2;
	}
}
