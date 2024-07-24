import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] parents;
    static class Edge{
        int from, to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
    static Edge[] edgeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 점의 개수
        M = Integer.parseInt(st.nextToken());   // 진행된 차례 수

        edgeList = new Edge[M];
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(from, to);
        }
        makeSet();

        boolean flag = false;
        for (int i=0; i<M; i++) {
            Edge edge = edgeList[i];
            if (!union(edge.from, edge.to)) {   // 사이클 발생
                sb.append(i+1);
                flag = true;
                break;
            }
        }
        if (flag){
            System.out.println(sb);
        } else {
            System.out.println(0);
        }
        br.close();
    }
    static void makeSet(){
        parents = new int[N];
        for (int i=0; i<N; i++) {
            parents[i] = i;
        }
    }
    static int find(int a){
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
    static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return false;
        parents[rootB] = rootA;
        return true;
    }
}