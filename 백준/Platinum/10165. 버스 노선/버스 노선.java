import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int [][] A = new int[M][2];
        for(int i = 0; i < M; i++){
            A[i][0] = sc.nextInt();
            A[i][1] = sc.nextInt();
        }
        List<Node> list = new ArrayList<>();
        for(int i = 0; i < M; i++){
            int s = A[i][0];
            int e = A[i][1];
            if(s > e){
                list.add(new Node(s, e+N, i+1));
            }else{
                list.add(new Node(s, e, i+1));
                list.add(new Node(s+N, e+N, i+1));
            }
        }
        Collections.sort(list);
        boolean[] check = new boolean[M+1];

        int min = list.get(0).s;
        for(int i = 1; i < list.size(); i++){
            Node temp = list.get(i);
            Node prev = list.get(i-1);
            if(prev.s < min) min = prev.s;
            if(temp.s >= min){
                check[temp.n] = true;
            }
        }
        for(int i = 1; i < M+1; i++){
            if(!check[i]) System.out.print(i + " ");
        }
    }
    static class Node implements Comparable<Node>{
        int s;
        int e;
        int n;
        Node(int s, int e, int n){
            this.s = s;
            this.e = e;
            this.n = n;
        }

        @Override
        public int compareTo(Node o) {
            if(this.e == o.e)
                return this.s-o.s;
            else
                return o.e-this.e;
        }
    }
}
