import java.util.*;
import java.io.*;

public class Main {
	static int N, M, res;
	static int rX, rY, bX, bY;
	static char[][] map;
	static int[][] dist;
	static boolean[][][][] vis;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Pos{
		int rx, ry, bx, by, move;
		public Pos(int rx, int ry, int bx, int by, int move) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.move = move;
		}
	}
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		vis = new boolean[N][M][N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'R') {
					rX = i;
					rY = j;
				} else if (map[i][j] == 'B') {
					bX = i;
					bY = j;
				}
			}
		}
		
		System.out.println(bfs());
		br.close();
	}
	private static int bfs() {
		Queue<Pos> queue = new ArrayDeque<>();
		vis[rX][rY][bX][bY] = true;
		queue.offer(new Pos(rX, rY, bX, bY, 0));
		
		while(!queue.isEmpty()) {
			Pos cur = queue.poll();
			
			if (cur.move >= 10)	return -1;	// 10번 초과로 움직이면 실패
			for (int dir = 0; dir < 4; dir++) {
				int nrx = cur.rx;
				int nry = cur.ry;
				int nbx = cur.bx;
				int nby = cur.by;
				
				// 벽 전까지, 혹은 구멍까지 이동
				int rCnt = 0;
				while (map[dx[dir] + nrx][dy[dir] + nry] != '#' && map[nrx][nry] != 'O'){
					nrx += dx[dir];
					nry += dy[dir];
					rCnt++;
				}
				int bCnt = 0;
				while (map[dx[dir] + nbx][dy[dir] + nby] != '#' && map[nbx][nby] != 'O'){
					nbx += dx[dir];
					nby += dy[dir];
					bCnt++;
				}
				
				if (map[nbx][nby] == 'O')	continue;	// 파랑이 구멍에 빠지는 경우는 생각X
				if (map[nrx][nry] == 'O')	return cur.move + 1;	// 빨강이 빠지면 종료
				
				if (nrx == nbx && nry == nby) {	// 빨강, 파랑 같은 위치에 만나면 늦게 온 애를 뒤로 밀기
					if (rCnt > bCnt) {
						nrx -= dx[dir];
						nry -= dy[dir];
					} else {
						nbx -= dx[dir];
						nby -= dy[dir];
					}
				}
				
				if (vis[nrx][nry][nbx][nby])	continue;	// 방문체크
				vis[nrx][nry][nbx][nby] = true;
				queue.offer(new Pos(nrx, nry, nbx, nby, cur.move + 1));
			}
		}
		return -1;
	}
}
