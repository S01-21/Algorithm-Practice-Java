import java.util.*;
import java.io.*;


// N-Queen 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] col;
	static int res;		// 퀸 놓을 방법의 수 (결과) 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col = new int[N + 1];	// 각 행마다 퀸 놓을 열값 저장 (0인덱스 사용x) 
		
		back(1);	// 첫번째 행부터 시작 
		
		System.out.println(res);
	}
	
	// row: 확인할 행 
	private static void back(int row) {
		for (int k=1; k<row-1; k++) {	// 이전행까지 놓았던 퀸 위치와 현재 행 비교 
			if (col[row-1] == col[k] || Math.abs(col[row-1] - col[k]) == row-1 - k) {
				// 같은 열이나 대각선(행값 차이와 열값차이 같음)에 이미 퀸 놓았으면 불가능 
				return; 
			}
		}
		if (row > N) {	// 마지막행까지 퀸 놓는 데 성공했으면 종료 
			res++;
			return;
		}
		for (int j=1; j <= N; j++) {	// 퀸 놓을 열 정하기 
			col[row] = j;
			back(row + 1);	// 다음 행 정하러 
		}
	}
}