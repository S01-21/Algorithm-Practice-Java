import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 미로 이동하기
public class Main {

	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[][] maze;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new int[N][M];
		int[][] gift = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				maze[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		gift[0][0] = maze[0][0];
		for (int i=1; i<N; i++) {
			gift[i][0] = gift[i-1][0] + maze[i][0];
		}
		for (int j=1; j<M; j++) {
			gift[0][j] = gift[0][j-1] + maze[0][j];
		}
		
		for (int i=1; i<N; i++) {
			for (int j=1; j<M; j++) {
				int tmp = Math.max(gift[i-1][j], gift[i][j-1]);
				gift[i][j] = Math.max(tmp, gift[i-1][j-1]) + maze[i][j];
			}
		}
		
		
		
		System.out.println(gift[N-1][M-1]);
	}

}