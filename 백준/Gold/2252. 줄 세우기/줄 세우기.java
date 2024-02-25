import java.util.*;
import java.io.*;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] cnt;	// 진입차수 저장 
	static ArrayList<Integer>[] std;	// 학생들 키 비교 저장 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 학생 수 
		M = Integer.parseInt(st.nextToken());	// 키 비교 횟수 
		cnt = new int[N + 1];
		std = new ArrayList[N + 1];
		
		for (int i=0; i <= N; i++) {
			std[i] = new ArrayList<>();
		}
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());	
			std[a].add(b);
			cnt[b]++;
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i=1; i <= N; i++) {
			if (cnt[i] == 0) {
				queue.offer(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			sb.append(cur).append(" ");
			for (int  i= 0; i < std[cur].size(); i++) {
				int nxt = std[cur].get(i);
				cnt[nxt]--;
				if (cnt[nxt] == 0) {
					queue.offer(nxt);
				}
			}
		}
		
		System.out.println(sb);
	}

}