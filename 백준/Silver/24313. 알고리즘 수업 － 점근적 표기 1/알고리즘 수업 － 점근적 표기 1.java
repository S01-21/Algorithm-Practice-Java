import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int a1 = scanner.nextInt(); // f(n)의 n의 계수
        int a0 = scanner.nextInt(); // f(n)의 상수항
        int c = scanner.nextInt();  // O(n)의 계수
        int n0 = scanner.nextInt(); // n0 값
        
        scanner.close();
        
        boolean satisfiesCondition = true;
        
        // 점근적 표기의 정의를 만족하는지 검사
        for (int n = n0; n <= 100; n++) { // n0부터 100까지 확인
            if (a1 * n + a0 > c * n) {
                satisfiesCondition = false;
                break;
            }
        }
        
        System.out.println(satisfiesCondition ? 1 : 0);
    }
}