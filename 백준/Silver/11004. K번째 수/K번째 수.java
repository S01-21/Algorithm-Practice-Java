import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1 ; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		heapSort();
		
		System.out.println(arr[K]);
		br.close();
	}
	private static void heapSort() {
		for (int i = N/2; i > 0; i--) {
			heapify(N, i);
		}
		
		for (int i = N; i > 0; i--) {
			int tmp = arr[1];
			arr[1] = arr[i];
			arr[i] = tmp;
			
			heapify(i - 1, 1);
		}
	}
	
	private static void heapify(int last, int i) {
		int largest = i;
		int l = i*2;
		int r = i*2 + 1;
		
		if (l <= last && arr[l] > arr[largest]) {
			largest = l;
		}
		if (r <= last && arr[r] > arr[largest]) {
			largest = r;
		}
		if (largest != i) {
			int tmp = arr[largest];
			arr[largest] = arr[i];
			arr[i] = tmp;
			heapify(last, largest);
		}
	}
}
