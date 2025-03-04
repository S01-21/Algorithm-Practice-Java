import java.util.*;
import java.io.*;

public class Main {
	static int N, res;
	static boolean[] check;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		check = new boolean[10000];
		for (int i = 1; i < 10000; i++) {
			func(i);
		}
		
		for (int i = 1; i < 10000; i++) {
			if (!check[i]) {
				sb.append(i).append("\n");
			}
		}
		System.out.println(sb);
		br.close();
	}
	private static void func(int num) {
		if (check[num])	return;
		int tmp = num;
		int sum = tmp;
		while (tmp > 0) {
			sum += tmp%10;
			tmp /= 10;
		}
		if (sum == num || sum >= 10000)	return;
		func(sum);
		check[sum] = true;
	}
}
