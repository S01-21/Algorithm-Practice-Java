import java.util.*;

public class Main {

	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int W = sc.nextInt();
		int L = sc.nextInt();
		int [] weights = new int[1001];
		int [] pos = new int[1001];	// 다리를 지나는 트럭의 이동시간 계산하는 배열 
		
		for (int i=0; i<N; i++)
			weights[i] = sc.nextInt();
		
		int time=0, wSum =0, cnt = 0;
		
		
		for (int nxt=0; nxt<N;) {
			
			// 다리 길이만큼 꽉 차면 큐에서 제거 
			if (!queue.isEmpty() && pos[queue.peek()] == W) {
				cnt--;
				wSum -= weights[queue.peek()];
				queue.remove();
			}
			
			// 다리 위 최대하중과 최대개수 넘지않으면 큐에 추가 
			if (wSum + weights[nxt] <= L && cnt+1 <= W) {
				queue.add(nxt);
				wSum += weights[nxt];
				cnt++;
				nxt++;
			}
			
			//다리 위에 올라가 있는 트럭의 이동 시간 계산 
			for (int i=queue.peek(); i<queue.peek() + cnt; i++)
				pos[i]++;
			
			
			time++;
		}
		
		// 다리 위에 남아 있는 트럭 이동 시간 계산 
		while (!queue.isEmpty()) {
			if (pos[queue.peek()] == W) {
                queue.remove();
                cnt--;
            }
			if (!queue.isEmpty()) {
				for (int i=queue.peek(); i<queue.peek() + cnt; i++) {
					pos[i]++;
				}
			}
			time++;                    
		}
		System.out.println(time);
	}

}
