import java.util.*;
import java.io.*;
public class Main {
    static int N, res;
    static int[][] map;
    static boolean[] link;
    static int scoreL, scoreS;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        link = new boolean[N];  // 링크 팀
        res = Integer.MAX_VALUE;
        powerSet( 0);    // 두 부분집합으로 나누기
        System.out.println(res);
        br.close();
    }
    static void powerSet(int cnt){
        if (cnt == N) {
            // 두 팀 비교
            scoreL = 0;
            scoreS = 0;
            for (int i=0; i<N-1; i++) {
                for (int j=i+1; j<N; j++) {
                    if (link[i] && link[j]) {   // 둘 다 링크 팀
                        scoreL += map[i][j] + map[j][i];
                    }
                    if (!link[i] && !link[j]) { // 둘 다 스타트팀
                        scoreS += map[i][j] + map[j][i];
                    }
                }
            }
            res = Math.min(res, Math.abs(scoreL - scoreS));
            return;
        }
        link[cnt]=true;
        powerSet(cnt+1);
        link[cnt]=false;
        powerSet(cnt+1);
    }
}