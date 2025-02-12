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
		st = new StringTokenizer(br.readLine());
		
		ArrayList<Integer> list = new ArrayList<>();
		TreeSet<Integer> set = new TreeSet<>();
		
		for (int i = 0 ; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			list.add(num);
			set.add(num);
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		int idx = 0;
		Iterator it = set.iterator();
		while (it.hasNext()) {
			int num = (int) it.next();
			map.put(num, idx);
			idx++;
		}
		for (int i = 0 ; i < N; i++) {
			int num = list.get(i);
			sb.append(map.get(num)).append(" ");
		}
		
		System.out.println(sb);
		br.close();
	}
}
