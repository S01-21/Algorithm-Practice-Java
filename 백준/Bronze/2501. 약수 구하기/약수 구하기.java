import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K, res;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (N%i == 0) {
				cnt++;
				if (cnt == K) {
					res = i;
					break;
				}
			}
		}
		
		System.out.println(res);
		br.close();
	}
}
