import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();

		String [] input = new String[500001];
		LinkedList<String> list = new LinkedList<>();
		HashMap<String, Integer> map = new HashMap<>();
		
		for (int i=0; i<L; i++) {
			input[i] = br.readLine();
			map.put(input[i], i);
		}
			
		
		for (int i=0; i<L; i++) {
			if (map.get(input[i]) == i) {
				list.add(input[i]);
			}
		}
		
		
		// 선착순 K명 안에 든 학생들 출력
		int cntK = 0;
		for (String num : list) {
			if (cntK == K)	break;
			sb.append(num).append("\n");
			cntK++;
		}
		System.out.println(sb.toString());
	}

}
