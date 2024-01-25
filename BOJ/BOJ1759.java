import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static boolean isVowel(char c) {
		return c=='a' || c=='e' || c=='i' || c=='o' || c=='u';
	}
	static int L, C;
	static char[] input;
	static int[] code;	//input의 인덱스 값을 결과에 저장
	static boolean[] apb = new boolean[26];	//알파벳 사용여부 체크
	static int v_cur, c_cur;
	static StringBuilder sb = new StringBuilder();
	
	static void func(int k) {
		if (k == L) {	//L자리 다 차면 출력
			if (v_cur < 1 || c_cur < 2)	return;	//모음 하나도 없으면 return
			
			for (int i=0; i<L; i++) {
				sb.append(input[code[i]]);
			}
			sb.append("\n");
			return;
		}
		int st = 0;
		if (k != 0) {
			st = code[k-1] + 1;
		}
		for (int i=st; i<C; i++) {
			if (!apb[input[i]-'a']) {
				if (isVowel(input[i])) {	
					v_cur++;
				} else {
					c_cur++;
				}
				apb[input[i]-'a'] = true;
				code[k] = i;
				func(k + 1);
				apb[input[i]-'a'] = false;
				if (isVowel(input[i])) {	
					v_cur--;
				} else {
					c_cur--;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		input = new char[C];
		code = new int[L];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<C; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(input);	//input배열을 알파벳순으로 정렬
		
		func(0);
		
		System.out.println(sb);
	}

}
