import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception{
				
		Scanner sc = new Scanner(System.in);
		
		
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			Stack<Character> front_stack = new Stack<>();
			Stack<Character> back_stack = new Stack<>();
			String str = sc.next();
			
			
			for (int j=0; j<str.length(); j++) {
				char c = str.charAt(j);
				
				if (c == '<') {
					if (!front_stack.isEmpty()) {
						back_stack.push(front_stack.pop());
					}
				} else if (c=='>') {
					if (!back_stack.isEmpty()) {
						front_stack.push(back_stack.pop());
					}
				} else if (c == '-') {
					if (!front_stack.isEmpty()) {
						front_stack.pop();
					}
				} else {
					front_stack.push(c);
				}
				
			}
			
			while (!front_stack.isEmpty()) {
				back_stack.push(front_stack.pop());
			}
			while (!back_stack.isEmpty()) {
				sb.append(back_stack.pop());
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
		sc.close();
	}

}
