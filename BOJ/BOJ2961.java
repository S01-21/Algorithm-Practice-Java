import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] ingredients;
	static boolean[] isSelected;
	static int min = Integer.MAX_VALUE;
	static boolean check = false;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 재료 개수
		ingredients = new int[N][2];
		isSelected = new boolean[N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ingredients[i][0] = Integer.parseInt(st.nextToken());	// 신맛
			ingredients[i][1] = Integer.parseInt(st.nextToken());	// 쓴맛
		}
		
		func(0, 1, 0);

		System.out.println(min);
	}
	// cnt: 처리한 재료 개수
	// sour: 현재까지 신맛
	// bitter: 현재까지 쓴맛
	static void func(int cnt, int sour, int bitter) {	
		if (check)
			min = Math.min(min, Math.abs(sour - bitter));
		if (cnt == N) return;
		
		isSelected[cnt] = true;
		check = true;
		func(cnt + 1, sour*ingredients[cnt][0], bitter+ingredients[cnt][1]);
		isSelected[cnt] = false;
		check = false;
		func(cnt + 1, sour, bitter);
	}
}
