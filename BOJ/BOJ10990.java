import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int sp = N-1;
		
		for (int i = 0; i< N; i++) {
			if (i == 0) {
				for (int t = 0; t < N-1; t++) {
					System.out.print(" ");
				}
				System.out.println("*");
				continue;
			}
			
			for (int t = 0; t < N-1 - i; t++) {	//앞 공백 
				System.out.print(" ");
			}
			System.out.print("*");
			for (int t = 0; t < 2*(i-1) + 1; t++) { // 중간 공백 
				System.out.print(" ");
			}
			System.out.println("*");
		}
		
	}
}

