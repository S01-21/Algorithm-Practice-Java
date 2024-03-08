import java.util.*;
import java.io.*;

// 	회문 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int T;
	static int res;
	static String str;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			str = br.readLine();
			
			res = 2;
			func(0, str.length() - 1, false);

			sb.append(res).append("\n");
		}
		System.out.println(sb);

	}

	// f: 앞 포인터, b: 뒤 포인터, flag: 1개 삭제했는지 체크
	private static void func(int f, int b, boolean flag) {
		boolean isPossible = true;
		while (f <= b) {
			if (str.charAt(f) != str.charAt(b)) {
				if (!flag) {
					func(f + 1, b, !flag);
					func(f, b - 1, !flag);
					return;
				} else {
					isPossible = false;
					break;
				}
			} else {
				f++;
				b--;
			}
		}
		
		if (!isPossible)	return;
		if (!flag) {
			res = 0;
		} else {
			res = 1;
		}
	}
}