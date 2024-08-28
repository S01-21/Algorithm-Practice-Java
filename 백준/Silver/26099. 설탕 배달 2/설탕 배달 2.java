
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static long N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        long sum = 0;
        while (true){
            if (N < 0){
                sum = -1;
                break;
            }
            if (N%5 == 0){
                sum += N/5;
                break;
            } else {
                sum++;
                N -= 3;
            }
        }
        System.out.println(sum);
        br.close();
    }


}