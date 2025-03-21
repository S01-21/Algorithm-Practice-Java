import java.util.*;
import java.io.*;

public class Main {
	static int res, N;
	static class Problem implements Comparable<Problem>{
		int num, score;
		public Problem(int num, int score) {
			this.num = num;
			this.score = score;
		}
		public int compareTo(Problem o) {
			return o.score - this.score;
		}
	}
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Problem[] list = new Problem[8];
		
		for (int i = 0;  i < 8; i++) {
			int score = Integer.parseInt(br.readLine());
			list[i] = new Problem(i+1, score);
		}
		
		Arrays.sort(list);
		PriorityQueue<Integer> nums = new PriorityQueue<>();
		int sum = 0;
		for (int i = 0; i < 5; i++) {
			sum += list[i].score;
			nums.offer(list[i].num);
		}
		sb.append(sum).append("\n");
		
		while(!nums.isEmpty()) {
			sb.append(nums.poll()).append(" ");
		}
		
		System.out.println(sb);
		br.close();
	}
}
