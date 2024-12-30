import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N, res;
    static Egg[] eggList;
    static class Egg{
        int p, w;
        Egg(int p, int w){
            this.p = p;
            this.w = w;
        }
    }
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        eggList = new Egg[N];
        for (int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggList[i] = new Egg(p, w);
        }

        func(0);

        System.out.println(res);
        br.close();
    }
    static void func(int st){
        if (st == N)    {
            int cnt = 0;
            for(int i = 0 ; i < N; i++){
                Egg cur = eggList[i];
                if (cur.p <= 0)  cnt++;
            }
            res = Math.max(cnt, res);
            return;
        }
        Egg left = eggList[st];
        if (left.p <= 0){
            func(st+1);
            return;
        }
        boolean flag = false;
        for (int i = 0; i < N; i++){
            if (st == i)    continue;
            Egg right = eggList[i];
            if (right.p <= 0)   continue;
            flag = true;
            left.p -= right.w;
            right.p -= left.w;
            func(st+1);
            left.p += right.w;
            right.p += left.w;
        }
        if (!flag){
            func(st+1);
        }
    }
}