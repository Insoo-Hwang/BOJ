import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int start = sc.nextInt();
        int end = sc.nextInt();
        int M = sc.nextInt();
        long [] distance = new long[N];
        for(int i = 0; i < N; i++)
            distance[i] = Long.MIN_VALUE;
        distance[start] = 0;
        Edge [] edges = new Edge[M];

        for(int i = 0; i < M; i++){
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = sc.nextInt();
            edges[i] = new Edge(A, B, C);
        }
        long[] money = new long[N];
        for(int i = 0; i < N; i++)
            money[i] = sc.nextLong();
        distance[start] = money[start];

        for(int i = 0; i < N+100; i++){
            for(int j = 0; j < M; j++){
                Edge edge = edges[j];
                if(distance[edge.start] == Long.MIN_VALUE) continue;
                else if(distance[edge.start] == Long.MAX_VALUE)
                    distance[edge.end] = Long.MAX_VALUE;
                else if(distance[edge.end] < distance[edge.start] + money[edge.end] - edge.weight){
                    distance[edge.end] = distance[edge.start] + money[edge.end] - edge.weight;
                    if(i >= N-1) distance[edge.end] = Long.MAX_VALUE;
                }
            }
        }

        if(distance[end] == Long.MIN_VALUE) System.out.println("gg");
        else if(distance[end] == Long.MAX_VALUE) System.out.println("Gee");
        else System.out.println(distance[end]);
    }
}

class Edge{
    int start;
    int end;
    int weight;
    public Edge(int start, int end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}
