import java.util.*;
import java.io.*;

class Solution {
    int n, res;
    int[] dist;
    String t;
    ArrayList<String> list = new ArrayList<>();
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        n = words.length;
        t = target;
        dist = new int[n+1];
        list.add(begin);
        for (String str : words){
            list.add(str);
        }
        if (!list.contains(target)){
            return 0;
        }
        
        bfs(0);
        
        return res;
    }
    public void bfs(int start){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        dist[start] = 1;
        
        while (!queue.isEmpty()){
            int cur = queue.poll();
            if (list.get(cur).equals(t)){
                res = dist[cur] - 1;
                return;
            }
            for (int nxt = 0; nxt< list.size(); nxt++){
                if (dist[nxt] > 0)    continue;
                if (!check(list.get(cur), list.get(nxt))) continue;
                dist[nxt] = dist[cur] + 1;
                queue.offer(nxt);
            }
        }
    }
    public boolean check(String cur, String nxt){ // 한글자만 바뀌는 지 체크
        int cnt = 0;
        for (int i=0; i< cur.length(); i++){
            if (cur.charAt(i) != nxt.charAt(i)){
                cnt++;
                if (cnt > 1){
                    return false;
                }
            }
        }
        return true;
    }
}