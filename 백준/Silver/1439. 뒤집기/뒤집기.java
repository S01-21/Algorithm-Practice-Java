import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static String str;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine().trim();

        for (int i=0; i<str.length()-1; i++) {
            if (str.charAt(i) != str.charAt(i+1)) {
                cnt++;
            }
        }

        System.out.println((cnt+1)/2);
        br.close();
    }


}