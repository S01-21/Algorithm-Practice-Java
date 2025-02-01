import java.util.*;
import java.io.*;

public class Main {
	static int N, M, res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int max = Math.max(N, M);
		int min = Math.min(N, M);
		int res1 = func1(max, min);
		int res2 = func2(max, min);
		
		System.out.println(res1);
		System.out.println(res2);
		br.close();
	}
	private static int func1(int max, int min) {
		for (int i = max ; i >= 1; i--) {
			if (max%i == 0 && min%i ==0) {
				return i;
			}
		}
		return 1;
	}
	private static int func2(int max, int min) {
		for (int i = 1; i <= 10000; i++) {
			int num = min*i;
			if (num%max == 0 && num%min == 0) {
				return num;
			}
		}
		return 1;
	}
}
