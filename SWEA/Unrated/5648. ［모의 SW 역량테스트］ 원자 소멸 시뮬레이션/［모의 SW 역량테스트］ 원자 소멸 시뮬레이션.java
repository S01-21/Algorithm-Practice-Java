import java.util.*;
import java.io.*;

// 원자 소멸 시뮬레이션 
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N, res;
	static Queue<Atom> atoms;
	static Map<Atom, Integer> pos;
	static Set<Integer> dead;
	static int[] energy;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static class Atom {
		int num, x, y, dir, k;

		public Atom(int num, int x, int y, int dir, int k) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.k = k;
		}
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Atom other = (Atom) obj;
			return x == other.x && y == other.y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 원자 개수
			atoms = new ArrayDeque<>();
			energy = new int[N];
			StringTokenizer st = null;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int dir = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				energy[i] = k;
				atoms.add(new Atom(i, x, y, dir, k));
			}

			dead = new HashSet<>();
			res = 0;

			while (true) {
				Queue<Atom> tmp = new ArrayDeque<>();
				pos = new HashMap<>();

				while (!atoms.isEmpty()) {
					Atom cur = atoms.poll();
					int ny = cur.y + dy[cur.dir];
					int nx = cur.x + dx[cur.dir];

					if (dead.contains(cur.num))
						continue;
					if (nx < 0 || nx >= 4001 || ny < 0 || ny >= 4001) {
						continue;
					}

					Atom nxt = new Atom(cur.num, nx, ny, cur.dir, cur.k);

					if (pos.containsKey(nxt)) { // 충돌 시 해당 위치에 있던 원자와 현재 원자 모두 죽여
						dead.add(pos.get(nxt));
						dead.add(cur.num);
					} else { // 충돌 X
						tmp.offer(nxt);
						pos.put(nxt, cur.num);
					}
				}

				if (tmp.isEmpty()) {
					break;
				}
				atoms = tmp;
			}

			for (int idx : dead) {
				res += energy[idx];
			}
			sb.append("#").append(tc).append(" ").append(res).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}