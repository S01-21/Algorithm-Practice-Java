
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int N, L, R;
    static StringBuilder sb = new StringBuilder();
    static int[][] map, vis;
    static class Pair{
        int x, y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0 ; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j <N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = 0;
        while(true){
            vis = new int[N][N];

            int countryNum = 0;
            for (int i = 0 ; i  <N; i++){
                for (int j = 0 ; j < N; j++){
                    if (vis[i][j] == 0){
                        countryNum++;
                        bfs(i, j, countryNum);
                    }
                }
            }

            if (countryNum == N*N)    break;  // 인구이동이 일어나지 않으면 종료
            res++;
        }
        System.out.println(res);
        br.close();
    }

    private static void bfs(int i, int j, int countryNum) {
        Queue<Pair> queue = new ArrayDeque<>();
        vis[i][j] = countryNum;
        queue.offer(new Pair(i, j));

        int cnt = 1;
        int sum = map[i][j];
        while(!queue.isEmpty()){
            Pair cur = queue.poll();
            for (int dir = 0 ; dir <4; dir++){
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (vis[nx][ny] != 0)   continue;
                if (Math.abs(map[nx][ny] - map[cur.x][cur.y]) < L
                || Math.abs(map[nx][ny] - map[cur.x][cur.y])> R) continue;
                queue.offer(new Pair(nx, ny));
                vis[nx][ny] = countryNum;
                cnt++;
                sum += map[nx][ny];
            }
        }

        int pop = sum / cnt;
        for (int r = 0 ; r < N; r++){
            for (int c = 0 ; c< N; c++){
                if (vis[r][c] == countryNum){
                    map[r][c] = pop;
                }
            }
        }
    }
}