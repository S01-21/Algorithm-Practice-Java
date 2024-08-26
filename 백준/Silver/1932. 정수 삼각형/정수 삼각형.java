import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, res;
    static int[][] triangle, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        triangle = new int[N][N];
        dp = new int[N][N];
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<i+1; j++){
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i=0; i<N; i++){
            for (int j=0; j<i+1; j++){
                dp[i][j] = triangle[i][j];
                if (i > 0){
                    int tmp = dp[i-1][j];
                    if (j-1 >= 0){
                        tmp = Math.max(tmp, dp[i-1][j-1]);
                    }
                    dp[i][j] += tmp;
                }
                if (i == N-1){
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        System.out.println(res);
        br.close();
    }
}