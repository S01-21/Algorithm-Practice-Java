
import java.util.*;
import java.io.*;

// 실버 3. 풍선 터뜨리기 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static Deque<Balloon> dq = new ArrayDeque<>();
	static class Balloon{
		int idx, num;

		public Balloon(int idx, int num) {
			super();
			this.idx = idx;
			this.num = num;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			dq.addLast(new Balloon(i, Integer.parseInt(st.nextToken())));
		}
		
		for (int i=0; i<N; i++) {
			Balloon cur = dq.pollFirst();
			sb.append(cur.idx).append(" ");
			
			if (cur.num > 0 && !dq.isEmpty()) {	// 오른쪽으로 이동 
				for (int j=0; j<Math.abs(cur.num)-1; j++) {
					Balloon tmp = dq.pollFirst();
					dq.addLast(tmp);
				}
			} else if (cur.num < 0 && !dq.isEmpty() ) {	// 왼쪽으로 이동 
				for (int j=0; j<Math.abs(cur.num); j++) {
					Balloon tmp = dq.pollLast();
					dq.addFirst(tmp);
				}
			}
		}
		System.out.println(sb);
		br.close();
	}
}