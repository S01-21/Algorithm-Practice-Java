import java.util.*;
import java.io.*;
class Solution {
    public List<String> list = new ArrayList<>();
    public int solution(String word) {
        int answer = 0;
        
        
        dfs("");
        
        
        return list.indexOf(word);
    }
    public void dfs(String s){
        if (s.length() > 5) return;
        list.add(s);
        for (int i = 0; i< 5; i++){
            dfs(s + "AEIOU".charAt(i));
        }
    }
}