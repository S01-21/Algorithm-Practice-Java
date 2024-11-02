import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int M, F;
    public static void main(String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                StringTokenizer st = new StringTokenizer(br.readLine());
                M = Integer.parseInt(st.nextToken());
                F = Integer.parseInt(st.nextToken());
                if (M == 0 && F == 0)   break;
                sb.append(M+F).append("\n");
            }
            System.out.println(sb);
            br.close();
    }
}