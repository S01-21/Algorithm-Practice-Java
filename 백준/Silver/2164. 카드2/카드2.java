import java.util.*;
import java.io.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> cards = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			cards.offer(i+1);
		}
		
		while (cards.size() > 1) {
			cards.poll();
			int second = cards.poll();
			cards.offer(second);
		}

		System.out.println(cards.poll());
	}
}