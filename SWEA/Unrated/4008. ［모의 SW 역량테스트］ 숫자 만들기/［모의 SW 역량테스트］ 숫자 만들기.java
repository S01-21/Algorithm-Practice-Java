import java.util.*;
import java.io.*;

// 숫자 만들기 
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] numbers;
	static int[] operator;
	static int max, min;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());	// 숫자 개수 
			numbers = new int[N];
			operator = new int[4];	// +, -, *, / 개수 
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			
			func(numbers[0], 0);
			
			sb.append(max - min).append("\n");
			
		}
		System.out.println(sb);
	}
	// num: 왼쪽 피연산자, cnt: 사용한 연산자 개수 
	private static void func(int num, int cnt) {
		if (cnt == N-1) {
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}
		int op = 0;
		for (int i=0; i<4; i++) {
			if (operator[i] > 0) {
				op = i;
				int tmp = calc(num, numbers[cnt + 1], op);
				operator[i]--;
				func(tmp, cnt + 1);
				operator[i]++;
			}
		}
	}
	
	
	static int calc (int a, int b, int op) {
		if (op == 0) {
			return a + b;
		} else if (op == 1) {
			return a - b;
		} else if (op == 2) {
			return a * b;
		} else {
			return a / b;
		}
	}
}