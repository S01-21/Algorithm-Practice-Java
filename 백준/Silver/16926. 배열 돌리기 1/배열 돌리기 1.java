import java.util.*;
import java.io.*;

public class Main {
	static int N, M, R;
	static int[][] array;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		array = new int[N][M];
		
		for (int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j< M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int rotateCnt = Math.min(N, M) / 2;
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < rotateCnt; j++) {
				int startX = j;
				int startY = j;
				int endX = N-1-j;
				int endY = M-1-j;
				int tmp = array[startX][startY];
				
				// 윗변: 오른쪽 -> 왼쪽 이동 
				for (int k = startY; k < endY; k++) {
					array[startX][k] = array[startX][k+1];
				}
				
				// 우변: 아랫쪽 -> 윗쪽 이동 
				for (int k = startX; k < endX; k++) {
					array[k][endY] = array[k+1][endY];
				}
				
				// 아랫변: 왼쪽 -> 오른쪽 이동 
				for (int k = endY; k > startY; k--) {
					array[endX][k] = array[endX][k-1];
				}
				
				// 좌변: 윗쪽 -> 아랫쪽 이동 
				for (int k = endX; k > startX; k--) {
					array[k][startY] = array[k-1][startY];
				}
				
				// 처음 빼냈던 값 다시 넣기 
				array[startX+1][startY] = tmp;
			}
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0 ; j < M ; j++) {
				sb.append(array[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}
}