
import java.util.*;
import java.io.*;

public class Main {
	static int brkCnt;	// 괄호 쌍 개수 
	static char[] formula;	// 	입력받은 수식 
	static StringBuilder sb = new StringBuilder();
	static List<int[]> bracket = new ArrayList<>();	// 괄호 쌍 저장 
	static boolean[] isDeleted;	// 삭제한 괄호체크 
	static TreeSet<String> result = new TreeSet<>();	// 결과 저장 (중복제거와 사전순 정렬 위해 트리셋) 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		formula = str.toCharArray();
		Stack<Integer> stack = new Stack<>();
		
		brkCnt = 0;
		for (int i = 0; i < formula.length; i++) {
			if (formula[i] == '(') {	// 여는괄호 스택에 저장하고 괄호쌍 개수 추가 
				stack.push(i);
				brkCnt++;
			} else if (formula[i] == ')') {	// 닫는괄호면 가장최근 여는괄호와 쌍으로 저장 
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
				tmp += formula[i];	// 삭제되지 않은 글자만 출력 
			}
			if (flag == true) {
				result.add(tmp);	// 한번 이상 괄호 삭제된 결과만 저장 
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
