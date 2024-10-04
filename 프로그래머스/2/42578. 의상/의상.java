import java.util.*;
import java.io.*;
class Solution {
    Map<String, Integer> map = new HashMap<>();
    public int solution(String[][] clothes) {
        int answer = 0;
        for (int i=0; i<clothes.length; i++){
            String type = clothes[i][1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }
        if (map.size() == 1){
            for (String type : map.keySet()){
                return map.get(type);
            }
        }
        int res = 1;
        for (String type : map.keySet()){
            res *= map.get(type) + 1;
        }
        answer = res - 1;
        return answer;
    }
}