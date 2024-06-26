import java.util.*;
import java.io.*;

// 실버 5. 크로아티아 알파벳 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		for (int i=0; i<input.length(); i++) {
			switch(input.charAt(i)) {
			case 'c':
				if (i < input.length()-1 && (input.charAt(i+1) == '=' || input.charAt(i+1) == '-')) {
					res++;
					i++;
				} else {
					res++;
				}
				break;
			case 'd':
				if (i < input.length()-1) {
					if (input.charAt(i+1) == '-') {
						res++;
						i++;
						
					}
					else if (i < input.length()-2 && input.charAt(i+1) == 'z' && input.charAt(i+2) == '=') {
						res++;
						i+=2;
					}
					else {
						res++;
					}
				} else {
					res++;
				}
				break;
			case 'l':
			case 'n':
				if (i < input.length()-1 && input.charAt(i+1) == 'j') {
					i++;
					res++;
				} else {
					res++;
				}
				break;
			case 's':
			case 'z':
				if (i < input.length()-1 && input.charAt(i+1) == '=') {
					i++;
					res++;
				} else {
					res++;
				}
				break;
			default:
				res++;
			}
		}
		System.out.println(res);
		br.close();
	}
}