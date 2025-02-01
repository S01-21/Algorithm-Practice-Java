import java.util.*;
import java.io.*;

public class Main {
	static int N, M, res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (isPrime(num)) {
				res++;
			}
		}
		
		System.out.println(res);
		br.close();
	}
	private static boolean isPrime(int num) {
		if (num == 1)	return false;
		for (int i = 2; i < num; i++) {
			if (num % i == 0)	return false;
		}
		return true;
	}
}
