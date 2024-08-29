import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[][] cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            cnt = new int[N+1][2];
            cnt[0][0] = 1;
            cnt[0][1] = 0;
            if (N >= 1){
                cnt[1][0] = 0;
                cnt[1][1] = 1;
            }
            for (int i=2; i<=N; i++) {
                cnt[i][0] = cnt[i-1][0] + cnt[i-2][0];
                cnt[i][1] = cnt[i-1][1] + cnt[i-2][1];
            }
            sb.append(cnt[N][0]).append(" ").append(cnt[N][1]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}