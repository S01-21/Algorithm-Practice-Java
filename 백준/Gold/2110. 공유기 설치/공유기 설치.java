import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, C, res;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for (int i = 0 ; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		bs();
		
		System.out.println(res);
		br.close();
	}
	
	private static void bs() {
		int start = 1;
		int end = arr[N-1] - arr[0];
		
		while (start <= end) {
			int mid = (start + end)/2;
			
			int cnt = 1;
			int last = 0;
			for (int i = 1; i < N; i++) {
				if (arr[i] - arr[last] >= mid) {
					cnt++;
					last = i;
				}
			}
			
			if (cnt >= C) {
				start = mid + 1;
				res = Math.max(res, mid);
			} else {
				end = mid - 1;
			}
		}
	}
}
