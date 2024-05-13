import java.util.*;
import java.io.*;

// 실버 5. 소트인사이드 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static PriorityQueue<Integer> pq;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		pq = new PriorityQueue<>((a, b) -> b-a);
		
		for (int i=0; i<str.length(); i++) {
			pq.offer(str.charAt(i)- '0');
		}
		
		while (!pq.isEmpty()) {
			sb.append(pq.poll());
		}
		
		System.out.println(sb);
		br.close();
	}
}