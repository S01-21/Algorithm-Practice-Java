import java.util.*;
import java.io.*;

// 벌꿀채취
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N, M, C;
	static int[][] board;
	static int[] honeyA, honeyB;
	static boolean[] isSelected;
	static int maxProfitA, maxProfitB;
	static int maxProfit;	// 두 사람의 최대 수익 합 (결과)
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// NxN 크기
			M = Integer.parseInt(st.nextToken());	// 선택할 수 있는 벌통의 수 
			C = Integer.parseInt(st.nextToken());	// 한 사람이 채취할 수 있는 꿀의 최대 양
			board = new int[N][N];
			honeyA = new int[M];
			honeyB = new int[M];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxProfit = Integer.MIN_VALUE;
			for (int i=0; i<N; i++) {
				for (int j=0; j <= N-M; j++) {
					// A 벌통 선택 
					for (int k=j, idx=0; k < j+M; k++, idx++) {
						honeyA[idx] = board[i][k];
					}
					// A 벌통 수익 최대인 조합 찾기
					maxProfitA = Integer.MIN_VALUE;
					isSelected = new boolean[M];
					findMax(0, 1);
					
					// B 벌통 선택 가능한 경우 모두 찾으러 
					choiceB(i, j+M);

				}
			}
			sb.append(maxProfit).append("\n");
		}
		
		System.out.println(sb);
	}
	private static void choiceB(int x, int y) {
		
		for (int i=x; i<N; i++) {
			for (int j = 0; j <= N-M; j++) {
				if (i == x && j < y)	continue;
				// B 벌통 선택 
				for (int k=j, idx = 0; k<j+M; k++, idx++) {
					honeyB[idx] = board[i][k];
				}
				// B 벌통 수익 최대인 조합 찾기
				maxProfitB = Integer.MIN_VALUE;
				isSelected = new boolean[M];
				findMax(0, 2);
				
				// 현재 조합에서 A, B 최대 이익 찾았으니까 전체 최대 이익 갱신 
				maxProfit = Math.max(maxProfit, maxProfitA + maxProfitB);
			}
		}
	}
	
	private static void findMax(int cnt, int num) {	// 부분집합 
		if (cnt == M) {
			int sum = 0;
			int profit = 0;
			for (int i=0; i<M; i++) {
				if (isSelected[i] && num == 1) {
					sum += honeyA[i];
					profit += Math.pow(honeyA[i],2);
				}
				if (isSelected[i] && num == 2) {
					sum += honeyB[i];
					profit += Math.pow(honeyB[i],2);
				}
			}
			if (sum <= C) {
				if (num == 1) maxProfitA = Math.max(maxProfitA, profit);
				if (num == 2) maxProfitB = Math.max(maxProfitB, profit);
			}
			return;
		}
		isSelected[cnt] = true;
		findMax(cnt + 1, num);
		isSelected[cnt] = false;
		findMax(cnt + 1, num);
	}
}