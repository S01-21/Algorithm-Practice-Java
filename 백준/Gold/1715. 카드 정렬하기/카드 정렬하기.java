import java.util.*;
import java.io.*;

// 골드 4. 카드 정렬하기 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, res;
	static PriorityQueue<Integer> pq;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		if (N > 1) {
			while(pq.size() != 1) {
				int tmp = pq.poll();
				tmp += pq.poll();
				pq.offer(tmp);
				res += tmp;
			}
			System.out.println(res);
		} else {
			System.out.println(0);
		}
		br.close();
	}
}