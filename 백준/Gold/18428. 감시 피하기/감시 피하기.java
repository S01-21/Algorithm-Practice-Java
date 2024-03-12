import java.util.*;
import java.io.*;
// 감시 피하기
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static ArrayList<Pair> teachers, blanks;
	static char[][] map;
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
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		teachers = new ArrayList<>();
		blanks = new ArrayList<>();
		map = new char[N][N];
		StringTokenizer st = null;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (map[i][j] == 'T') {
					teachers.add(new Pair(i,j));
				} else if (map[i][j] == 'X') {
					blanks.add(new Pair(i,j));
				}
			}
		}
		
		setObstacle(0);	// 장애물 설치
		System.out.println("NO");
	}
	private static void setObstacle(int cnt) {
		if (cnt == 3) {
			if (watch()) {
				System.out.println("YES");
				System.exit(0);
			}
			return;
		}
		
		for (int i=0; i<blanks.size(); i++) {
			Pair tmp = blanks.get(i);
			if (map[tmp.x][tmp.y] != 'O') {
				map[tmp.x][tmp.y] = 'O';
				setObstacle(cnt + 1);
				map[tmp.x][tmp.y] = 'X'; 
			}
		}
	}
	private static boolean watch() {
		for (int i=0; i<teachers.size(); i++) {	// 모든 선생님 위치에서 감시
			Pair cur = teachers.get(i);
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x;
				int ny = cur.y;
				while(true) {
					nx += dx[dir];
					ny += dy[dir];
					if (nx < 0 || nx >= N  || ny < 0 || ny >= N)	break;
					if (map[nx][ny] == 'O')	break;
					if (map[nx][ny] == 'S') {
						return false;
					}
				}
			}
		}
		return true;
	}
}