import java.util.*;
import java.io.*;

public class Main {
	static int R, C, res;
	static char[][] map, tmpMap;
	static boolean[][] alive;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		tmpMap = new char[R][C];
		alive = new boolean[R][C];
		for (int i = 0 ; i < R; i++) {
			String tmp = br.readLine();
			for (int j = 0 ; j <C; j++) {
				map[i][j] = tmp.charAt(j);
				tmpMap[i][j] = map[i][j];
			}
		}
		
		for (int i = 0 ; i <R; i++) {
			for (int j = 0 ; j <C; j++) {
				if (map[i][j] == 'X' && check(i, j)) {
					tmpMap[i][j] = '.';
				}
			}
		}
		
		int minX = R, maxX = 0, minY = C, maxY = 0;
		for (int i  = 0 ; i < R; i++) {
			for (int j = 0 ; j < C; j++) {
				if (tmpMap[i][j] == 'X') {
					minX = Math.min(minX, i);
					maxX = Math.max(maxX, i);
					minY = Math.min(minY, j);
					maxY = Math.max(maxY, j);
				}
			}
		}
		for (int i = minX; i <= maxX; i++) {
			for (int j =  minY; j <= maxY; j++) {
				sb.append(tmpMap[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
	private static boolean check(int x, int y) {
		int cnt = 0;
		for (int dir = 0; dir <4 ; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == '.')	cnt++;
		}
		if (cnt >= 3)	return true;
		return false;
	}
}
