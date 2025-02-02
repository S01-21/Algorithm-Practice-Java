import java.util.*;
import java.io.*;

public class Main {
	static int N, M, res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		long sum = 0;
		int r = 31;
		int M = 1234567891;
		long power = 1;
		for (int i = 0; i < N; i++) {
			int a = str.charAt(i) - 'a' + 1;
			sum = (sum + (a*power) % M) % M;
			power = (power*r) % M;
		}
		System.out.println(sum);
		br.close();
	}
}
