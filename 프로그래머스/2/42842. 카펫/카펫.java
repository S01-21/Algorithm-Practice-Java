import java.util.*;
import java.io.*;
class Solution {
    int total;
    int[] answer = new int[2];
    public int[] solution(int brown, int yellow) {
        total = brown + yellow;
        find(brown, yellow);
        return answer;
    }
    void find(int brown, int yellow){   // 약수 찾기
        for (int i = 1; i <= yellow; i++){
            if (yellow%i == 0){ 
                int tmp = yellow/i;
                if (i*2 + tmp*2 + 4 == brown){
                    answer[0] = tmp+2;
                    answer[1] = i+2;
                    return;
                }
            }
        }
    }
}