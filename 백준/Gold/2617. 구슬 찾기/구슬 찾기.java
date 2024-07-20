import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, res;
    static int[][] floyd;
    static int[] bigger, smaller;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 구슬 개수
        M = Integer.parseInt(st.nextToken());   // 저울에 올린 쌍의 개수

        floyd = new int[N+1][N+1];

        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            floyd[a][b] = 1;    // a > b
            floyd[b][a] = -1;   // a < b
        }

        for (int k=1; k<=N; k++) {
            for (int i=1; i<=N; i++) {
                if (i==k)   continue;
                for (int j=1; j<=N; j++) {
                    if (j==k || i==j)   continue;
                    if (floyd[i][k] == floyd[k][j] && floyd[i][k] != 0){
                        floyd[i][j] = floyd[i][k];
                    }
                }
            }
        }

        bigger = new int[N+1];
        smaller = new int[N+1];
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                if (floyd[i][j] == 1){
                    bigger[i]++;
                }
                if (floyd[i][j] == -1){
                    smaller[i]++;
                }
            }
        }

        for (int i=1; i<=N; i++) {
            if (bigger[i] >= (N+1)/2)   res++;
            if (smaller[i] >= (N+1)/2)   res++;
        }
        System.out.println(res);
        br.close();
    }
}