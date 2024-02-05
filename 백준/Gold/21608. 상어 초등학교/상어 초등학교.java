

import java.util.*;
import java.io.*;

public class Main {
	static class Pair implements Comparable<Pair>{
		int x, y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Pair o) {
			if (o.x != this.x) {	// 행 작은것이 우선 
				return this.x - o.x;
			} else {	// 행 같을 시 열 작은 것이 우선 
				return this.y - o.y;
			}
		}
		
	}
	static int N;
	static int[][] seat;	// 결과 자리 
	static int[] stdOrder;	// 자리 선정 순서
	static Map<Integer, Pair> result = new HashMap<>();	// 학생 별 결과 자리 
	static int[][] prefer; // 학생 별 좋아하는 학생
	static int[][] dist; 
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean isOut(int x, int y) {	// 범위 체크 함수 
		return x < 0 || x >= N || y < 0 || y >= N;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		seat = new int[N][N];
		stdOrder = new int[N*N];
		prefer = new int[N*N+1][4];
		
		for (int i = 0 ; i< N*N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int std = Integer.parseInt(st.nextToken());	// 학생 번호
			stdOrder[i] = std;
			for (int j = 0 ; j < 4; j++) {
				// 좋아하는 학생 번호 저장
				prefer[std][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 첫번째 순서 -> (1,1) 지정
		seat[1][1] = stdOrder[0];	
		result.put(stdOrder[0], new Pair(1, 1));
		
		for (int idx = 1; idx < N*N; idx++) {
			dist = new int[N][N];
			int max1 = 0;
			for (int p: prefer[stdOrder[idx]]) {	// idx학생의 선호 학생들 탐색 
				if (result.containsKey(p)) {	// 선호 학생이 맵에 존재하면 해당 학생 주위로 탐색 
					int r = result.get(p).x;
					int c = result.get(p).y;
					for (int dir = 0; dir < 4; dir++) {
						int nr = r + dr[dir];
						int nc = c + dc[dir];
						if (isOut(nr, nc))	continue;
						if (seat[nr][nc] == 0) {	// 선호 학생 주변 빈칸에 가중치 부여 
							dist[nr][nc]++;
							max1 = Math.max(max1, dist[nr][nc]);
						}
					}
				}
			}
			int max2 = max1;
			for (int i = 0 ; i< N; i++) {
				for (int j = 0 ; j<N; j++) {
					if (dist[i][j] == max1 && seat[i][j]==0) {	// 1번 조건에서 가중치 최대인 좌표들 중에 인접한 빈칸 개수 최대인 좌표 구하기 
						for (int dir = 0 ; dir<4; dir++) {
							int nr = i + dr[dir];
							int nc = j + dc[dir];
							if (isOut(nr, nc))	continue;
							if (seat[nr][nc] == 0) {	// 주위에 있는 빈칸 만큼 가중치 부여 
								dist[i][j]++;	
								max2 = Math.max(max2, dist[i][j]);
							}
						}
					}
				}
			}
			PriorityQueue<Pair> pq = new PriorityQueue<>();
			for (int i = 0; i<N; i++) {
				for (int j = 0; j<N; j++) {
					if (dist[i][j] == max2 && seat[i][j] == 0) {	// 2번 조건까지 동일한 가중치인 경우 행과 열 기준 정렬하기 위해 우선순위큐 사용 
						pq.add(new Pair(i, j));
					}
				}
			}
			Pair pick = pq.peek();	// 우선순위큐의 가장 앞에 있는 원소가 해당 학생의 최종 자리 
			seat[pick.x][pick.y] = stdOrder[idx];	
			result.put(stdOrder[idx], new Pair(pick.x, pick.y));
		}
		int sum = 0;
		for (int i = 0 ; i<N; i++) {
			for (int j = 0; j<N; j++) {
				int std = seat[i][j];	// 각 자리의 학생들마다 선호하는 학생과 얼마나 인접했는지 카운트  
				int cnt = 0;
				for (int dir = 0; dir < 4; dir++) {
					int nr = i + dr[dir];
					int nc = j + dc[dir];
					if (isOut(nr, nc))	continue;
					for (int k = 0; k < 4; k++) {
						if (prefer[std][k] == seat[nr][nc]) {
							cnt++;	
						}
					}
				}
				switch(cnt) {
				case 0:
					break;
				case 1:
					sum += 1;
					break;
				case 2:
					sum += 10;
					break;
				case 3:
					sum += 100;
					break;
				case 4:
					sum += 1000;
					break;
				}
			}
		}
		System.out.println(sum);
	}
}