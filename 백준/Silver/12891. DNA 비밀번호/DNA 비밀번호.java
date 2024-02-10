import java.util.*;
import java.io.*;
public class Main {
	static int S, P;
	static int[] minCnt = new int[4];	// A, C, G, T 의 최소 개수 
	static int[] curCnt = new int[4];	// A, C, G, T 의 현재 개수 
	static int satisfyCnt;
	static int res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		String str = br.readLine();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			minCnt[i] = Integer.parseInt(st.nextToken());
		}
		
		String subStr = str.substring(0, P);	// 부분 문자열 구하기 
		for (int i = 0; i < subStr.length(); i++) {
			char c = subStr.charAt(i);
			switch(c) {
			case 'A':
				curCnt[0]++;
				break;
			case 'C':
				curCnt[1]++;
				break;
			case 'G':
				curCnt[2]++;
				break;
			case 'T':
				curCnt[3]++;
				break;
			}
		}
		
		// 초기 상태 기록 
		for (int i = 0 ; i < 4; i++) {
			if (curCnt[i] >= minCnt[i]) {
				satisfyCnt++;
			}
		}
		// 초기 상태가 조건 만족하면 결과값 카운트 +1 
		if (satisfyCnt == 4)	res++;
		
		for (int i = P; i < S; i++) {
			int j = i - P;	// 윈도우의 첫번째 요소의 인덱스 
			add(str.charAt(i));
			remove(str.charAt(j));
			
			if (satisfyCnt == 4)	res++;
		}
		
		System.out.println(res);
	}
	private static void remove(char out) {
		switch(out) {
		case 'A':
			if (curCnt[0] == minCnt[0]) {	//기존에 최솟값이었으면 이게 빠지면 만족조건 -1 
				satisfyCnt--;
			}
			curCnt[0]--;
			break;
		case 'C':
			if (curCnt[1] == minCnt[1]) {
				satisfyCnt--;
			}
			curCnt[1]--;
			break;
		case 'G':
			if (curCnt[2] == minCnt[2]) {
				satisfyCnt--;
			}
			curCnt[2]--;
			break;
		case 'T':
			if (curCnt[3] == minCnt[3]) {
				satisfyCnt--;
			}
			curCnt[3]--;
			break;
		}
	}
	private static void add(char in) {
		switch(in) {
		case 'A':
			curCnt[0]++;	// 현재 A 개수 증가 
			if (curCnt[0] == minCnt[0]) {
				satisfyCnt++;
			}
			break;
		case 'C':
			curCnt[1]++;	// 현재 C 개수 증가 
			if (curCnt[1] == minCnt[1]) {
				satisfyCnt++;
			}
			break;
		case 'G':
			curCnt[2]++;	// 현재 G 개수 증가 
			if (curCnt[2] == minCnt[2]) {
				satisfyCnt++;
			}
			break;
		case 'T':
			curCnt[3]++;	// 현재 T 개수 증가 
			if (curCnt[3] == minCnt[3]) {
				satisfyCnt++;
			}
			break;
		}
	}

}