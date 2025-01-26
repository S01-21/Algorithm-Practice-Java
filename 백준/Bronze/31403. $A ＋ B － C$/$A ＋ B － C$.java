import java.util.*;
import java.io.*;

public class Main {
	static int A, B, C, res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = Integer.parseInt(br.readLine());
		B = Integer.parseInt(br.readLine());
		C = Integer.parseInt(br.readLine());
		
		String str = Integer.toString(A)+Integer.toString(B);
		int tmp = Integer.parseInt(str);
		
		System.out.println(A+B-C);
		System.out.println(tmp-C);
		
		
		br.close();
	}
}
