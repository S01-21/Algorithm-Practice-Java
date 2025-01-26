import java.util.*;
import java.io.*;

public class Main {
	static int H, W, N, res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());	// 호텔의 층 수
			W = Integer.parseInt(st.nextToken());	// 각 층의 방 수
			N = Integer.parseInt(st.nextToken());	// 몇번째 손님
			
			String tmp = func();
			sb.append(tmp).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
	private static String func() {
		int cnt = 0;
		for (int i = 1; i <= W; i++) {
			for (int j = 1 ; j <= H; j++) {
				cnt++;
				if (cnt == N)	{
					String str = Integer.toString(j);
					if (i < 10) {
						str += "0";
					}
					str += Integer.toString(i);
					return str;
				}
			}
		}
		return "";
	}
}
