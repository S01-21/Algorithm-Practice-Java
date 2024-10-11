import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] parents;
    static class Edge{
        int from, to;
        public Edge(int from, int to){
            this.from = from;
            this.to = to;
        }
    }
    static Edge[] edgeList;
    public static void main(String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            parents = new int[N+1];
            for (int i = 1; i<= N; i++){
                parents[i] = i;
            }
            edgeList = new Edge[N-2];
            StringTokenizer st = null;
            for (int i = 0 ; i < N-2; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                edgeList[i] = new Edge(a, b);
            }

            for (Edge edge : edgeList){
                union(edge.from, edge.to);
            }
            int[] res = new int[2];
            res[0] = find(1);
            for (int i = 2; i <= N; i++){
                int rootTmp = find(i);
                if (res[0] != rootTmp){
                    res[1] = rootTmp;
                    break;
                }
            }
            System.out.println(res[0] + " " + res[1]);
            br.close();
    }
    static int find(int a){
        if (parents[a] == a)    return a;
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