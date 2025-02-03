import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		if (N == 1) {
			System.out.println(1);
		} else {
			int res = 1;
			int min = 2;
			while (min <= N) {
				min += 6 * res;
				res++;
			}
			System.out.println(res);
		}
		br.close();
	}
}
