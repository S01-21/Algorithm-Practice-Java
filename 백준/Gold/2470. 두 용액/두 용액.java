import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0 ;i  < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int sum = Integer.MAX_VALUE;
		int i = 0;
		int j = N-1;
		int a = 0;
		int b = 0;
		Arrays.sort(arr);
		while (i < j) {
			int tmp = arr[i] + arr[j];
			
			
			if (Math.abs(tmp) < Math.abs(sum)) {
				sum = tmp;
				a = i;
				b = j;
				if (tmp == 0)	break;
			}
			
			if (tmp > 0) {
				j--;
			} else {
				i++;
			}
		}
		System.out.println(arr[a] + " " +arr[b]);
		br.close();
	}
}
