import java.util.*;
import java.io.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int N, L;
	static int[] heights;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		heights = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(heights);
		
		for (int i = 0 ; i < N ; i++) {
			if (L >= heights[i]) {
				L++;
			}
		}

		System.out.println(L);
	}
}