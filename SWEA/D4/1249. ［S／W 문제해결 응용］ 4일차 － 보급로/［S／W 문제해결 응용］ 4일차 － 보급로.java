import java.util.*;
import java.io.*;

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int res;
	static int[][] map;
	static boolean[][] vis;
	static int[][] minDist;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T ;tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			minDist = new int[N][N];
			vis = new boolean[N][N];
			
			
			for (int i=0; i<N; i++) {
				String str = br.readLine();
				for (int j=0 ;j<N; j++) {
					map[i][j] = str.charAt(j)-'0';
				}
			}
			
			
			dijkstra();
			
			
			sb.append("#").append(tc).append(" ").append(res).append("\n");
		}
		
		System.out.println(sb);
	}
	private static void dijkstra() {
		for (int i=0; i<N; i++) {
			Arrays.fill(minDist[i], Integer.MAX_VALUE);
		}
		minDist[0][0] = 0;
		vis[0][0] = true;
		
		for (int dir = 0; dir < 4; dir++) {
			int nx = dx[dir];
			int ny = dy[dir];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)	continue;
			if (vis[nx][ny])	continue;
			minDist[nx][ny] = map[nx][ny];
		}
		
		for (int i=0; i < N*N; i++) {
			int min = Integer.MAX_VALUE;
			int minX = -1;
			int minY = -1;
			
			for (int j=0; j<N; j++) {
				for (int k=0; k<N; k++) {
					if (vis[j][k])	continue;
					if (minDist[j][k] < min) {
						min = minDist[j][k];
						minX = j;
						minY = k;
					}
				}
			}
			
			vis[minX][minY] = true;
			
			if (minX == N-1 && minY == N-1)	break;
			
			for (int dir = 0 ; dir < 4; dir++) {
				int nx = minX + dx[dir];
				int ny = minY + dy[dir];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)	continue;
				if (vis[nx][ny])	continue;
				if (minDist[nx][ny] > minDist[minX][minY] + map[nx][ny]) {
					minDist[nx][ny] = minDist[minX][minY] + map[nx][ny];
				}
			}
		}
		res = minDist[N-1][N-1];
	}
}