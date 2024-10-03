import java.util.*;
import java.io.*;

class Solution {
    public Map<String, Integer> map = new HashMap<>();
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        for (String name : participant){
            map.put(name, map.getOrDefault(name, 0) + 1);
        }
        
        for (String name : completion){
            map.put(name, map.get(name) - 1);
            if (map.get(name) == 0){
                map.remove(name);
            }
        }
        
        Set<String> set = map.keySet();
        String str = set.toString();
        int length = str.length();
        for (int i = 1; i < length-1; i++){
            answer += str.charAt(i);
        }
        
        return answer;
    }
}