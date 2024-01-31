import java.io.*;
import java.util.*;

public class Main {
	static int[] switchState;
	
	static void swap(int idx) {
		if (switchState[idx] == 1) {
			switchState[idx] = 0;
		} else {
			switchState[idx] = 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int S = Integer.parseInt(br.readLine());	//스위치 개수
		switchState = new int[S+1];	//스위치 상태
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1 ; i < S+1; i++) {
			switchState[i] = Integer.parseInt(st.nextToken());
		}
		
		int N = Integer.parseInt(br.readLine());	//학생수
		int[][] studentNum = new int[N][2];
		
		for (int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			studentNum[i][0] = Integer.parseInt(st.nextToken());	//성별
			studentNum[i][1] = Integer.parseInt(st.nextToken());	//번호
		}
		
		for (int i = 0; i < N; i++) {
			int num = studentNum[i][1];		//학생이 받은 스위치 번호
			if (studentNum[i][0] == 1) {	//남학생일 경우
				int cnt = 1;
				int tmp = num;
				do {
					swap(tmp);
					tmp = num * ++cnt;
				} while (tmp <= S);
			} else {						//여학생일 경우
				swap(num);
				int cnt = 1;
				while (true) {
					int left = num - cnt;
					int right = num + cnt;
					if (left < 1 || right > S) break;
					if (switchState[left] != switchState[right])	break;
					
						swap(left);
						swap(right);
						cnt++;
					
				}
			}
		}
		
		StringBuilder sb=  new StringBuilder();
		for (int i = 1 ; i<S+1; i++) {
			sb.append(switchState[i]).append(" ");
			if (i%20 == 0) {
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

}
