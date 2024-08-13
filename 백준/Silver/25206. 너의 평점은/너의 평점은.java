import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        double mulSum = 0;
        double scoreSum = 0;
        for (int i=0; i<20; i++){
            st = new StringTokenizer(br.readLine());
            st.nextToken(); // 과목명
            double score = Double.parseDouble(st.nextToken());
            String grade = st.nextToken();
            if (grade.equals("P")){
                continue;
            } else {
                scoreSum += score;
               switch (grade){
                   case "A+":
                       score *= 4.5;
                       break;
                   case "A0":
                       score *= 4.0;
                       break;
                   case "B+":
                       score *= 3.5;
                       break;
                   case "B0":
                       score *= 3.0;
                       break;
                   case "C+":
                       score *= 2.5;
                       break;
                       case "C0":
                           score *= 2.0;
                           break;
                           case "D+":
                               score *= 1.5;
                               break;
                   case "D0":
                       score *= 1.0;
                       break;
                   case "F":
                       score *= 0.0;
                       break;
               }
                mulSum += score;
            }
        }
        System.out.println(mulSum/scoreSum);
        br.close();
    }
}