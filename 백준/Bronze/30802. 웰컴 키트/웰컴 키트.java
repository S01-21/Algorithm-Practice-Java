import java.util.*;
import java.io.*;

public class Main {
	static int N, M, res;
	static int[] tshirts = new int[6];
	static int T, P;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());	// 참가자수
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 6; i++) {
			tshirts[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		int total = 0;
		for(int size : tshirts) {
			total += (size + T-1)/T;
		}
		sb.append(total).append("\n");
		sb.append(N/P).append(" ").append(N%P);
		System.out.println(sb);
		br.close();
	}
}
