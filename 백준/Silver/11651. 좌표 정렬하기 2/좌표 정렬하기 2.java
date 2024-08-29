import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static PriorityQueue<Point> pq = new PriorityQueue<>();
    static class Point implements Comparable<Point>{
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Point o){
            if (this.y != o.y){
                return this.y - o.y;
            } else {
                return this.x - o.x;
            }
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
            Point point = pq.poll();
            sb.append(point.x).append(" ").append(point.y).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}