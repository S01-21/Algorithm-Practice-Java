import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	static int N, res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long S = Long.parseLong(br.readLine());
		
		long cnt = 0;
		while (true) {
			if (S < cnt) {
				cnt--;
				break;
			}
			S -= cnt;
			cnt++;
		}
		
		System.out.println(cnt);
		br.close();
	}
}
