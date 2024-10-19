import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map<String, String> map = new HashMap<>();
		
		for (int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			String addr = st.nextToken();
			String pwd = st.nextToken();
			
			map.put(addr, pwd);
		}
		
		for (int i = 0; i <M; i++) {
			String str = br.readLine();
			
			sb.append(map.get(str)).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}