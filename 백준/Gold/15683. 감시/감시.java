
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	static int[][] dist;
	static int N, M;
	static int mn = Integer.MAX_VALUE;
	static List<Pair> cctv = new ArrayList<>();
	
	static int calc() {	// 사각지대 카운트 
		int res = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (dist[i][j] == 0) res++;
			}
		}
		return res;
	}
	
	// 특정 방향으로 이동 
	static void move(int x, int y, int dir, boolean isCheck) {
		int nx = x;
		int ny = y;
		while (true) {
			nx += dx[dir];
			ny += dy[dir];
			
			// NxM 범위 밖이거나 벽 만나면 이동 종료 
			if (checkIdx(nx, ny))	break;
			if (map[nx][ny] == 6)	break;
			
			
			if (isCheck) dist[nx][ny]++;
			else {
				if (dist[nx][ny] > 0)	dist[nx][ny]--;
			}
			
		}
	}
	
	//백트래킹 함수 
	static void func(int cnt) {
		if (cnt == cctv.size()) {	//종료 조건은 모든 CCTV지점 돈 후 
			int tmp = calc();
			mn = Math.min(mn, tmp);
			return;
		}
		
		// cctv 위치좌표 가져오기 
		int x = cctv.get(cnt).x;
		int y = cctv.get(cnt).y;
		
		switch(map[x][y]) {
		case 1:
			for (int dir = 0; dir < 4; dir++) {	//상-우-하-좌 순서 
				move(x, y, dir, true);
				func(cnt + 1);
				move(x, y, dir, false);
			}
			break;
		case 2:
			for (int dir = 0; dir < 2; dir++) { //상하-좌우 순서 
				move(x, y, dir, true);
				move(x, y, dir+2, true);
				func(cnt + 1);
				move(x, y, dir, false);
				move(x, y, dir+2, false);
			}
			break;
		case 3:
			for (int dir = 0; dir < 4; dir++) {
				int dir90 = dir+1;
				if (dir90 == 4)	dir90 = 0;
				move(x, y, dir, true);
				move(x, y, dir90, true);
				func(cnt + 1);
				move(x, y, dir, false);
				move(x, y, dir90, false);
			}
			break;
		case 4:
			for (int dir = 0; dir < 4; dir++) {
				move(x, y, dir, true);
			}
			for (int dir = 0; dir< 4; dir++) {
				move(x, y, dir, false);
				func(cnt + 1);
				move(x, y, dir, true);
			}
			for (int dir = 0; dir < 4; dir++) {
				move(x, y, dir, false);
			}
			break;
		case 5:
			for (int dir = 0; dir < 4; dir++) {
				move(x, y, dir, true);
			}
			func(cnt + 1);
			for (int dir = 0; dir < 4; dir++) {
				move(x, y, dir, false);
			}
			break;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dist = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] !=0 && map[i][j] != 6) {
					cctv.add(new Pair(i,j));
					dist[i][j] = M*N;
				} else if (map[i][j] == 6){
					dist[i][j] = M*N;
				}
			}
		}
		func(0);
		System.out.println(mn);
	}

	
	
	public static boolean checkIdx(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}
}
class Pair{
	int x, y;
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
