import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, res;
    static PriorityQueue<Point> pq = new PriorityQueue<Point>();
    static class Point implements Comparable<Point>{
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Point o){
            if (this.x != o.x)  return this.x - o.x;
            return this.y - o.y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.offer(new Point(x, y));
        }
        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            sb.append(cur.x).append(" ").append(cur.y).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}