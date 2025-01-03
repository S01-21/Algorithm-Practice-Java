import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, res, max;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}

		bs();
		
		System.out.println(res);
		br.close();
	}
	private static void bs() {
		int left = 0;		// 절단기 높이의 최솟값
		int right = max;	// 절단기 높이의 최댓값
		while (left <= right) {
			int mid = (left + right) / 2;
			
			long sum = 0;
			
			for (int h : arr) {
				if (h > mid) {
					sum += h - mid;
				}
			}
			
			if (sum >= M) {
				res = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
	}
}
