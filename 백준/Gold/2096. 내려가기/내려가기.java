import java.util.*;
import java.io.*;

// 골드 5. 내려가기 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] map;
	static int[][] dpMax, dpMin;
	static int min, max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][3];
		dpMax = new int[N][3];
		dpMin = new int[N][3];
		StringTokenizer st = null;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int j=0; j<3; j++) {
			dpMax[0][j] = dpMin[0][j] = map[0][j];
		}
		
		for (int i=1; i<N; i++) {
			for (int j=0; j<3; j++) {
				int minTmp = dpMin[i-1][j];
				int maxTmp = dpMax[i-1][j];
				if (j-1 >= 0) {
					minTmp = Math.min(minTmp, dpMin[i-1][j-1]);
					maxTmp = Math.max(maxTmp, dpMax[i-1][j-1]);
				}
				if (j+1 < 3) {
					minTmp = Math.min(minTmp, dpMin[i-1][j+1]);
					maxTmp = Math.max(maxTmp, dpMax[i-1][j+1]);
				}
				dpMin[i][j] = minTmp + map[i][j];
				dpMax[i][j] = maxTmp + map[i][j];
			}
		}
		
		min = dpMin[N-1][0];
		max = dpMax[N-1][0];
		for (int j = 1; j <3; j++) {
			if (min > dpMin[N-1][j]) {
				min = dpMin[N-1][j];
			}
			if (max < dpMax[N-1][j]) {
				max = dpMax[N-1][j];
			}
		}
		System.out.println(max + " " + min);
	}
}