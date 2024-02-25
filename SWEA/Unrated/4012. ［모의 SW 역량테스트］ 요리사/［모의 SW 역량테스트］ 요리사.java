import java.util.*;
import java.io.*;
 
public class Solution {
    private static StringBuilder sb = new StringBuilder();
    static int N;
    static int[][] food;
    static boolean[] vis;
    static int minDiff;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            N = Integer.parseInt(br.readLine());
            food = new int[N][N];
            vis = new boolean[N];
             
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    food[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            minDiff = Integer.MAX_VALUE;
            func(0, 0);
             
             
            sb.append(minDiff).append("\n");
        }
 
        System.out.println(sb);
    }
    // cnt: 방문한 개수
    // start: 선택할 인덱스 시작순서
    static void func(int cnt, int start) {
        if (cnt == N/2) {
            compareDiff();
            return;
        }
         
        for (int i = start; i < N; i++) {
            if (!vis[i]) {
                vis[i] = true;
                func(cnt + 1, i + 1);
                vis[i] = false;
            }
        }
    }
     
    private static void compareDiff() {
        int aSum = 0;   // vis==true인 재료가 A음식
        int bSum = 0;   // vis==false인 재료가 B음식
         
        for (int i = 0 ; i< N-1; i++) {
            for (int j = i+1; j < N; j++) {
                    if (vis[i] && vis[j]) { // 둘다 A음식 재료
                        aSum += food[i][j] + food[j][i];
                    }
                    else if (!vis[i] && !vis[j]) {  // 둘다 B음식 재료
                        bSum += food[i][j] + food[j][i];
                    }
            }
        }
         
        minDiff = Math.min(minDiff, Math.abs(aSum-bSum));
    }
}