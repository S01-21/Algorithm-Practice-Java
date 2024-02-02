
import java.util.*;
import java.io.*;

public class Main {
	static int brkCnt;
	static char[] formula;
	static StringBuilder sb = new StringBuilder();
	static List<int[]> bracket = new ArrayList<>();
	static boolean[] isDeleted;
	static TreeSet<String> result = new TreeSet<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		formula = str.toCharArray();
		Stack<Integer> stack = new Stack<>();
		
		brkCnt = 0;
		for (int i = 0; i < formula.length; i++) {
			if (formula[i] == '(') {
				stack.push(i);
				brkCnt++;
			} else if (formula[i] == ')') {
				bracket.add(new int[] {stack.pop(), i});
			}
		}
		
		isDeleted = new boolean[formula.length]; 
		func(0);
		
		for (String s : result) {
			sb.append(s).append("\n");
		}
		System.out.println(sb);
		
	}
	private static void func(int cnt) {	// cnt: 현재까지 처리한 괄호 개수
		if (cnt == brkCnt) {
			boolean flag = false;
			String tmp = "";
			for (int i = 0; i<formula.length; i++) {
				if (isDeleted[i]) {
					flag = true;	// 괄호가 하나도 삭제되지 않은 수식을 제외하기 위한 플래그 
					continue;
				}
				tmp += formula[i];
			}
			if (flag == true) {
				result.add(tmp);
			}
			return;
		}
		
		int[] curBrk = bracket.get(cnt);
		isDeleted[curBrk[0]] = false;	
		isDeleted[curBrk[1]] = false;
		func(cnt + 1);
		isDeleted[curBrk[0]] = true;	
		isDeleted[curBrk[1]] = true;
		func(cnt + 1);
	}
}