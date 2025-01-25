import java.util.*;
import java.io.*;

public class Main {
	static int N, res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
//		N = Integer.parseInt(br.readLine());
		
		int state = 0;	// 1: asc, 2: desc, 3: mixed
		int tmp = Integer.parseInt(st.nextToken());
		for (int i = 0 ; i < 7; i++) {
			int cur = Integer.parseInt(st.nextToken());
			if (cur == tmp + 1) {
				if (state == 0 || state == 1) {
					tmp = cur;
					state = 1;
				} else {
					state = 3;
					break;
				}
			} else if (cur == tmp - 1) {
				if (state == 0 || state == 2) {
					tmp = cur;
					state = 2;
				} else {
					state = 3;
					break;
				}
			} else {
				state = 3;
				break;
			}
		}
		if (state == 1) {
			System.out.println("ascending");
		} else if (state == 2) {
			System.out.println("descending");
		} else {
			System.out.println("mixed");
		}
		
		br.close();
	}
}
