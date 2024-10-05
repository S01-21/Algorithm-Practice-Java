import java.util.*;
import java.io.*;
class Solution {
    public class Node implements Comparable<Node>{
        int num, weight;
        Node next;
        public Node (int num, Node next){
            this.num = num;
            this.next = next;
        }
        public Node (int num, Node next, int weight){
            this.num = num;
            this.next = next;
            this.weight = weight;
        }
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }
    public Node[] adjList;
    public int[] minDist;
    public boolean[] vis;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        // 1번에서 모든 노드까지의 최단경로. 최단경로 중 최댓값
        // 그런 노드 개수
        
        adjList = new Node[n];
        minDist = new int[n];
        vis = new boolean[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        
        for (int[] info : edge){
            int from = info[0] - 1;
            int to = info[1] - 1;
            adjList[from] = new Node(to, adjList[from], 1);
            adjList[to] = new Node(from, adjList[to], 1);
        }
        
        dijkstra(0);
        
        int max = 0;
        for (int i = 0; i < n; i++){
            max = Math.max(max, minDist[i]);
        }
        for (int i = 0; i < n; i++){
            if (minDist[i] == max){
                answer++;
            }
        }
        
        return answer;
    }
    public void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        minDist[start] = 1;
        pq.offer(new Node(start, null, minDist[start]));
        
        while (!pq.isEmpty()){
            Node cur = pq.poll();
            
            if (vis[cur.num])   continue;
            
            vis[cur.num] = true;
            
            for (Node tmp = adjList[cur.num]; tmp != null; tmp = tmp.next){
                if (vis[tmp.num])   continue;
                if (minDist[tmp.num] > minDist[cur.num] + 1){
                    minDist[tmp.num] = minDist[cur.num] + 1;
                    pq.offer(new Node(tmp.num, null, minDist[tmp.num]));
                }
            }
        }
    }
}