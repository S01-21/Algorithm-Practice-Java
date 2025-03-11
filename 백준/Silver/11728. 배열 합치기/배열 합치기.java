import java.util.*;
import java.io.*;

public class Main {
	static int res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < M; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(list);
		
		for (int num : list) {
			sb.append(num).append(" ");
		}
		System.out.println(sb);
		br.close();
	}
}
