import java.util.*;
import java.io.*;

public class Main {
	static int N, res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine());
		for (int i = 0 ; i < N; i++) {
			String str = br.readLine();
			
			int sum = 0;
			int cnt = 0;
			for (int j = 0; j < str.length(); j++) {
				if (j == 0) {
					if (str.charAt(j) == 'O') {
						cnt = 1;
						sum += cnt;
					}
				} else {
					if (str.charAt(j) == 'O') {
						if (str.charAt(j-1) == 'O') {
							cnt++;
							sum += cnt;
						} else {
							cnt = 1;
							sum += cnt;
						}
					}
				}
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
