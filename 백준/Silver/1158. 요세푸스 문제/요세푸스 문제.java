import java.util.*;
import java.io.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int N, K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i<= N; i++) {
			queue.offer(i);
		}
		
		sb.append('<');
		for (int i = 1; i<= N; i++) {
			for (int j = 0; j < K- 1; j++) {
				queue.offer(queue.poll());
			}
			sb.append(queue.poll());
			if (i != N)	sb.append(", ");
		}
		sb.append('>');
		System.out.println(sb);
	}
}