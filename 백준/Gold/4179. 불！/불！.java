import java.util.*;
import java.io.*;

// 골드 3. 불!
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int R, C;
	static char[][] map;
	static int[][][] dist;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Pair{
		int x, y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());	// 세로 
		C = Integer.parseInt(st.nextToken());	// 가로 
		map = new char[R][C];
		dist = new int[R][C][2];	// [0]: 불, [1]: 지훈
		Queue<Pair> jq = new ArrayDeque<>();	// 지훈 
		Queue<Pair> fq = new ArrayDeque<>();	// 불 
		for (int i=0; i<R; i++) {
			String str = br.readLine();
			for (int j=0; j<C; j++) {
				for (int k=0; k<2; k++) {
					dist[i][j][k] = -1;		// -1로 초기화 
				}
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'J') {
					dist[i][j][1] = 0;
					jq.offer(new Pair(i, j));
				} else if (map[i][j] == 'F') {
					dist[i][j][0] = 0;		// 시작점 표시 
					fq.offer(new Pair(i, j));
				}
			}
		}
		
		
		while(!fq.isEmpty()) {
			Pair cur = fq.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (nx < 0 || nx >= R || ny < 0 || ny >= C)	continue;
				if (dist[nx][ny][0] >= 0 || map[nx][ny] == '#')	continue;
				dist[nx][ny][0] = dist[cur.x][cur.y][0] + 1;
				fq.offer(new Pair(nx, ny));
			}
		}
		
		boolean flag = false;
		while(!jq.isEmpty() && !flag) {
			Pair cur = jq.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
					sb.append(dist[cur.x][cur.y][1] + 1);
					flag = true;
					break;
				}
				if (dist[nx][ny][1] >= 0 || map[nx][ny] == '#')	continue;
				if (dist[cur.x][cur.y][1]+1 >= dist[nx][ny][0] && dist[nx][ny][0] != -1) continue;
				dist[nx][ny][1] = dist[cur.x][cur.y][1] + 1;
				jq.offer(new Pair(nx, ny));
			}
		}
		
		if (flag) {
			System.out.println(sb);
		} else {
			System.out.println("IMPOSSIBLE");
		}
		br.close();
	}
}