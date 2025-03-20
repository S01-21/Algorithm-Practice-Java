import java.util.*;
import java.io.*;

public class Main {
	static int res, N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) ->  o2-o1);
		for (int i = 0 ; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			pq.offer(num);
		}
		
		while(!pq.isEmpty()) {
			sb.append(pq.poll());
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
