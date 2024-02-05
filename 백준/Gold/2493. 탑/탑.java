import java.util.*;
import java.io.*;

public class Main {
	static class Top {
		int height, num;

		public Top(int num, int height) {
			super();
			this.num = num;
			this.height = height;
		}
	}

	private static StringBuilder sb = new StringBuilder();
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Top> input = new Stack<>();
		Stack<Top> compare = new Stack<>();
		int[] result = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			input.add(new Top(i, Integer.parseInt(st.nextToken())));
		}
		while (!input.isEmpty()) {
			Top tmp = input.pop();
			if (compare.isEmpty()) {
				compare.push(tmp);
			} else {
				while (!compare.isEmpty()) {
					Top prev = compare.peek();
					if (tmp.height > prev.height) { // 수신 가능
						result[prev.num] = tmp.num;
						compare.pop();
					}	else {	// 수신 불가능
						compare.push(tmp);
						break;
					}
				}
				compare.push(tmp);
			}
		}
		for (int i = 1; i <= N; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb);
	}
}