import java.util.*;
import java.io.*;

// 실버 2. 차이를 최대로 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] arr;
	static int[] order;
	static boolean[] isSelected;
	static int res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		order = new int[N];
		isSelected = new boolean[N];
		perm(0);
		
		System.out.println(res);
		br.close();
	}
	private static void perm(int cnt) {
		if (cnt == N) {
			func();
			return;
		}
		for (int i=0; i<N; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				order[cnt] = arr[i];
				perm(cnt + 1);
				isSelected[i] = false;
			}
		}
	}
	static void func() {
		int sum = 0;
		for (int i=0; i<N-1; i++) {
			sum += Math.abs(order[i] - order[i+1]);
		}
		res = Math.max(res, sum);
	}
}