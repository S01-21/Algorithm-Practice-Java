import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] prime = {2, 3, 5, 7};
	static int[] res;
	static boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0)	return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		res = new int[N];	// 결과 저장 
		
		for (int i = 0 ; i < 4; i++) {
			res[0] = prime[i];
			func(1);
		}
		
		System.out.println(sb);
	}
	static void func(int loc) {
		// 기저 부분 
		if (loc == N) {
			for (int i = 0 ; i < N; i++) {
				sb.append(res[i]);
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0 ; i<10; i++) {
			int sum = 0;
			for (int j = 0; j <loc; j++) {
				sum += res[j]*Math.pow(10,loc-j);
			}
			if (isPrime(sum + i)) {
				res[loc] = i;
				func(loc + 1);
			}
		}
		
	}
}