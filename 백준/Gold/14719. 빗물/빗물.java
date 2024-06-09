import java.util.*;
import java.io.*;

// 골드 5. 빗물 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int H, W, res;
	static int[] heights;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		heights = new int[W];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<W; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=1; i<W-1; i++) {	// 처음, 마지막은 제외 
			int left = 0;
			int right = 0;
			
			for (int j=i-1; j>=0; j--) {	// 현재 칸 왼쪽 중 최대 높이 찾기 
				left = Math.max(left, heights[j]);
			}
			
			for (int j=i+1; j<W; j++) {		// 현재 칸 오른쪽 중 최대 높이 찾기 
				right = Math.max(right, heights[j]);
			}
			
			if (heights[i] < left && heights[i] < right) {	// 양쪽에 보다 높은 칸 있으면 빗물 고임 
				res += Math.min(left, right) - heights[i];
			}
		}
		
		System.out.println(res);
		br.close();
	}
}