import java.util.*;
import java.io.*;

public class Main {
	static int N, res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		N = Integer.parseInt(br.readLine());
		
		int L = Integer.parseInt(br.readLine());
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		int D = Integer.parseInt(br.readLine());
		
		int math = B/D;
		math += B%D > 0 ? 1 : 0;
		int kor = A/C;
		kor += A%C > 0 ? 1 : 0;
		
		
		System.out.println(L - Math.max(math, kor));
		br.close();
	}
}
