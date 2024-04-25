import java.util.*;
import java.io.*;

// 실버 4. 숫자 카드 2
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static Map<Integer, Integer> map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 숫자 카드 개수 
		map = new HashMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (map.containsKey(num)) {
				int tmp = map.get(num);
				map.put(num, tmp + 1);
			} else {
				map.put(num, 1);
			}
		}
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (map.containsKey(num)) {
				sb.append(map.get(num)).append(" ");
			} else {
				sb.append(0).append(" ");
			}
		}
		
		System.out.println(sb);
		br.close();
	}
}