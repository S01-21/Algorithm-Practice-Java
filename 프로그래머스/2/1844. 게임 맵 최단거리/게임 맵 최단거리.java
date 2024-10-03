import java.util.*;
import java.io.*;

class Solution {
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    int[][] dist;
    int[][] map;
    int n, m;
    class Pair{
        int x, y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public int solution(int[][] maps) {
        int answer = 0;
        n = maps.length;
        m = maps[0].length;
        dist = new int[n][m];
        map = maps;
        answer = bfs(0,0);
        
        return answer;
    }
    public int bfs(int x, int y){
        Queue<Pair> queue = new ArrayDeque<>();
        dist[x][y] = 1;
        queue.offer(new Pair(x, y));
        
        while(!queue.isEmpty()){
            Pair cur = queue.poll();
            if (cur.x == n-1 && cur.y == m-1){
                return dist[cur.x][cur.y];
            }
            for (int dir = 0; dir< 4; dir++){
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (dist[nx][ny] > 0)   continue;
                if (map[nx][ny] == 0)   continue;
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
                queue.offer(new Pair(nx, ny));
            }
        }
        return -1;
    }
}