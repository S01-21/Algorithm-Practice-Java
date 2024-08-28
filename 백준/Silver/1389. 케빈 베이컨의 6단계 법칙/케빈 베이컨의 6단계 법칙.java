import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] floyd;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        floyd = new int[N+1][N+1];
        for (int i=1; i<=N; i++){
            for (int j=1; j<=N; j++){
                floyd[i][j] = 100_000_000;
            }
        }
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            floyd[a][b] = 1;
            floyd[b][a] = 1;
        }

        for (int k=1; k<=N; k++){
            for (int i=1; i<=N; i++){
                if (i == k) continue;
                for (int j=1; j<=N; j++){
                    if (j == k || j == i)   continue;
                    if (floyd[i][k] != 100_000_000 && floyd[k][j] != 100_000_000){
                        floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int minIdx = 0;
        for (int i=1; i<=N; i++){
            int sum = 0;
            for (int j=1; j<=N; j++){
                if (i == j) continue;
                sum += floyd[i][j];
            }
            if (sum < min){
                min = sum;
                minIdx = i;
            }
        }
        System.out.println(minIdx);
        br.close();
    }
}