import java.util.*;
import java.io.*;

// 별자리 만들기 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static double[][] adjMatrix;
	static double[] minEdge;
	static boolean[] vis;
	static Node[] stars;
	static class Node{
		double x, y;

		public Node(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stars = new Node[N];
		StringTokenizer st = null;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			double a = Double.parseDouble(st.nextToken());
			double b = Double.parseDouble(st.nextToken());
			stars[i] = new Node(a, b);
		}
		
		// 인접행렬
		adjMatrix = new double[N][N];
		for (int i=0; i<N; i++) {
			for (int j=i+1; j<N; j++) {
				if (i == j)	continue;
				double dist = Math.sqrt(Math.pow(stars[i].x - stars[j].x, 2) + Math.pow(stars[i].y - stars[j].y, 2));
				adjMatrix[i][j] = adjMatrix[j][i] = dist;
			}
		}
		
		minEdge = new double[N];
		vis = new boolean[N];
		Arrays.fill(minEdge, Double.MAX_VALUE);
		
		minEdge[0] = 0.0;
		double result = 0.0;
		int cnt = 0;		// 트리정점에 추가된 정점 수
		for (int i=0; i<N; i++) {	// N개 정점 처리 시 종료
			
			// 비트리 정점 중 최소비용 정점 선택 
			double min = Double.MAX_VALUE;
			int minIdx = -1;
			for (int j=0; j<N; j++) {
				if (vis[j])	continue;
				if (min > minEdge[j]) {
					min = minEdge[j];
					minIdx = j;
				}
			}
			
			// 트리정점에 추가
			vis[minIdx] = true;
			result += min;
			
			// 추가된 트리정점에서 연결했을 때 기존 최소비용보다 작아지는 애들 갱신
			for (int j=0; j<N; j++) {
				if (vis[j])	continue;
				if (adjMatrix[minIdx][j] == 0)	continue;	// 간선 없으면 패스 (자신일 경우)
				if (minEdge[j] > adjMatrix[minIdx][j]) {
					minEdge[j] = adjMatrix[minIdx][j];
				}
			}
			
		}
		System.out.printf("%.2f", result);
	}
}