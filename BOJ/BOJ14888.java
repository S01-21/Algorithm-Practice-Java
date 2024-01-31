package boj_study;

import java.util.*;
import java.io.*;

public class BOJ14888 {
	static int N;
	static int[] operators = new int[4]; // 연산자 +, -, *, / 개수
	static int[] numbers;
	static int mn = Integer.MAX_VALUE;
	static int mx = Integer.MIN_VALUE;

	static int calc(int a, int b, int op) {
		int res = 0;
		switch (op) {
		case 0:
			res = a + b;
			break;
		case 1:
			res = a - b;
			break;
		case 2:
			res = a * b;
			break;
		case 3:
			if (a < 0) {
				a *= -1;
				res = -1 * (a / b);
			} else {
				res = a / b;
			}
			break;
		}
		return res;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];

		// 정수 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		// 연산자 개수 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}

		func(numbers[0], 0);

		System.out.println(mx + "\n" + mn);
	}

	
	// pre: 연산자의 앞 피연산자
	// cnt: 현재까지 사용한 연산자 수 
	private static void func(int pre, int cnt) {
		if (cnt == N - 1) {	// 완전한 수식 만들면 최대, 최솟값 계산 
			mn = Math.min(mn, pre);
			mx = Math.max(mx, pre);
			return;
		}
		int nxt = numbers[cnt + 1];	// 연산자의 뒷 피연산자 
		for (int j = 0; j < 4; j++) {	// 사용가능한 연산자 찾으면 계산하고 백트래킹 호출 
			if (operators[j] > 0) {
				operators[j]--;
				int num = calc(pre, nxt, j);
				func(num, cnt + 1);
				operators[j]++;
			}
		}
	}
}
