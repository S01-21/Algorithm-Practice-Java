import java.util.*;
import java.io.*;

// 실버 5. 숫자 카드 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] numbers;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numbers);	// 오름차순 정렬 
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			
			int res = bs(0, N-1, num);
			if (res == -1) {
				res = 0;
			}
			sb.append(res).append(" ");
		}
		System.out.println(sb);
		br.close();
	}
	private static int bs(int start, int end, int num) {
		while (start <= end) {
			int mid = (start + end)/2;
			if (numbers[mid] == num) {
				return 1;
			}
			else if (numbers[mid] > num) {
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
		return -1;
	}
}