import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static Deque<Integer> cards = new ArrayDeque<Integer>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i=1; i<=N; i++){
            cards.addLast(i);
        }

        while(!cards.isEmpty()){
            int top = cards.removeFirst();
            sb.append(top).append(" ");

            if (!cards.isEmpty()){
                top = cards.removeFirst();
                cards.addLast(top);
            }
        }

        System.out.println(sb);
        br.close();
    }
}