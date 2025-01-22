import java.util.*;
import java.io.*;
import java.io.*;
import java.math.*;

public class Main {
	static int A, B, res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		Set<Integer> a = new HashSet<>();
		Set<Integer> b = new HashSet<>();
		Set<Integer> same = new HashSet<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < A; i++) {
			a.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < B; i++) {
			int num = Integer.parseInt(st.nextToken());
			b.add(num);
			if (a.contains(num)) {
				same.add(num);
			}
		}
		a.removeAll(same);
		b.removeAll(same);
		
		System.out.println(a.size() + b.size());
		br.close();
	}
}
