import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] res;
    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        res = new int[N];
        isSelected = new boolean[N+1];

        perm(0);
        System.out.println(sb);
        br.close();
    }

    private static void perm(int cnt) {
        if (cnt == N) {
            for (int i=0; i<N; i++) {
                sb.append(res[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i=1; i<=N; i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                res[cnt] = i;
                perm(cnt + 1);
                isSelected[i] = false;
            }
        }
    }
}