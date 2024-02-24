import java.io.*;
import java.util.*;

// 회전 초밥  
public class Main {
	static int[] check;
	static int[] sushi;
	static int N, D, K, C;
    static int satisfiedCount, typeCnt;

	public static void main(String args[]) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 벨트 위 접시 수
		D = Integer.parseInt(st.nextToken()); // 초밥 가짓수
		K = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
		C = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		check = new int[D + 1];
		sushi = new int[N + K];
		satisfiedCount = 0;

		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}

		int tmp = 0;
		for (int i = N; i < N + K; i++) {
			sushi[i] = sushi[tmp];
			tmp++;
		}
		
		typeCnt = 0;
		for (int i = 0; i < K; i++) {
			if (sushi[i] == C) {
				typeCnt++;
			} else {
				check[sushi[i]]++;

				if (check[sushi[i]] == 1) {
					satisfiedCount++;
				}
			}
		}
		int max = 0;
		int max1 = 0;
		for (int i = K; i < N + K; i++) {
			int j = i - K;
			
			add(i);
			remove(j);
			
			if (satisfiedCount >= max && typeCnt == 0) {
				max1 = satisfiedCount;
			}
			if (satisfiedCount >= max) {
				max = satisfiedCount;
			}
		}

		if (max <= max1) {
			sb.append(max1 + 1);
		} else {
			sb.append(max + 1);
		}
		sb.append("\n");

		System.out.println(sb);
	}

	private static void add(int in) {
		if (sushi[in] == C) {
			typeCnt++;
		} else {
			check[sushi[in]]++;
			if (check[sushi[in]] == 1) {
				satisfiedCount++;
			}
		}
	}

	private static void remove(int out) {
		if (sushi[out] == C) {
			typeCnt--;
		} else {
			check[sushi[out]]--;
			if (check[sushi[out]] == 0) {
				satisfiedCount--;
			}
		}
	}
}