import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	static int N, res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		TreeSet<String> set = new TreeSet<>();
		
		for (int i = 0; i < str.length(); i++) {
			String tmp = str.substring(i, str.length());
			set.add(tmp);
		}
		
		Iterator it = set.iterator();
		while (it.hasNext()) {
			sb.append(it.next()).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
}
