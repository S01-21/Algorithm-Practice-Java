import java.util.*;
import java.io.*;

public class Main {
	static int N, res;
	static int[] balls;
	static ArrayList<Integer> tmpBalls;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		balls = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			balls[i] = Integer.parseInt(st.nextToken());
		}
		
		tmpBalls = new ArrayList<>();
		for (int num : balls) {
			tmpBalls.add(num);
		}
		func(0);
		
		System.out.println(res);
		br.close();
	}
	
	// 현재까지 획득 에너지
	private static void func(int sum) {
		if (tmpBalls.size() == 2) {
			res = Math.max(res, sum);
			return;
		}
		for (int i = 1; i < tmpBalls.size() - 1; i++) {
			int energy = tmpBalls.get(i-1) * tmpBalls.get(i+1);
			int cur = tmpBalls.remove(i);
			
			func(sum + energy);
			
			tmpBalls.add(i, cur);
		}
	}
}
