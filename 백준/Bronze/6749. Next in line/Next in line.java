import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int Y, M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        int res = M*2 - Y;
        System.out.println(res);
        br.close();
    }
}