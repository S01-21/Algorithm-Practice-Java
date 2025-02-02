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
		
		int sum = 0;
		int r = 31;
		int M = 1234567891;
		for (int i = 0; i < N; i++) {
			int a = str.charAt(i) - 'a' + 1;
			sum += a*Math.pow(r, i) % M;
		}
		System.out.println(sum);
		br.close();
	}
}
