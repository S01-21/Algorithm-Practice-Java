import java.util.*;
import java.io.*;

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int res;
	static int max;
	static int coreConn, coreTotal;
	static int[][] map;
	static boolean[][] vis;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Core{
		int x, y;
		public Core(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static ArrayList<Core> cores;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			coreConn = 0;
			coreTotal = 0;
			cores = new ArrayList<>();
			StringTokenizer st = null;
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {	// core
						if (i==0 || i == N-1 || j == 0 || j == N-1) {
							coreConn++;	// 가장자리는 core 카운트만 증가시키고 저장안하고 패스
							coreTotal++;
							continue;
						}
						coreTotal++;
						cores.add(new Core(i, j));
					}
				}
			}
			
			res = Integer.MAX_VALUE;	// 전선길이의 합 (최소)
			max = -1;		// 전원 연결된 Core 개수
			vis = new boolean[N][N];
			func(0, coreConn, 0);

			
			sb.append("#").append(tc).append(" ").append(res).append("\n");
		}
		

		System.out.println(sb);
	}
	private static void func(int idx, int connectCount, int length) {
		if (idx == (coreTotal - coreConn)) {	
			if (connectCount > max) {
				max = connectCount;
				res = length;
			} else if (connectCount == max) {
				res = Math.min(length, res);
			}
			return;
		}
		
		Core cur = cores.get(idx);
		int x = cur.x;
		int y = cur.y;
		
		for (int dir = 0; dir < 4; dir++) {
			int nx = x;
			int ny = y;
			
			int len = 0;
			while(true) {
				nx += dx[dir];
				ny += dy[dir];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {	// 가장자리 도착 (연결 완료)
					func(idx + 1, connectCount + 1, length + len);
					break;
				}
				if (vis[nx][ny])	break;	// 다른 전선과 교차될 경우
				if (map[nx][ny] == 1)	break;	// 다른 core 있을 경우
				
				len++;
				vis[nx][ny] = true;
			}
			
			// vis배열 원상복구
			nx = x;
			ny = y;
			for (int k = 0; k < len; k++) {
				nx += dx[dir];
				ny += dy[dir];
				vis[nx][ny] = false;
			}
		}
		
		func(idx + 1, connectCount, length);	// 연결안할 경우 
	}
}