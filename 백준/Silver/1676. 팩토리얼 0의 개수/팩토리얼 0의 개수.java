import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	static int N, K, res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(br.readLine());
		BigInteger tmp = BigInteger.ONE;
		for (int i = N; i >= 1; i--) {
			tmp = tmp.multiply(BigInteger.valueOf(i));
		}
		
		String str = tmp.toString();
		for (int i = str.length()-1; i >= 0; i--) {
			if (str.charAt(i) == '0') {
				res++;
			} else {
				break;
			}
		}
		
		System.out.println(res);
		br.close();
	}
}
