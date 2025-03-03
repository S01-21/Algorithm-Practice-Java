import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	static int N, res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		boolean isTag = false;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			
			if (c == '<') {
				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				isTag = true;
				sb.append("<");
			} else if (c == '>') {
				isTag = false;
				sb.append(">");
			}
			
			else if (c == ' ') {	
				if (!isTag) {
					while (!stack.isEmpty()) {
						sb.append(stack.pop());
					}
				}
				sb.append(" ");
			} else {
				if (isTag) {
					sb.append(c);
				} else {
					stack.push(c);
				}
			}
		}
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
		br.close();
	}
}
