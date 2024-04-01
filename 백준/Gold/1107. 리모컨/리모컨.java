import java.util.*;
import java.io.*;

// 골드5. 리모컨
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int res;
	static boolean[] broken;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 이동하려는 채널
		M = Integer.parseInt(br.readLine()); // 고장난 버튼 개수
		broken = new boolean[10];
		if (M != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				broken[Integer.parseInt(st.nextToken())] = true;
			}
		}
		res = func();

		System.out.println(res);
		br.close();
	}

	private static int func() {
		if (N == 100) { // 이동하려는 채널이 100번이면 버튼 클릭 필요 없음
			return 0;
		}
		if (M == 10) { // 버튼 10개 다 고장났으면 + or - 로 이동하는 방법뿐
			return Math.abs(N - 100);
		}

		int ret = Math.abs(N - 100); // 초기값 설정
		for (int i = 0; i <= 999999; i++) { // 9 버튼만 남아있을 수도 있으니까
			String tmp = Integer.toString(i);

			boolean isPossible = true;
			for (int j = 0; j < tmp.length(); j++) {
				if (broken[tmp.charAt(j) - '0']) {
					isPossible = false;
					break;
				}
			}

			if (!isPossible)
				continue; // 하나라도 고장났으면 패스

			int cnt = tmp.length() + Math.abs(N - i);
			ret = Math.min(ret, cnt);
		}
		return ret;
	}
}