import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class CompareAbs implements Comparable<CompareAbs>{

	private int org;
	private int abs;
	
	public CompareAbs(int org) {
		this.org = org;
		this.abs = Math.abs(org);
	}
	
	public int getAbs() {
		return this.abs;
	}
	public int getOrg() {
		return this.org;
	}
	
	@Override
	public int compareTo(CompareAbs obj) {
		int result;
		if (this.abs == obj.getAbs()) {
			result = Integer.compare(this.org, obj.getOrg());
		} else {
			result = this.abs - obj.abs;
		}
		return result;
	}
	
}
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<CompareAbs> PQ = new PriorityQueue<>();
		
		for (int i=0; i<N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if (x == 0) {
				if (PQ.isEmpty()) {
					sb.append(0).append("\n");
				} else {
					CompareAbs output = PQ.poll();
					sb.append(output.getOrg()).append("\n");
				}
			} else {
				PQ.add(new CompareAbs(x));
			}
		}
		System.out.println(sb.toString());
	}

}
