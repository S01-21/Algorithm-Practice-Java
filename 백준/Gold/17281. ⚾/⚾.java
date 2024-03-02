import java.util.*;
import java.io.*;

// 야구 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] result;
	static int[] order;
	static int maxScore, tmpScore;
	static int[] plate;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 이닝 수
		result = new int[N][9];
		StringTokenizer st  = null;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<9; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		maxScore = -1;
		order = new int[9];
		perm(0, 0);
		
		System.out.println(maxScore);
	}
	// cnt: 뽑을 차례 
	private static void perm(int cnt, int flag) {
		if (cnt == 9) {	// 다뽑았으면 점수 구하러 
			tmpScore = 0;
			play();
			maxScore = Math.max(maxScore, tmpScore);
			return;
		}
		if (cnt == 3) {
			order[cnt] = 0;
			perm(cnt + 1, flag);
			return;
		}
		
		for (int i=1; i<9; i++) {	// 1번[0]선수는 4번타자 고정이니까, 2번[1]부터 
			if ((flag & 1 << i) == 0) {
				order[cnt] = i;
				perm(cnt + 1, flag | 1 << i);
			}
		}
	}
	
	private static void play() {
		int st = 0;		// 타석에 첫번째로 설 선수 (order[st])
		for (int i=0; i<N; i++) {	// N 이닝 
			plate = new int[3];
			
			int outCnt = 0;
			while(outCnt < 3) {
				switch(result[i][order[st]]) {
				case 0:		// 아웃 
					outCnt++;
					break;
				case 1:
				case 2:
				case 3:
				case 4:
					move(result[i][order[st]]);
					break;
				}
				st++;	// 다음주자 
				if (st == 9) {
					st = 0;
				}
			}
		}
	}
	
	
	static void move(int cnt) {
		for (int i=0; i<cnt; i++) {
			if (plate[2] == 1) {	// 3루에 사람 있었으면 점수++
				tmpScore++;
			}
			// 1, 2루 -> 2, 3루 밀어주기
			for (int j=2; j>=1; j--) {
				plate[j] = plate[j-1];
			}
			
			// 현재 주자 진출 
			if (i==0) {
				plate[0] = 1;
			} else {
				plate[0] = 0;
			}
		}
	}
}