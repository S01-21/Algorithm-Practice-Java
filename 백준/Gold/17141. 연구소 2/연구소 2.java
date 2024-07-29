import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    static boolean[] check;
    static boolean[][] vis;
    static int N, M, res, wall;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static class Pair{
        int x, y, dist;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Pair(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    static List<Pair> pos = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // NxN map
        M = Integer.parseInt(st.nextToken());   // 바이러스 개수
        map = new int[N][N];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2){
                    pos.add(new Pair(i, j));
                } else if (map[i][j] == 1){
                    wall++;
                }
            }
        }

        res = Integer.MAX_VALUE;
        // pos 개수에서 M개 뽑기
        check = new boolean[pos.size()];
        comb(0, 0);

        if (res == Integer.MAX_VALUE){
            res = -1;
        }
        sb.append(res);
        System.out.println(sb);
        br.close();
    }

    private static void comb(int st, int cnt) {
        if (cnt == M){  // M개 뽑으면 bfs 시작
            bfs();
            return;
        }
        for (int i=st; i < pos.size(); i++){
            check[i] = true;
            comb(i+1, cnt+1);
            check[i] = false;
        }
    }

    private static void bfs() {
        Queue<Pair> queue = new ArrayDeque<>();
        vis = new boolean[N][N];

        for (int i=0; i<pos.size(); i++){
           if (check[i]){   // 선택한 M개의 위치에 해당하는 칸
               vis[pos.get(i).x][pos.get(i).y] = true;
               queue.add(new Pair(pos.get(i).x, pos.get(i).y, 0));
           }
        }

        int max = -1;
        int virus = 0;
        while(!queue.isEmpty()){
            Pair cur = queue.poll();
            virus++;
            if (cur.dist > res){
                return;
            }
            if (cur.dist > max){
                max = cur.dist;
            }
            for (int dir = 0; dir < 4; dir++){
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (map[nx][ny] == 1 || vis[nx][ny])   continue;
                queue.add(new Pair(nx, ny, cur.dist+1));
                vis[nx][ny] = true;
            }
        }
        if (N*N-wall == virus){ // 벽 제외 모든 칸에 전파 성공했을 때만 최솟값 갱신
            res = Math.min(res, max);
        }

    }
}