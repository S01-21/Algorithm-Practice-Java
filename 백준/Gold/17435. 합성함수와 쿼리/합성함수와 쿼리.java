import java.util.*;
import java.io.*;

// 합성함수와 쿼리 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int M, Q;
	static int[][] table;
	final static int LOG = (int)(Math.log10(500000)/Math.log10(2));
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		M = Integer.parseInt(br.readLine());
		table = new int[LOG + 1][M+1];	//x를 2^i번 합성했을 때 결과 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<= M; i++) {	// f1(x) 값 저장 (1번 합성한 합성함수)
			table[0][i] = Integer.parseInt(st.nextToken());
		}
		
		setTable();	// 희소배열 저장 
		
		Q = Integer.parseInt(br.readLine());	// 쿼리 개수 
		for (int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			x = query(n, x);
			sb.append(x).append("\n");
		}
		System.out.println(sb);
	}
	
	static void setTable() {
		for (int i=1; i<LOG+1; i++) {
			for (int j = 1; j <= M; j++) {
				table[i][j] = table[i-1][table[i-1][j]];
			}
		}
	}
	static int query(int n, int x) {
		for (int i = LOG; i >= 0; i--) {	// n이하 중 가장 큰 2의제곱수부터 확인하면서 합성 
//			int cur = (1<<i);
			int cur = (int) Math.pow(2,i);
			if (n >= cur) {
				x = table[i][x];
				n -= cur;
				if (n == 0)	break;
			}
		}
		return x;
	}
}