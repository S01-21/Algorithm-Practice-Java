import java.util.*;
import java.io.*;

public class Solution {

	static StringBuilder sb = new StringBuilder();
	static int N;
	static double E;
	static int[][] island; // 각 섬의 x좌표, y좌표
	static long[][] dist;
	static double[] minEdge;
	static boolean[] vis;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine()); // 섬의 개수

			island = new int[N][2];
			dist = new long[N][N]; // 섬끼리의 거리
			minEdge = new double[N];
			vis = new boolean[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				island[i][0] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				island[i][1] = Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(br.readLine()); // 환경부담 세율 실수

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;
					long d = (long) (Math.pow(island[i][0] - island[j][0], 2)
							+ Math.pow(island[i][1] - island[j][1], 2));
					dist[i][j] = dist[j][i] = d;
				}
			}

			Arrays.fill(minEdge, Double.MAX_VALUE);
			double result = 0.0;
			minEdge[0] = 0.0;

			for (int c = 0; c < N; c++) {

				// 비트리 중 최소비용 찾기
				int minIdx = -1;
				double min = Double.MAX_VALUE;
				for (int i = 0; i < N; i++) {
					if (vis[i])
						continue;
					if (min > minEdge[i]) {
						min = minEdge[i];
						minIdx = i;
					}
				}

				result += min;
				vis[minIdx] = true;

				for (int i = 0; i < N; i++) { // 비트리에서 갈 수 있는 정점 중 최소 거리 갱신
					if (vis[i]) continue;
					if (i == minIdx) continue;
					if (dist[minIdx][i] < minEdge[i]) {
						minEdge[i] = dist[minIdx][i];
					}
				}
			}

			sb.append(Math.round(result * E)).append("\n");
		}
		System.out.println(sb);
	}

}