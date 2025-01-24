import java.util.*;
import java.io.*;
import java.io.*;
import java.math.*;

public class Main {
	static int N, res;
	static int[] arr;
	static ArrayList<Integer> lis = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
			
		for (int num : arr) {
			int pos = bs(num);
			if (pos == lis.size()) {
				lis.add(num);	// 배열 끝에 추가
			} else {
				lis.set(pos, num);	// 기존 값 대체
			}
		}
		System.out.println(lis.size());
		br.close();
	}
	private static int bs(int num) {
		int start = 0;
		int end = lis.size() - 1;

		while (start <= end) {
			int mid = (start + end)/2;
			
			if (lis.get(mid) < num) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return start;	// num 삽입 위치 반환 
	}
}
