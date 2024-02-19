import java.util.*;
import java.io.*;

// 보물상자 비밀번호 
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N, K;
	static int res;
	static String numbers;	// 입력받을 숫자 	
	static ArrayList<Integer> list;		// 자물쇠에서 나올 수 있는 숫자 목록 
	static Deque<Character> dq;	// 자물쇠 번호 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			numbers = br.readLine();
			list = new ArrayList<>();
			dq = new ArrayDeque<>();
			
			for (int i=0; i<N; i++) {	// 현재 numbers 상태 덱에 저장 
				dq.addLast(numbers.charAt(i));
			}
			
			for (int i=0; i<N/4; i++) {	// N/4번 회전 
				calc();
				rotate();
			}
			
			Collections.sort(list, Collections.reverseOrder());
			res = list.get(K-1);
			
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}
	static void calc() {
		for (int i=0; i<N; i+= N/4) {
			String hex = "";
			for (int j=i; j<i + N/4; j++) {
				hex += numbers.charAt(j);
			}
			if (!list.contains(Integer.parseInt(hex, 16))) {
				list.add(Integer.parseInt(hex, 16));
			}
		}
	}
	static void rotate() {
		numbers = "";
		char tmp = dq.removeLast();
		dq.addFirst(tmp);	// 오른쪽으로 한칸씩 밀기 
		for (int i=0; i<N; i++) {
			numbers += dq.removeFirst();
		}
		for (int i=0; i<N; i++) {	// 현재 numbers 상태 덱에 저장 
			dq.addLast(numbers.charAt(i));
		}
	}
}