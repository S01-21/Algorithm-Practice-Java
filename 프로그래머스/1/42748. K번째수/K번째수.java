import java.util.*;
import java.io.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int i = 0; i < commands.length; i++){
            int from = commands[i][0] - 1;
            int to = commands[i][1] - 1;
            int at = commands[i][2] - 1;
            
            List<Integer> list = new ArrayList<>();
            for (int j = from; j <= to; j++){
                list.add(array[j]);
            }
            Collections.sort(list);
            answer[i] = list.get(at);
        }
        
        return answer;
    }
}