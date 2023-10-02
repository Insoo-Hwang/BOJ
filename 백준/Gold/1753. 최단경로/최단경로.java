import java.util.*;

public class Main {
    static ArrayList<Edge>[] A;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt();
        int E = sc.nextInt();
        int K = sc.nextInt();
        int [] shortA = new int[V+1];
        boolean [] visited = new boolean[V+1];
        A = new ArrayList[V+1];
        for(int i = 1; i < V+1; i++)
            A[i] = new ArrayList<>();
        for(int i = 0; i < V+1; i++)
            shortA[i] = Integer.MAX_VALUE;
        for(int i = 1; i < E+1; i++){
            int start = sc.nextInt();
            int end = sc.nextInt();
            int weight = sc.nextInt();
            A[start].add(new Edge(end, weight));
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(K,0));
        shortA[K] = 0;
        while(!queue.isEmpty()){
            Edge tempv = queue.poll();
            int temp = tempv.end;
            if(visited[temp]) continue;
            visited[temp] = true;
            for(int i = 0; i < A[temp].size(); i++){
                Edge tmp = A[temp].get(i);
                int end = tmp.end;
                int weight = tmp.weight;
                if(shortA[end] > shortA[temp] + weight){
                    shortA[end] = shortA[temp] + weight;
                    queue.add(new Edge(end, shortA[end]));
                }
            }
        }
        for(int i = 1; i < V+1; i++){
            if(visited[i]) System.out.println(shortA[i]);
            else System.out.println("INF");
        }
    }
}

class Edge implements Comparable<Edge>{
    int end;
    int weight;
    Edge(int end, int weight){
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
        if(this.weight > e.weight) return 1;
        else return -1;
    }
}
