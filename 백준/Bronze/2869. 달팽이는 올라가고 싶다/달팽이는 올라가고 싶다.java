import java.util.*;
import java.io.*;

public class Main {
	static int A, B, V;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());	// 낮에 올라가는 높이
		B = Integer.parseInt(st.nextToken());	// 밤에 내려오는 높이
		V = Integer.parseInt(st.nextToken());	// 나무막대 길이
		
		int tmp = (V-A)/(A-B);
		if ((V-A)%(A-B) != 0) {
			tmp++;
		}
		
		System.out.println(tmp+1);
		br.close();
	}
}
