import java.util.*;
import java.io.*;
class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        for (int[] size: sizes){
            if (size[0] < size[1]){
                int tmp = size[0];
                size[0] = size[1];
                size[1] = tmp;
            }
        }
        int maxW = 0;
        int maxH = 0;
        for (int[] size: sizes){
            maxW = Math.max(size[0], maxW);
            maxH = Math.max(size[1], maxH);
        }
        return maxW*maxH;
    }
}