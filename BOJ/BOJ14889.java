import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean [] visited;
	static int[][] input;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		input = new int[N][N];
		visited = new boolean[N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		 
		func(0, 0);
		System.out.println(min);
	}

	static void func(int st, int n) {
		if (n == N/2) {	//팀 조합 끝나면 최소 능력치 차이 비교 
			compareDiff();
			return;
		}
		for (int i=st ; i<N; i++) {
			if (!visited[i]) { 
				visited[i] = true;
				func(i+1, n+1);
				visited[i] = false;
			}
		}
	}
	static void compareDiff() {
		int start_sum = 0;  //방문한 사람 start팀
		int link_sum = 0;  //방문하지 않은 사람 link팀
		
		for (int i=0; i<N-1; i++) {
			for (int j=i+1; j<N; j++) {
				if (visited[i] == true && visited[j] == true) {
					start_sum += input[i][j];
					start_sum += input[j][i];
				}
				else if (visited[i] == false && visited[j]== false) {
					link_sum += input[i][j];
					link_sum += input[j][i];
				}
			}
		}
		
		int diff = Math.abs(start_sum - link_sum);
		if (diff == 0) {  //0은 최솟값이니까 바로 출력 후 종료
			System.out.println(0);
			System.exit(0);
		}
		min = Math.min(min, diff);
		
	}
}
