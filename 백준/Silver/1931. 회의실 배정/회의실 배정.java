import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, res;
    static PriorityQueue<Meeting> pq = new PriorityQueue<>();
    static class Meeting implements Comparable<Meeting>{
        int start, end;
        public Meeting(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Meeting o){
            if (this.end != o.end){
                return this.end - o.end;
            } else {
                if (this.start != o.start){
                    return this.start - o.start;
                } else {
                    int gapThis = this.end - this.start;
                    int gapOther = o.end - o.start;
                    return gapThis - gapOther;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Meeting first = pq.poll();
        int s = first.start;
        int e = first.end;
        res++;
        while (!pq.isEmpty()){
            Meeting cur = pq.poll();
            if (cur.start < e)  continue;
            s = cur.start;
            e = cur.end;
            res++;
        }
        System.out.println(res);
        br.close();
    }
}