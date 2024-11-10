import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    public static void main(String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            N = Integer.parseInt(br.readLine());
            long res = 1;
//            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = N ; i >= 1 ; i--){
                res *= i;
            }
            System.out.println(res);
            br.close();
    }
}