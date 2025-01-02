import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, res;
	static int[] arr;
	static boolean[] isSel;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		isSel = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		func(0, 0);	// 현재까지 고른 개수, 현재까지 합
		
		System.out.println(res);
		br.close();
	}
	private static void func(int cnt, int sum) {
		if (cnt == 3) {
			res = Math.max(res, sum);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (isSel[i])	continue;
			if (sum + arr[i] > M)	continue;
			isSel[i] = true;
			func(cnt + 1, sum + arr[i]);
			isSel[i] = false;
		}
	}
}
