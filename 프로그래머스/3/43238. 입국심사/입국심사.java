class Solution {
    int[] input;
    public long solution(int n, int[] times) {
        input = times;
        long start = 0;
        long end = (long)times[0] * n;
        
        while (start < end){
            long mid = (start + end) / 2;
            
            if (isValid(mid, n)) {  // 최소 찾기 위해 end를 반으로 줄이기
                end = mid;
            } else {    // 시간 늘리면 가능한지 찾으러 가기
                start = mid + 1;
            }
        }
        
        return start;
    }
    public boolean isValid(long minute, int n){
        long cnt = 0;
        for (int time : input){
            cnt += minute/time;
        }
       return cnt >= n; // 심사 가능한 사람 수가 대기인원 보다 많으면 true
    }
}