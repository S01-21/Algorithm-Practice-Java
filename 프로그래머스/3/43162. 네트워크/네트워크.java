import java.util.*;
import java.io.*;
class Solution {
    public class Node{
        int num;
        Node next;
        public Node(int num, Node next){
            this.num = num;
            this.next = next;
        }
    }
    public Node[] adjList;
    boolean[] vis;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        adjList = new Node[n];
        for (int i=0; i<n; i++){
            for (int j=i+1; j<n; j++){
                if (computers[i][j] == 1){
                    adjList[i] = new Node(j, adjList[i]);
                    adjList[j] = new Node(i, adjList[j]);
                }
            }
        }
        
        vis = new boolean[n];
        for (int i=0; i<n; i++){
            if (!vis[i]){
                bfs(i);
                answer++;
            }
        }
        
        return answer;
    }
    public void bfs(int start){
        Queue<Integer> queue = new ArrayDeque<>();
        vis[start] = true;
        queue.offer(start);
        
        while (!queue.isEmpty()){
            int cur = queue.poll();
            for (Node tmp = adjList[cur]; tmp != null; tmp = tmp.next){
                if (vis[tmp.num])   continue;
                vis[tmp.num] = true;
                queue.offer(tmp.num);
            }
        }
    }
}