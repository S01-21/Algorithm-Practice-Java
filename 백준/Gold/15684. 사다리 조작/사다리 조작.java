import java.util.*;
import java.io.*;

// 사다리 조작 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, H;
	static int res;		// 추가할 가로선 개수 
	static int[] addHor;
	static ArrayList<Integer> orgHor;
	static boolean[] isSelected;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 세로선 개수 
		M = Integer.parseInt(st.nextToken());	// 초기 가로선 개수 
		H = Integer.parseInt(st.nextToken());	// 세로선마다 가로선 놓을 수 있는 위치 개수 
		
		isSelected = new boolean[(N-1)*H+2];
		orgHor = new ArrayList<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	// 점선 번호 
			int b = Integer.parseInt(st.nextToken());	// 세로선 번호 
			
			orgHor.add((a-1)*(N-1) + b);	// 해당 가로선 번호 추가 
			isSelected[(a-1)*(N-1) + b] = true;
		}
		
		res = -1;
		
		for (int i=0; i <= 3; i++) {	// 0개 ~ 빈 가로칸개수만큼 가로선 추가
			if (res != -1) {
				break;
			}
			addHor = new int[i];
			comb(1, 0, i);
		}
		
		System.out.println(res);
	}
	private static void comb(int st, int cnt, int target) {
		if (cnt == target) {
			if (check()) {
				res = target;
			}
			return;
		}
		for (int i=st; i <= (N-1)*H; i++) {
			if (isSelected[i])	continue;
			isSelected[i] = true;
			addHor[cnt] = i;
			comb(i+1, cnt+1, target);
			isSelected[i] = false;
		}
	}
	private static boolean check() {
		ArrayList<Integer> curHor = (ArrayList<Integer>) orgHor.clone();
		
		for (int i : addHor) {
			curHor.add(i);
		}
		
		for (int j=1; j<= N; j++) {
			int cur = j;
			for (int i=1; i<=H; i++) {
				int left = (i-1)*(N-1) + cur - 1;
				int right = (i-1)*(N-1) + cur;
				if (isSelected[left] && cur - 1 >= 1) {
					cur--;
				} else if (isSelected[right] && cur + 1 <= N) {
					cur++;
				}
			}
			if (cur != j) {
				return false;
			}
		}
		return true;
	}
}