import java.util.*;
import java.io.*;

// 핀볼 게임 
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] map;
	static int res;
	static int[][] worm;
	static int[] dx = {-1,1,0,0};	// 상하좌우 (0,1,2,3)
	static int[] dy = {0,0,-1,1};
	static int[][] dd = {{0,1,2,3}, {1, 3, 0, 2}, {3, 0, 1, 2}, {2, 0, 3, 1}, {1, 2, 3, 0}, {1,0,3,2}};	// 1~5번 블록 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		StringTokenizer st = null;
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N+2][N+2];
			worm = new int[11][4];
			for (int i=1; i<= N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j=1; j<=N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] >= 6) {	// 웜홀
						int n = map[i][j];
						if (worm[n][0] == 0) {	// 첫번째 웜홀인 경우 
							worm[n][0] = i;
							worm[n][1] = j;
						} else {	// 두번째 웜홀인 경우 
							worm[n][2] = i;
							worm[n][3] = j;
						}
					}
				}
			}
			// 가장자리 칸 5번 블록으로 채우기 
			for (int i=0; i < N+2; i++) {
				map[i][0] = 5;
				map[i][N+1] = 5;
			}
			for (int j=0; j < N+2; j++) {
				map[0][j] = 5;
				map[N+1][j] = 5;
			}
			
			res = Integer.MIN_VALUE;
			
			// 모든 지점에서 탐색 시작 
			for (int i=1; i<= N; i++) {
				for (int j=1; j<=N; j++) {	// (i, j): 출발 위치 
					if (map[i][j] != 0)	continue;	// 빈칸에서만 시작 가능 
					for (int dir = 0; dir < 4; dir++) {	// 4방탐색 
						int nx = i;
						int ny = j;
						int nd = dir;
						int score = 0;
						while(true) {
							nx += dx[nd];
							ny += dy[nd];
							int n = map[nx][ny];
							
							// 원위치 or 블랙홀
							if ((nx == i && ny == j) || n == -1)	break;
							
							if (n >= 6) {	// 웜홀
								if (nx == worm[n][0] && ny == worm[n][1]) {	// 첫번째 웜홀
									nx = worm[n][2];
									ny = worm[n][3];
								} else {	// 두번째 웜홀
									nx = worm[n][0];
									ny = worm[n][1];
								}
							} else {	// 블록 or 빈칸
								nd = dd[n][nd];	// 방향 전환
								if (n != 0) score++;
							}
						}
						res = Math.max(res, score);
					}
				}
			}
			
			
			sb.append("#").append(tc).append(" ").append(res).append("\n");
		}
		
		System.out.println(sb);
	}
}