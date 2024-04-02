import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static BigInteger[] arr;
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new BigInteger[N+1];
		
		arr[0] = new BigInteger("0");
        if (N != 0) arr[1] = new BigInteger("1");
		
		for (int i=2; i<=N; i++) {
			if (i %2 == 0) {
				arr[i] = arr[i/2].multiply(arr[i/2]).add(arr[i/2].multiply(arr[i/2-1]).multiply(new BigInteger("2")));
			} else {
				arr[i] = arr[i/2].multiply(arr[i/2]).add(arr[i/2+1].multiply(arr[i/2+1]));
			}
		}
		

		System.out.println(arr[N]);
	}
}