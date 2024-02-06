import java.util.*;
import java.io.*;

public class Main {
	static class Pair implements Comparable<Pair> {
		int x, y;
		int dist;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public Pair(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		@Override
		public int compareTo(Pair o) {
			if (o.dist != this.dist) { // 거리 최소인 애가 우선
				return this.dist - o.dist;
			}
			if (this.x != o.x) { // 가장 위(행 최소) 우선
				return this.x - o.x;
			} else { // 행 같으면 가장 왼쪽(열 최소) 우선
				return this.y - o.y;
			}
		}
	}
	static boolean isOut(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
	static int N;
	static int size = 2; // 상어 크기
	static int count; // 먹은 물고기 개수 카운트
	static int[][] map;
	static int time; // 먹을 물고기가 없을 때까지 이동한 시간 (결과)
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[][] dist; // 이동거리 저장할 배열
	static PriorityQueue<Pair> pq; // 먹을 수 있는 물고기 후보

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		int sharkX = 0;
		int sharkY = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) { // 초기 아기 상어 좌표 저장
					sharkX = i;
					sharkY = j;
				}
			}
		}
		bfs(sharkX, sharkY);
	}

	private static void bfs(int x, int y) {
		pq = new PriorityQueue<>();
		dist = new int[N][N];
		Queue<Pair> queue = new LinkedList<>();
		dist[x][y] = 1;  // 방문여부 나타내기 위해 0말고 1로 시작 (time계산 시  -1)
		queue.add(new Pair(x, y));
		
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + deltas[dir][0];
				int ny = cur.y + deltas[dir][1];
				if (isOut(nx, ny))  // 범위체크 
					continue;
				if (dist[nx][ny] != 0)  // 방문했으면 패스 
					continue;
				if (map[nx][ny] > size)
					continue; // 상어보다 큰 물고기 못먹고 못지나감
				else if (map[nx][ny] < size && map[nx][ny] != 0) { // 상어보다 작은 물고기 먹이 후보에 추가
					dist[nx][ny] = dist[cur.x][cur.y] + 1;
					pq.add(new Pair(nx, ny, dist[nx][ny]));
				} else if (map[nx][ny] == 0 || map[nx][ny] == size) { // 지나갈 수 있으면 큐에 넣고 진행
					queue.add(new Pair(nx, ny));
					dist[nx][ny] = dist[cur.x][cur.y] + 1;
				}
			}
		}

		if (pq.size() == 0) {  // 더 이상 먹을 물고기 없으면 종료 
			System.out.println(time);
			System.exit(0);
		}

		Pair pick = pq.peek();  // 우선순위 1번 위치 
		map[pick.x][pick.y] = 9; // 상어 이동
		map[x][y] = 0;
		count++;
		if (count == size) {  // 상어 크기만큼 물고기 먹으면 크기 +1
			size++;
			count = 0;
		}		
		time += pick.dist - 1;  // 시작을 1로 해서 소요시간 -1 해주기 

		bfs(pick.x, pick.y);
	}
}