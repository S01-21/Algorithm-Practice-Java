import java.util.*;
import java.io.*;

// 단어 정렬 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static Set<String> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new TreeSet<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() != o2.length()) {
					return o1.length() - o2.length();
				} else {
					return o1.compareTo(o2);
				}
			}
		});
		
		for (int i=0; i<N; i++) {
			list.add(br.readLine());
		}
		
		for (String str : list) {
			sb.append(str).append("\n");
		}
		System.out.println(sb);
	}
}