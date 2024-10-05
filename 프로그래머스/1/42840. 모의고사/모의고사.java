import java.util.*;
import java.io.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] answer;
        int[] p1 = {1,2,3,4,5};
        int[] p2 = {2,1,2,3,2,4,2,5};
        int[] p3 = {3,3,1,1,2,2,4,4,5,5};
        List<Integer> score = new ArrayList<>();
       for (int i = 0; i< 3; i++){
           score.add(0);
       }
       for (int i = 0; i<answers.length; i++){
           if (p1[i%5] == answers[i]){
               score.set(0, score.get(0)+1);
           }
           if (p2[i%8] == answers[i]){
               score.set(1, score.get(1)+1);
           }
           if (p3[i%10] == answers[i]){
               score.set(2, score.get(2)+1);
           }
       }
        List<Integer> ans = new ArrayList<>();
        int max = Collections.max(score);
        for (int i = 0 ; i < score.size(); i++){
            if (max == score.get(i)){
                ans.add(i+1);
            }
        }
        Collections.sort(ans);
        answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++){
            answer[i] = ans.get(i);
        }
        return answer;
    }
}