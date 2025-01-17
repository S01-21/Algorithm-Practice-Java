import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
    static int N, one, res;
    static class Candidate implements Comparable<Candidate>{
        int num, vote;
        public Candidate(int num, int vote) {
            this.num = num;
            this.vote = vote;
        }
        public int compareTo(Candidate o){
            return o.vote - this.vote;
        }
    }
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Candidate> pq = new PriorityQueue<>();
        one = Integer.parseInt(br.readLine());
        for (int i = 2; i <= N; i++){
            pq.offer(new Candidate(i, Integer.parseInt(br.readLine())));
        }

        while (true){
            if (pq.isEmpty())  break;
            Candidate cur = pq.poll();
            if (cur.vote < one)   break;
            pq.offer(new Candidate(cur.num, cur.vote-1));
            one++;
            res++;
        }

        System.out.println(res);
        br.close();
    }
}