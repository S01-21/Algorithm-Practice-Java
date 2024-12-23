
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static StringBuilder sb = new StringBuilder();
    static class Node{
        int left, right;
        public Node(int left, int right){
            this.left = left;
            this.right = right;
        }
    }
    static Node[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new Node[N];
        for (int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            char cur = st.nextToken().charAt(0);
            int num = cur - 'A';
            char l = st.nextToken().charAt(0);
            char r = st.nextToken().charAt(0);
            int left = -1;
            int right = -1;
            if (l != '.'){
                left = l - 'A';
            }
            if (r != '.'){
                right = r - 'A';
            }
            tree[num] = new Node(left, right);
        }

        preorder(0);
        sb.append("\n");
        inorder(0);
        sb.append("\n");
        postorder(0);
        System.out.println(sb);
        br.close();
    }
    private static void preorder(int start) {
        if (start == -1)    return;
        char cur = (char) ('A' + start);
        sb.append(cur);
        preorder(tree[start].left);
        preorder(tree[start].right);
    }
    private static void inorder(int start) {
        if (start == -1)    return;
        char cur = (char) ('A' + start);
        inorder(tree[start].left);
        sb.append(cur);
        inorder(tree[start].right);
    }
    private static void postorder(int start) {
        if (start == -1)    return;
        char cur = (char) ('A' + start);
        postorder(tree[start].left);
        postorder(tree[start].right);
        sb.append(cur);
    }
}