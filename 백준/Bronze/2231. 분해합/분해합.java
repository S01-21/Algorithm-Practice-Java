import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, res;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine());

		for (int i = 1; i < N; i++) {
			int tmp = i;
			int sum = i;
			
			while (tmp >= 1) {
				sum += tmp%10;
				tmp /= 10;
			}
			
			if (sum == N) {
				res = i;
				break;
			}
		}
		
		System.out.println(res);
		br.close();
	}
}
