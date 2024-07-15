import java.util.*;
import java.io.*;

// 실버 4. 수들의 합 2
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, res;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int l = 0;
		int r = 0;
		int sum = arr[l];
		
		while(l < N && r < N) {
			if (sum == M) {
				res++;
				sum -= arr[l];
				l++;
			} else if (sum < M) {
				r++;
				if (r >= N)	break;
				sum += arr[r];
			} else {
				sum -= arr[l];
				l++;
				if (l >= N)	break;
			}
		}
		System.out.println(res);
		br.close();
	}
}