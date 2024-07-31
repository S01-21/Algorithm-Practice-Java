import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static String pattern;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), "*");
        String pt1 = st.nextToken();
        String pt2 = st.nextToken();

        String tmp1 = "";
        String tmp2 = "";
        for (int i=0; i<N; i++) {
            String str = br.readLine();
            if (pt1.length() + pt2.length() > str.length()){
                sb.append("NE").append("\n");
                continue;
            }
            tmp1 = str.substring(0, pt1.length());  // 앞부분
            tmp2 = str.substring(str.length() - pt2.length());  // 뒷부분

            if (tmp1.equals(pt1) && tmp2.equals(pt2)) {
                sb.append("DA").append("\n");
            } else {
                sb.append("NE").append("\n");
            }
        }
        System.out.println(sb);
        br.close();
    }


}