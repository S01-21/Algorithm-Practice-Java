
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int R, C, sheep, wolf;
    static StringBuilder sb = new StringBuilder();
    static char[][] map;
    static boolean[][] vis;
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
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        vis = new boolean[R][C];
        for (int i = 0 ; i<R ; i++){
            String str = br.readLine();
            for (int j = 0 ; j <C; j++){
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < R; i++){
            for (int j = 0 ; j < C; j++){
                if (map[i][j] != '#' && !vis[i][j]){
                    bfs(i, j);
                }
            }
        }

        System.out.println(sheep + " " + wolf);
        br.close();
    }
    private static void bfs(int r, int c){
        Queue<Pair> queue = new ArrayDeque<>();
        vis[r][c] = true;
        queue.offer(new Pair(r, c));
        int sCnt = 0;
        int wCnt = 0;
        if (map[r][c] == 'v') wCnt++;
        if (map[r][c] == 'o') sCnt++;

        while(!queue.isEmpty()){
            Pair cur = queue.poll();
            for(int dir = 0 ; dir < 4; dir++){
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                if (map[nx][ny] == '#') continue;
                if (vis[nx][ny])    continue;
                if (map[nx][ny] == 'v') wCnt++;
                if (map[nx][ny] == 'o') sCnt++;
                vis[nx][ny] = true;
                queue.offer(new Pair(nx, ny));
            }
        }
        if (wCnt < sCnt)    sheep += sCnt;
        else wolf += wCnt;
    }
}