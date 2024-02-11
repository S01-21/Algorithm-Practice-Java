import java.io.*;
import java.util.*;

public class Main {
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		String str = "";
		while(N > 0) {
			str = "" + cnt;
			cnt++;
			if (str.contains("666")) {
				N--;
			}
		}
		
		System.out.println(str);
	}

}