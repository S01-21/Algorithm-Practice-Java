import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Deque<Integer> dq = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			dq.offer(i);
		}
		
		sb.append("<");
		while (!dq.isEmpty()) {
			for (int i = 0 ; i < K-1; i++) {
				int tmp = dq.removeFirst();
				dq.addLast(tmp);
			}
			int pick = dq.removeFirst();
			sb.append(pick);
			
			if (!dq.isEmpty()) {
				sb.append(", ");
			}
		}
		
		sb.append(">");
		System.out.println(sb);
		br.close();
	}
}