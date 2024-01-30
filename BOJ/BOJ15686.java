import java.io.*;
import java.util.*;

public class Main {
	static int[][] board;
	static int N, M;
	static List<Pair> chicken = new ArrayList<>();
	static List<Pair> home = new ArrayList<>();
	static int mn = Integer.MAX_VALUE;
	static int result = Integer.MAX_VALUE;	// 도시의 치킨거리 중 최솟값 
	static boolean[] pick;
	
	static void func(int start, int cnt) {
		if (cnt == M) {	// 치킨집 M개 고르면 집마다 치킨거리 계산 
			int sum = 0;	// 도시의 치킨 거리 
			for (int i = 0; i<home.size(); i++) {
				int tmp = Integer.MAX_VALUE;
				for (int j = 0; j<chicken.size(); j++) {
					if (pick[j]) {
						int diff = Math.abs(home.get(i).x - chicken.get(j).x) 
								+ Math.abs(home.get(i).y - chicken.get(j).y);
						tmp = Math.min(tmp, diff);	// 해당 집의 치킨 거리 
					}
				}
				sum += tmp;
			}
			result = Math.min(result, sum);
			return;
		}
		
		
		// 치킨집 중 M개 고르기 
		for (int i = start; i < chicken.size(); i++	) {
			pick[i] = true;
			func(i + 1, cnt + 1);
			pick[i] = false;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 2) {
					chicken.add(new Pair(i, j));
				} else if (board[i][j] == 1) {
					home.add(new Pair(i, j));
				}
			}
		}
		pick = new boolean[chicken.size()];
		
		func(0, 0);
		System.out.println(result);
	}
	
}
class Pair{
	int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
