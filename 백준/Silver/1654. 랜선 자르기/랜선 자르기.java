import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K;
	static int[] arr;
	static long sum, max;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[K];
		for (int i = 0 ; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}
		
		max = sum / N;
		
		long res = bs();
		
		System.out.println(res);
		br.close();
	}
	private static long bs () {
		long start = 1;
		long end = max;
		
		long maxNum = 0;
		
		while (start <= end) {
			long mid = (start + end) / 2;
			
			long cnt = 0;
			for (int i = 0; i < K; i++) {
				cnt += arr[i]/mid;
				if (cnt >= N)	break;
			}
			
			if (cnt >= N) {
				start = mid + 1;
				maxNum = Math.max(maxNum, mid);
			} else {
				end = mid - 1;
			}
		}
		return maxNum;
	}
}
