
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int N, M, x, y;
    static StringBuilder sb = new StringBuilder();
    static int[][] map, dist;
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
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M];

        for (int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 2){
                    dist[i][j] = -1;
                } else {
                    x = i;
                    y = j;
                }
            }
        }
        bfs(x, y);
        printResult();
        System.out.println(sb);
        br.close();
    }
    private static void bfs(int x, int y){
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(x, y));
        while (!queue.isEmpty()){
            Pair cur = queue.poll();
            for (int dir = 0 ; dir < 4; dir++){
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (dist[nx][ny] >= 0)  continue;
                if (map[nx][ny] == 0)   continue;
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
                queue.offer(new Pair(nx, ny));
            }
        }
    }
    private static void printResult(){
        for (int i = 0 ; i < N; i++){
            for (int j = 0 ; j < M; j++){
                if (dist[i][j] >= 0){
                    sb.append(dist[i][j]);
                } else if (map[i][j] == 0){
                    sb.append(0);
                } else {
                    sb.append(-1);
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
    }
}