import java.util.*;
import java.io.*;
import java.io.*;
import java.math.*;

public class Main {
	static int N;
	static HashMap<Long, Integer> map = new HashMap<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		for (int i = 0 ; i < N; i++) {
			Long num = Long.parseLong(br.readLine());
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		int max = 0;
		long res = 0;
		for (Map.Entry<Long, Integer> entry : map.entrySet()) {
			long key = entry.getKey();
			int value = entry.getValue();
			
			if (value > max || (value == max && key < res)) {
				max = value;
				res = key;
			}
		}
		System.out.println(res);
		br.close();
	}
}
