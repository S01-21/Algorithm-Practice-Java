import java.util.*;
import java.io.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] arr, find;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		M = Integer.parseInt(br.readLine());
		find = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i<M; i++) {
			find[i] = Integer.parseInt(st.nextToken());
			int res = bs(0, arr.length-1, find[i]);
			sb.append(res).append("\n");
		}
		
		System.out.println(sb);
	}
	static int bs(int start, int end, int tgt) {
		int mid = 0;
		while (start <= end) {
			mid = (start + end)/ 2;
			if (arr[mid] == tgt) {
				return 1;
			}
			if (arr[mid] > tgt) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return 0;
	}
}