import java.util.*;
import java.io.*;

// 실버 3. 소수 구하기 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		for (int i=M; i<=N; i++) {
			if (isPrime(i)) {
				sb.append(i).append("\n");
			}
		}
		System.out.println(sb);
		br.close();
	}

	private static boolean isPrime(int num) {
		if (num < 2) {
			return false;
		}
		for (int i=2; i <= Math.sqrt(num); i++) {
			if (num%i == 0) {
				return false;
			}
		}
		return true;
	}
}