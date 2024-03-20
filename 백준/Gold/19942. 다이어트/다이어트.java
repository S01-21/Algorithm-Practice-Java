import java.util.*;
import java.io.*;

// 다이어트 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int minPrice;
	static int[] minAmount;
	static ArrayList<String> minSet;
	static Nutrient[] nutList;
	static int[] selected;
	static class Nutrient {
		int p, f, s, v, price;
		public Nutrient(int p, int f, int s, int v, int price) {
			super();
			this.p = p;
			this.f = f;
			this.s = s;
			this.v = v;
			this.price = price;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		minAmount = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) { // 영양소의 최소 영양성분
			minAmount[i] = Integer.parseInt(st.nextToken());
		}
		nutList = new Nutrient[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			nutList[i] = new Nutrient(p, f, s, v, price);
		}

		minPrice = Integer.MAX_VALUE;
		minSet = new ArrayList<>();
		
		for (int i = 1; i<= N; i++) {
			selected = new int[i];
			func(0, 1, i);
		}
		

		if (minPrice == Integer.MAX_VALUE) {
			System.out.println(-1);
			System.exit(0);
		}
		Collections.sort(minSet);
		sb.append(minPrice).append("\n");
		sb.append(minSet.get(0));
		System.out.println(sb);
	}

	
	
	// cnt: 뽑은 개수, st: 선택 시작 인덱스, choice: 뽑을 개수 
	private static void func(int cnt, int st, int choice) {
		
		if (cnt == choice) {
			check(choice);
			return;
		}
		
		for (int i = st; i<=N; i++) {
			selected[cnt] = i;
			func(cnt + 1, i+1, choice);
		}
	}

	private static void check(int choice) {
		int priceSum = 0;
		int[] nutSum = new int[4];
		for (int i=0; i<choice; i++) {
			Nutrient cur = nutList[selected[i]];
			
			priceSum += cur.price;
			nutSum[0] += cur.p;
			nutSum[1] += cur.f;
			nutSum[2] += cur.s;
			nutSum[3] += cur.v;
			
			if (priceSum > minPrice) return;
		}
		
		for (int i=0; i<4; i++) {
			if (nutSum[i] < minAmount[i]) {
				return;
			}
		}
		
		if (priceSum <= minPrice) {
			if (priceSum < minPrice) {
				minSet.clear();
			}
			StringBuilder tmp = new StringBuilder();
			for (int i=0; i<choice; i++) {
				tmp.append(selected[i]).append(" ");
			}
			minSet.add(tmp.toString());
			minPrice = priceSum;
		}
	}
}