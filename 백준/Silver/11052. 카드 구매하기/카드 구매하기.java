import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        dp = new int[N+1];
        for (int i=1; i<=N; i++) {
            dp[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=2; i<=N; i++){
            int tmp = dp[i-1] + dp[1];
            if (i%2 == 0){
                tmp = Math.max(tmp, dp[i/2]*2);
            }
            int half = i;
            while (half >= i/2){
                tmp = Math.max(tmp, dp[half] + dp[i-half]);
                half--;
            }
            dp[i] = Math.max(dp[i], tmp);
        }
        System.out.println(dp[N]);
        br.close();
    }
}