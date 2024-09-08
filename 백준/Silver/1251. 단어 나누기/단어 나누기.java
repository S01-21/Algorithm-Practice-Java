import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] part = new int[2];
    static int length;  // 입력 문자열의 길이
    static String str, result;
    static Set<String> set = new TreeSet<>();
public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        length = str.length();

        // 1) 세 단어로 나누기
        // 1 ~ N-1 -> 중복 없는 2개 조합
        comb(1, 0);

        Iterator<String> it = set.iterator();
        System.out.println(it.next());
        br.close();
    }

    private static void comb(int st, int cnt) {
        if (cnt == 2){
            if (part[0] > part[1])  return;

            // 2) 각각 뒤집기
            String[] list = new String[3];
            list[0] = str.substring(0, part[0]);
            list[1] = str.substring(part[0], part[1]);
            list[2] = str.substring(part[1], length);

           result = "";
            for (String part : list){
                    String tmp = "";
                for (int i = part.length() - 1; i>=0; i--){
                    tmp += part.charAt(i);
                }
                result += tmp;
            }

            // 3) 합치기
            set.add(result);
            return;
        }

        for (int i=st; i < length; i++){
            part[cnt] = i;
            comb(i+1, cnt + 1);
        }
    }
}