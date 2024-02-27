import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[] D = new int[1000001];
		
		D[1] = 0;
		D[2] = 1;
		D[3] = 1;
		
		for (int i = 4; i<= N; i++) {
			if (i%3 == 0 && i%2 == 0) {
				D[i] = Math.min(D[i/3], D[i/2]) + 1;
			} else if (i % 3 == 0) {
				D[i] = Math.min(D[i/3], D[i-1]) + 1;
			} else if (i % 2 == 0) {
				D[i] = Math.min(D[i/2], D[i-1]) + 1;
			} else {
				D[i] = D[i-1] + 1;
			}
		}
		
		
		
		System.out.println(D[N]);
	}
}