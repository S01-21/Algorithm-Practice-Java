import java.util.*;
import java.io.*;

// 실버 4. 문자열 집합 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, res;
	static Set<String> set;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		set = new HashSet<>();
		
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			set.add(str);
		}
		
		for (int i=0; i<M; i++) {
			String str = br.readLine();
			if (set.contains(str)) {
				res++;
			}
		}
		System.out.println(res);
		br.close();
	}
}