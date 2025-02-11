import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	static int N, res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String op = st.nextToken();
			if (op.equals("all")) {
				set = new HashSet<>();
				for (int j = 1; j <= 20; j++) {
					set.add(j);
				}
			} else if (op.equals("empty")) {
				set = new HashSet<>();
			} else {
				int num = Integer.parseInt(st.nextToken());
				
				if (op.equals("add")) {
					set.add(num);
				} else if (op.equals("remove")) {
					set.remove(num);
				} else if (op.equals("check")) {
					if (set.contains(num)) {
						sb.append(1).append("\n");
					} else {
						sb.append(0).append("\n");
					}
				} else if (op.equals("toggle")) {
					if (set.contains(num)) {
						set.remove(num);
					} else {
						set.add(num);
					}
				}
			}
		}
		
		System.out.println(sb);
		br.close();
	}
}
