import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, K;
    static Integer[] score;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        score = new Integer[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
           score[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(score, Collections.reverseOrder());
        System.out.println(score[K-1]);
        br.close();
    }

}