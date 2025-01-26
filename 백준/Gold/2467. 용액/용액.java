import java.util.*;
import java.io.*;

public class Main {
	static int N, res;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int i = 0;
		int j = N-1;
		int sum = Integer.MAX_VALUE;
		int res1 = 0, res2 = 0;
		while (i < j) {
			int tmp = arr[i] + arr[j];
			if (tmp < 0) {
				if (Math.abs(tmp) < Math.abs(sum)) {
					sum = tmp;
					res1 = i;
					res2 = j;
				}
				i++;
			} else if (tmp > 0) {
				if (Math.abs(tmp) < Math.abs(sum)) {
					sum = tmp;
					res1 = i;
					res2 = j;
				}
				j--;
			} else {
				res1 = i;
				res2 = j;
				break;
			}
		}
		
		System.out.println(arr[res1] + " " + arr[res2]);
		br.close();
	}
}
