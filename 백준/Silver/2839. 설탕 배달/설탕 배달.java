import java.util.*;
import java.io.*;

public class Main {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 봉지 최소 개수 초기화
		int sum  = 0;
		
		while (true) {
			if (N < 0) {
				sum = -1;
				break;
			}
			if (N%5 == 0) {
				sum += N/5;
				break;
			} else {
				sum++;
				N -= 3;
			}
		}
		System.out.println(sum);
	}
}