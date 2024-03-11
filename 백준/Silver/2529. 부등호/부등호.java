import java.util.*;
import java.io.*;

// 부등호 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int K;
	static char[] sign;
	static long max, min;
	static String maxStr, minStr;
	static int[] numbers;
	static boolean[] isUsed;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());	// 부등호 문자 개수
		StringTokenizer st = new StringTokenizer(br.readLine());
		sign = new char[K];
		for (int i=0; i<K; i++) {
			sign[i] = st.nextToken().charAt(0);
		}
		
		max = Long.MIN_VALUE;
		min = Long.MAX_VALUE;
		numbers = new int[K+1];
		
		for (int i=0; i<10; i++) {
			isUsed = new boolean[10];
			numbers[0] = i;
			isUsed[i] = true;
			find(1);
		}
		
		
		System.out.println(maxStr);
		System.out.println(minStr);
	}
	private static void find(int idx) {
		if (idx == K+1) {
			String tmp = "";
			for (int i : numbers) {
				tmp += i;
			}
			long res = Long.parseLong(tmp);
			if (res > max) {
				max = res;
				maxStr = tmp;
			}
			if (res < min) {
				min = res;
				minStr = tmp;
			}
			return;
		}
		
		for (int i=0; i<10; i++) {
			if (isUsed[i])	continue;
			char tmp = sign[idx-1];
			if (tmp == '<') {
				if (numbers[idx-1] < i) {
					numbers[idx] = i;
					isUsed[i] = true;
					find(idx + 1);
					isUsed[i] = false;
				}
			} else if (tmp == '>'){
				if (numbers[idx-1] > i) {
					numbers[idx] = i;
					isUsed[i] = true;
					find(idx + 1);
					isUsed[i] = false;
				}
			}
		}
	}
}