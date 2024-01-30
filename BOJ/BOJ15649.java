import java.util.*;
import java.io.*;
public class Main {
	static int N, M;
	static boolean[] isUsed;
	static int[] res;
	static StringBuilder sb = new StringBuilder();
	
	static void func(int k) {
		if (k == M) {
			for (int i = 0; i<M; i++) {
				sb.append(res[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if (!isUsed[i]) {
				res[k] = i;
				isUsed[i] = true;
				func(k + 1);
				isUsed[i] = false;
			}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isUsed = new boolean[N+1];
		res = new int[M];
		
		func(0);
		System.out.println(sb);
	}
}
