import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Computer> [] A = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++){
            A[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            A[s].add(new Computer(e, w));
            A[e].add(new Computer(s, w));
        }
        int [] shortA = new int[N+1];
        Arrays.fill(shortA, Integer.MAX_VALUE);
        shortA[1] = 0;
        boolean [] visited = new boolean[N+1];
        String [] route = new String[N+1];
        route[1] = "1-";
        PriorityQueue<Computer> queue = new PriorityQueue<>();
        queue.add(new Computer(1, 0));
        while(!queue.isEmpty()){
            Computer now = queue.poll();
            if(visited[now.e]) continue;
            visited[now.e] = true;
            for(int i = 0; i < A[now.e].size(); i++){
                Computer temp = A[now.e].get(i);
                if(shortA[temp.e] > shortA[now.e]+temp.w){
                    shortA[temp.e] = shortA[now.e]+temp.w;
                    route[temp.e] = route[now.e]+temp.e+"-";
                    queue.add(new Computer(temp.e, shortA[temp.e]));
                }
            }
        }
        HashSet<String> hashSet = new HashSet<>();
        boolean [][] check = new boolean[N+1][N+1];
        for(int i = 1; i < N+1; i++){
            String [] s = route[i].split("-");
            for(int j = 1; j < s.length; j++){
                if(check[Integer.parseInt(s[j-1])][Integer.parseInt(s[j])]) continue;
                check[Integer.parseInt(s[j-1])][Integer.parseInt(s[j])] = true;
                check[Integer.parseInt(s[j])][Integer.parseInt(s[j-1])] = true;
                hashSet.add(s[j-1]+" "+s[j]);
            }
        }
        sb.append(hashSet.size()+"\n");
        for(String s : hashSet){
            sb.append(s+"\n");
        }
        System.out.println(sb);
    }

    static class Computer implements Comparable<Computer>{
        int e;
        int w;

        public Computer(int e, int w){
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Computer o){
            return this.w-o.w;
        }
    }
}