import java.util.*;
import java.io.*;

// 골드 5. 상범 빌딩 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int L, R, C, res;
	static char[][][] map;
	static int[][][] dist;
	static int[] dx = {-1,1,0,0,0,0};
	static int[] dy = {0,0,-1,1,0,0};
	static int[] dz = {0,0,0,0,-1,1};
	static Queue<Pair> queue;
	static class Pair{
		int x, y,z;
		public Pair(int x, int y, int z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());	// 층 
			R = Integer.parseInt(st.nextToken());	// 행 
			C = Integer.parseInt(st.nextToken());	// 열 
			if (L == 0 && R == 0 && C == 0)	break;
			
			map = new char[R][C][L];
			dist = new int[R][C][L];
			queue = new ArrayDeque<>();
			for (int l = 0; l < L; l++) {
				for (int r=0; r<R; r++) {
					String str = br.readLine();
					for (int c=0; c<C; c++) {
						map[r][c][l] = str.charAt(c);
						if (map[r][c][l] == 'S') {
							queue.offer(new Pair(r, c, l));
							dist[r][c][l] = 0;
						} else {
							dist[r][c][l] = -1;
						}
					}
				}
				br.readLine();	// 빈칸 한줄 
			}
			res = -1;
			bfs();
			
			if (res == -1) {
				sb.append("Trapped!");
			} else {
				sb.append("Escaped in ").append(res).append(" minute(s).");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	private static void bfs() {
		while(!queue.isEmpty()) {
			Pair cur = queue.poll();
			
			for (int dir = 0; dir < 6; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				int nz = cur.z + dz[dir];
				if (isOut(nx, ny, nz))	continue;
				if (dist[nx][ny][nz] >= 0 || map[nx][ny][nz] == '#')	continue;
				if (map[nx][ny][nz] == 'E') {
					res = dist[cur.x][cur.y][cur.z]+ 1;
					return;
				}
				dist[nx][ny][nz] = dist[cur.x][cur.y][cur.z]+ 1;
				queue.offer(new Pair(nx, ny, nz));
			}
		}
	}
	private static boolean isOut(int x, int y, int z) {
		return x < 0 || x >= R || y < 0 || y >= C || z < 0 || z >=L;
	}
}