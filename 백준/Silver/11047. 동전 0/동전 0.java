import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] coins;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 동전 종류
        K = Integer.parseInt(st.nextToken());   // 목표 합
        coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        int res = 0;
        for (int i=N-1; i>=0; i--){
            if (coins[i] <= K){
                res += K/coins[i];
                K %= coins[i];
            }
        }
        System.out.println(res);
        br.close();
    }
}