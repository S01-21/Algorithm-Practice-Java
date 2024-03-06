import java.util.*;
import java.io.*;

// 부분합
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, S;
	static int[] arr;
	static int minLength;
	static int count;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 수열의 길이 
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		minLength = Integer.MAX_VALUE;
		int j=0;
		int sum = arr[j];
		for (int i=0; i<N; i++) {
			while (true) {
				if (sum >= S) {
					minLength = Math.min(minLength, j-i+1);
					break;
				}
				if (j == N-1)	break;
				sum += arr[++j];
			}
			sum -= arr[i];
		}
		
		if (minLength == Integer.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(minLength);
		}
	}
}