import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static long A, B, res;
public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        res = bfs(A);
        System.out.println(res);
        br.close();
    }

    private static int bfs(long start) {
        Queue<Long> queue = new ArrayDeque<>();
        queue.offer(start);
        int cnt = 0;    // 연산 횟수
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i=0; i<size; i++){
                long cur = queue.poll();
                if (cur == B){
                    return cnt+1;
                }
                if (cur*2 <= B) queue.offer(cur*2);
                if (cur*10+1 <= B) queue.offer(cur*10+1);
            }
            cnt++;
        }
        return -1;
    }
}