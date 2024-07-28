import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, res;
    static int[] A, B;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        B = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        // int 배열을 Integer 배열로 변환
        Integer[] integerArray = Arrays.stream(B).boxed().toArray(Integer[]::new);

        // 배열을 내림차순으로 정렬
        Arrays.sort(integerArray, Collections.reverseOrder());

        // 정렬된 배열을 다시 int 배열로 변환
        B = Arrays.stream(integerArray).mapToInt(Integer::intValue).toArray();
        for (int i=0; i<N; i++) {
            res += A[i]*B[i];
        }

        System.out.println(res);
        br.close();
    }
}