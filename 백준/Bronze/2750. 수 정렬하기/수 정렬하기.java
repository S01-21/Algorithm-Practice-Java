import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		for (int i = 0 ; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		quickSort(0, N-1);
		
		for (int i = 0 ; i < N; i++) {
			sb.append(arr[i]).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	private static void quickSort(int low, int high) {
		if (low < high) {
			int pos = partition(low, high);
			quickSort(low, pos - 1);
			quickSort(pos + 1, high);
		}
	}
	private static int partition(int low, int high) {
		int pivot = arr[high];
		
		int i = low - 1;	// 파랑 포인터
		for (int j = low ; j < N ; j++) {
			if (arr[j]<pivot) {
				i++;
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}
		
		int tmp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = tmp;
		
		return i + 1;
	}
}
