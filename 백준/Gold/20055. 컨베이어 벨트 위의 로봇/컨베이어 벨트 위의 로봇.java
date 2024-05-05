import java.util.*;
import java.io.*;

// 골드 5. 컨베이어 벨트 위의 로봇 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K;
	static int[] belt;
	static boolean[] robot;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belt = new int[2*N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<2*N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		robot = new boolean[N];
		int level = 1;
		
		while(true) {
			
			
			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			// 1-1. 컨베이어 벨트 회전  
			int last = belt[2*N-1];
			for (int i=2*N-1; i>0; i--) {
				belt[i] = belt[i-1];
			} 
			belt[0] = last;
			
			// 1-2. 로봇 한칸 이동 (벨트 따라서) 
			for (int i = N-1; i>0; i--) {
				robot[i] = robot[i-1];
			}
			robot[0] = false;
			robot[N-1] =  false;
			
			// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 
			for (int i=N-1; i>0; i--) {
				if (!robot[i] && robot[i-1]) {	// 로봇이 존재하고 다음칸에 로봇이 없을 경우
					if (belt[i] > 0) {	// 이동할 칸에 내구도 1 이상 
						robot[i] = true;
						robot[i-1] = false;
						belt[i]--;
						robot[N-1] = false;	// 내리는 위치면 언제든지 내려야함 
					}
				}
			}
			
			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if (belt[0] > 0) {
				robot[0] = true;
				belt[0]--;
			}
			
			// 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
			int cnt = 0;
			for (int i=0; i<2 * N; i++) {
				if (belt[i] == 0) {
					cnt++;
				}
			}
			if (cnt >= K) break;
			
			level++;
		}
		
		System.out.println(level);
		br.close();
	}
}