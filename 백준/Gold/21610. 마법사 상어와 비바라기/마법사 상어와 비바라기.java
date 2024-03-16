import java.util.*;
import java.io.*;

// 마법사 상어와 비바라기 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, d, s;
	static int[] dx = {0,-1,-1,-1,0,1,1,1};
	static int[] dy = {-1,-1,0,1,1,1,0,-1};
	static int[][] map;
	static boolean[][] check;
	static ArrayList<Cloud> clouds;
	static class Cloud{
		int x, y, water;
		public Cloud(int x, int y, int water) {
			super();
			this.x = x;
			this.y = y;
			this.water = water;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		clouds = new ArrayList<>();
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 초기 구름 저장 
		for (int i=N-1; i<=N; i++) {
			for (int j=1; j<=2; j++) {
				clouds.add(new Cloud(i, j, map[i][j]));
			}
		}
		
		for (int i=0; i<M; i++) {	// M번 이동 
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken())-1;
			s = Integer.parseInt(st.nextToken());
			check = new boolean[N+1][N+1];
			
			moveCloud();	// 1번, 2번 과정 
			
			checkDiagonal();	// 4번 과정 
			
			clouds = new ArrayList<>();	// 3번 과정 (구름초기화)
			
			makeCloud();	// 5번 과정 
			
		}
		
		// M번 이동 후 바구니에 들어있는 물의 양 계산 
		int sum = 0;
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				sum += map[i][j];	
			}
		}
		System.out.println(sum);
	}
	
	private static void moveCloud() {
		for (Cloud cur: clouds) {
			int nx = cur.x;
			int ny = cur.y;
			for (int j=0; j<s; j++) {	// d방향으로 s칸 이동 
				nx += dx[d];
				ny += dy[d];
				if (nx <= 0) {
					nx = N;
				}
				if (ny <=0) {
					ny = N;
				}
				if (nx > N) {
					nx = 1;
				}
				if (ny > N) {
					ny = 1;
				}
			}
			cur.x = nx;
			cur.y = ny;
			map[nx][ny]++;	// 구름이 있는 칸의 물의 양 1 증가 
			cur.water = map[nx][ny];
			check[cur.x][cur.y] = true;	// 5번 과정에서 제외하기 위해 표시 
		}
	}

	private static void checkDiagonal() {
		int[][] count = new int[N+1][N+1];
		for (Cloud cur : clouds) {
			int tmp = 0;
			for (int dir = 1; dir < 8; dir += 2) {	
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (nx <= 0 || nx > N || ny <= 0 || ny > N) continue;
				if (map[nx][ny] <= 0)	continue;
				tmp++;	// 대각선에 물이 있는 바구니 수 카운트 
			}
			count[cur.x][cur.y] = tmp; 
		}
		
		for (Cloud cur : clouds) {
			map[cur.x][cur.y] += count[cur.x][cur.y];	// 카운트한 만큼 물 양 증가 
		}
		
	}
	private static void makeCloud() {
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				if (check[i][j])	continue;	// 2에 해당하는 구름이면 패스 
				if (map[i][j] >= 2) {	// 물의 양이 2이상인 모든칸에 구름 생성 
					map[i][j]-=2;	// 물의 양 2 감소 
					clouds.add(new Cloud(i, j, map[i][j]));
				}
			}
		}
	}
}