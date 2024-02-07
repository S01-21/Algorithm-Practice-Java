import java.util.*;
import java.io.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int N;
	static boolean[][] paper = new boolean[100][100];
	static int res;	// 색종이 붙은 영역 넓이
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		for (int i =0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			
			for (int j=x; j<x+10; j++) {
				for (int k=y; k < y+10; k++) {
					if (!paper[j][k]) {
						paper[j][k] = true;
					}
				}
			}
		}
		for (int i=0; i<100; i++) {
			for (int j=0; j<100; j++) {
				if (paper[i][j]) {
					res++;
				}
			}
		}
		
		System.out.println(res);
	}
}