import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        int vCount = n / 5;
        int iCount = n % 5;

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < vCount; i++) {
            result.append("V");
        }
        for (int i = 0; i < iCount; i++) {
            result.append("I");
        }

        System.out.println(result.toString());
    }
}