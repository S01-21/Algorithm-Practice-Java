import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static ArrayList<Long> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		make();
		
		if (N >= list.size()) {
			System.out.println(-1);
		} else {
			System.out.println(list.get(N));
		}
		
		br.close();
	}
	private static void make() {
		Queue<Long> queue = new ArrayDeque<>();
		for (long i = 0; i < 10; i++) {
			queue.offer(i);
			list.add(i);
		}
		while(!queue.isEmpty()) {
			long num = queue.poll();
			long last = num % 10;
			for (int nxt = 0; nxt < last; nxt++) {
				long newNum = num*10 + nxt;
				queue.offer(newNum);
				list.add(newNum);
			}
		}
	}
}
