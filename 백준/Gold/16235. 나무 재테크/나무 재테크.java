import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, K;
	static int[][] A;
	static int[][] map;
	static List<Tree>[][] treeMap;
	static ArrayList<Tree> treeList;
	static Queue<Tree> dead;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 12시 방향부터 시계방향
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static class Tree implements Comparable<Tree> {
		int x, y, age;

		public Tree(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}

		@Override
		public String toString() {
			return "Tree [x=" + x + ", y=" + y + ", age=" + age + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // map 크기
		M = Integer.parseInt(st.nextToken()); // 나무 개수
		K = Integer.parseInt(st.nextToken()); // k년
		treeList = new ArrayList<>();
		dead = new ArrayDeque<>();
		treeMap = new List[N+1][N+1];
		for (int i=0; i<=N; i++) {
			for (int j=0; j<=N; j++) {
				treeMap[i][j] = new ArrayList<>();
			}
		}
		A = new int[N + 1][N + 1]; // 겨울에 추가될 양분배열
		map = new int[N + 1][N + 1]; // 현재 땅의 양분 상태
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = 5; // 초기 양분은 5
			}
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) { // M개 나무 정보 저장
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			treeMap[x][y].add(new Tree(x, y, age));
		}

		for (int i = 0; i < K; i++) { // K년 반복
			spring();
			summer();
			autumn();
			winter();
		}
		
		int cnt = 0;
		for (int i=1; i<=N ; i++) {
			for (int j=1; j<=N; j++) {
				cnt += treeMap[i][j].size();
			}
		}
		System.out.println(cnt);
		br.close();
	}

	

	private static void winter() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] += A[i][j];
			}
		}
	}

	private static void autumn() {
		for (int i=1; i<=N; i++) {
			for (int j=1;j <= N; j++) {
				int size = treeMap[i][j].size();
				for (int k = 0; k < size; k++) {
					Tree cur = treeMap[i][j].get(k);
					if (cur.age % 5 != 0) continue; // 나이가 5의 배수인 나무만 번식
					for (int dir = 0; dir < 8; dir++) {
						int nx = cur.x + dx[dir];
						int ny = cur.y + dy[dir];
						if (nx <= 0 || nx > N || ny <= 0 || ny > N) continue;
						treeMap[nx][ny].add(new Tree(nx, ny, 1));
					}
				}
			}
		}
		
		
	}
	private static void summer() {
		while (!dead.isEmpty()) {
			Tree cur = dead.poll();
			map[cur.x][cur.y] += cur.age/2; 
		}
	}

	private static void spring() {
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				Collections.sort(treeMap[i][j]);
				
				for (int k = 0; k < treeMap[i][j].size(); k++) {
					
					Tree cur = treeMap[i][j].get(k);
					if (cur.age > map[cur.x][cur.y]) { // 땅에 양분 부족해서 못먹으면 죽음
						dead.add(new Tree(cur.x, cur.y, cur.age));
						treeMap[i][j].remove(k--); // 죽여
						continue;
					}
					map[cur.x][cur.y] -= cur.age;
					cur.age++;
				}
			}
		}

		
	}
}