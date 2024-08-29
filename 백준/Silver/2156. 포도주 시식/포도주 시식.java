import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] wine, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        wine = new int[N+1];
        dp = new int[N+1];
        for (int i=1; i<=N; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }
        dp[1] = wine[1];
        if (N >= 2){
            dp[2] = wine[1] + wine[2];
        }
        for (int i=3; i<=N; i++) {
            int tmp = Math.max(dp[i-1], dp[i-2] + wine[i]); // O O X, O X O
            dp[i] = Math.max(tmp, dp[i-3] + wine[i-1] + wine[i]);   // X O O
        }
        System.out.println(dp[N]);
        br.close();
    }
}