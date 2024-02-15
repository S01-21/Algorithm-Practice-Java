import java.util.*;
import java.io.*;

// 무선 충전 
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int T, M, A;
	static int[] routeA, routeB;	// A, B의 이동 정보 
	static Charger[] chargers;		// 충전기 정보 
	static int[][] deltas = {{0,0}, {-1,0}, {0,1}, {1,0}, {0,-1}};	// 이동x-상-우-하-좌 
	static int aX, aY, bX, bY;	// 현재 A, B의 좌표 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());	// 총 이동시간
			A = Integer.parseInt(st.nextToken());	// 충전기 개수
			routeA = new int[M+1];
			routeB = new int[M+1];
			chargers = new Charger[A];
			st = new StringTokenizer(br.readLine());
			for (int i =1; i<=M; i++) {
				routeA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i =1; i<=M; i++) {
				routeB[i] = Integer.parseInt(st.nextToken());
			}
			for (int i =0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
					int y = Integer.parseInt(st.nextToken());
					int x = Integer.parseInt(st.nextToken());
					int c = Integer.parseInt(st.nextToken());
					int p = Integer.parseInt(st.nextToken());
					chargers[i] = new Charger(x, y, c, p);
			}
		
			// A, B의 초기 위치 저장 
			aX = 1;
			aY = 1;
			bX = 10;
			bY = 10;
			int maxSum = 0;	// 모든 사용자 충전량의 합 최대값 (결과) 
			for (int t=0; t <= M; t++) {
				int dirA = routeA[t];
				aX += deltas[dirA][0];
				aY += deltas[dirA][1];
				int dirB = routeB[t];
				bX += deltas[dirB][0];
				bY += deltas[dirB][1];
				
				//A, B 모든 조합에서 결과값 합산하고 최댓값 찾기 
				maxSum += getMax();
			}
			sb.append(maxSum)
			.append("\n");
		}
		System.out.println(sb);
	}
	
	private static int getMax() {
		int max = 0;
		for (int aBC = 0; aBC < A; aBC++) {
			for (int bBC = 0; bBC < A; bBC++) {
				int sum = 0;
				int scoreA = getScore(aBC, aX, aY);
				int scoreB = getScore(bBC, bX, bY);
				if (aBC != bBC) {	// 포함안된 애는 어차피 0이니까 더해주기 
					sum = scoreA + scoreB;
				} else {
					sum = Math.max(scoreA, scoreB);
				}
				if (sum > max)	max = sum;
			}
		}
		return max;
	}

	private static int getScore(int bc, int x, int y) {
		int d = Math.abs(x-chargers[bc].x) + Math.abs(y-chargers[bc].y);
		if (d <= chargers[bc].c) {
			return chargers[bc].p;
		} else {
			return 0;
		}
	}

	static class Charger{
		int x, y;
		int c, p;
		public Charger(int x, int y, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}
}