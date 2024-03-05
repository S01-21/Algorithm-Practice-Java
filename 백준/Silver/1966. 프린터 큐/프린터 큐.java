import java.util.*;
import java.io.*;

// 프린터 큐 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static ArrayList<Doc> list;
	static class Doc{
		int order, weight;

		public Doc(int order, int weight) {
			super();
			this.order = order;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Doc [order=" + order + ", weight=" + weight + "]";
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 문서 개수 
			M = Integer.parseInt(st.nextToken());	// 문서가 현재 Queue에서 몇 번째에 놓여 있는지를 나타내는 정수
			
			list = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				list.add(new Doc(i, Integer.parseInt(st.nextToken())));
			}
			
			int cnt = 1;
			int idx = 0;
			while(idx < list.size()) {
				Doc first = list.get(idx);
				boolean flag = true;
				for (int i = idx+1; i < list.size(); i++) {
					if (list.get(i).weight > first.weight) {
						flag = false;
						list.add(first);
						break;
					}
				}
				if (flag && first.order == M) {
					break;
				}
				if (flag) {
					cnt++;
				}
				idx++;
			}			
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}