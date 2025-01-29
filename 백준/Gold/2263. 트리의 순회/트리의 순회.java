import java.util.*;
import java.io.*;

public class Main {
	static int N, postIndex;
	static Map<Integer, Integer> inOrder = new HashMap<>();
	static int[] postOrder;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N ; i++) {
			inOrder.put(Integer.parseInt(st.nextToken()), i);
		}
		
		postOrder = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N ; i++) {
			postOrder[i] = Integer.parseInt(st.nextToken());
		}
		
		postIndex = N-1;
		func(0, N-1, 0, N-1);
		
		System.out.println(sb);
		br.close();
	}
	private static void func(int inStart, int inEnd, int postStart, int postEnd) {
		if (inStart > inEnd || postStart > postEnd) return;
		
		int root = postOrder[postEnd];	// 루트 찾기
		int rootIdx = inOrder.get(root);
		int leftSize = rootIdx - inStart;	
		
		sb.append(root).append(" ");
		
		// 왼쪽 서브트리 먼저 탐색 (postStart부터 leftSize만큼)
        func(inStart, rootIdx - 1, postStart, postStart + leftSize - 1);
        // 오른쪽 서브트리 탐색 (leftSize 이후부터 postEnd 바로 앞까지)
        func(rootIdx + 1, inEnd, postStart + leftSize, postEnd - 1);
	}
	
}
