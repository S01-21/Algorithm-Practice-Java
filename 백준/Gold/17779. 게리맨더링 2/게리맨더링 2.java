import java.util.*;
import java.io.*;

// 골드 3. 게리맨더링 2
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] map;
	static int res;
	static int[] dist;
	static int[][] area;
	static int[] pop;
	static int stdX, stdY;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		StringTokenizer st = null;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dist = new int[2];
		res = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				stdX = i;
				stdY = j;
				perm(0);
			}
		}

		System.out.println(res);
		br.close();
	}

	private static void perm(int cnt) {
		if (cnt == 2) {
			if (isOut()) {
				return;
			}
			area = new int[N + 1][N + 1];
			pop = new int[6];
			setFive();
			setArea();
			comparePop();
			return;
		}

		for (int i = 1; i <= N; i++) { // 중복순열로 d1, d2 설정
			dist[cnt] = i;
			perm(cnt + 1);
		}
	}

	private static void setFive() {
		for (int i = 0; i <= dist[0]; i++) {
			area[stdX + i][stdY - i] = 5; // 1번 경계선
			pop[5] += map[stdX + i][stdY - i];
			area[stdX + dist[1] + i][stdY + dist[1] - i] = 5; // 4번 경계선
			pop[5] += map[stdX + dist[1] + i][stdY + dist[1] - i];
		}
		for (int i = 0; i <= dist[1]; i++) {
			area[stdX + i][stdY + i] = 5; // 2번 경계선
			pop[5] += map[stdX + i][stdY + i];
			area[stdX + dist[0] + i][stdY - dist[0] + i] = 5; // 3번 경계선
			pop[5] += map[stdX + dist[0] + i][stdY - dist[0] + i];
		}
		pop[5] -= map[stdX][stdY] + map[stdX + dist[0]][stdY - dist[0]]
				+ map[stdX+dist[1]][stdY+dist[1]] + map[stdX+dist[0]+dist[1]][stdY + dist[1]-dist[0]];
		
		for (int i=stdX + 1; i <= stdX + dist[0] +dist[1]; i++) {
			List<Integer> pos = new ArrayList<>();
			for (int j=stdY-dist[0]; j <= stdY+dist[1]; j++) {
				if (area[i][j] == 5) {
					pos.add(j);
				}
			}
			if (pos.size() == 1)	break;	// 마지막꼭짓점 
			int start = pos.get(0);
            int end = pos.get(1);
            
            for(int j=start+1; j<end; j++) {
                area[i][j]= 5; 
                pop[5] += map[i][j];
            }
		}
	}

	private static void comparePop() {
		int max = -1;
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= 5; i++) {
			if (pop[i] > max) {
				max = pop[i];
			}
			if (pop[i] < min) {
				min = pop[i];
			}
		}
		res = Math.min(res, max - min);
	}

	private static void setArea() { // 선거구 표시
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int num = where(i, j);
				if (area[i][j] != 0)
					continue; // 이미 5번 선거구 표시됐으면 pass
				pop[num] += map[i][j]; // 인구 카운트
			}
		}
	}

	private static int where(int x, int y) {
		if (x < stdX + dist[0] && y <= stdY) { // 1번 선거구에 해당
			return 1;
		} else if (x <= stdX + dist[1] && y > stdY) { // 2번
			return 2;
		} else if (stdX + dist[0] <= x && stdY - dist[0] + dist[1] > y) { // 3번
			return 3;
		} else if (stdX + dist[1] < x && stdY - dist[0] + dist[1] <= y) { // 4번
			return 4;
		}
		return 5;
	}

	private static boolean isOut() {
		if ((stdX + dist[0] + dist[1] > N) || (stdY - dist[0] < 1) || (stdY + dist[1] > N))
			return true;
		return false;
	}
}