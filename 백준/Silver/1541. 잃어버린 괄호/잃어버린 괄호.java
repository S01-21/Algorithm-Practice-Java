import java.util.*;
import java.io.*;

public class Main {
	static int N, res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		String str = br.readLine();
		
		int tmp = 0;
		int pos = 1;
		for (int i = str.length() - 1; i >= 0; i--) {
			char c = str.charAt(i);
			
			if (c == '+') {
				pos = 1;
			} else if (c == '-') {
				res -= tmp;
				tmp = 0;
				pos = 1;
			} else {
				tmp += (c - '0')*pos;
				pos *= 10;
			}
		}
		res += tmp;
		
		System.out.println(res);
		br.close();
	}
}
