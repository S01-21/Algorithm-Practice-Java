import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static class Item implements Comparable<Item>{
		int w, v;
		public Item(int w, int v) {
			this.w = w;
			this.v = v;
		}
		public int compareTo(Item o) {
			return this.w - o.w;
		}
	}
	static int[] bags;
	static StringBuilder sb = new StringBuilder();
	static Item[] items;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 보석 개수
		K = Integer.parseInt(st.nextToken());	// 가방 개수 
		items = new Item[N];
		for (int i = 0 ;i  < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			items[i] = new Item(w, v);
		}
		bags = new int[K];
		for (int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		
		
		Arrays.sort(items);
		Arrays.sort(bags);
		long res = 0;
		int idx = 0;
		PriorityQueue<Integer> value = new PriorityQueue<>((o1, o2) -> o2 - o1);
		for (int bag : bags) {
			while (idx < N && items[idx].w <= bag) {
				value.offer(items[idx].v);
				idx++;
			}
			if (!value.isEmpty()) {
				res += value.poll();
			}
		}
		System.out.println(res);
		br.close();
	}
}
