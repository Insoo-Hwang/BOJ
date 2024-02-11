import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        ArrayList<Bus> [] A = new ArrayList[n+1];
        for(int i = 1; i < n+1; i++){
            A[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[Integer.parseInt(st.nextToken())].add(new Bus(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int [] shortA = new int[n+1];
        Arrays.fill(shortA, Integer.MAX_VALUE);
        shortA[start] = 0;
        boolean [] visited = new boolean[n+1];
        String [] city = new String[n+1];
        city[start] = start +"-";
        int [] count = new int[n+1];
        count[start] = 1;
        PriorityQueue<Bus> queue = new PriorityQueue<>();
        queue.add(new Bus(start, 0));
        while(!queue.isEmpty()){
            Bus now = queue.poll();
            if(visited[now.e]) continue;
            visited[now.e] = true;
            for(int i = 0; i < A[now.e].size(); i++){
                Bus temp = A[now.e].get(i);
                if(shortA[temp.e] > shortA[now.e]+temp.w){
                    shortA[temp.e] = shortA[now.e]+temp.w;
                    queue.add(new Bus(temp.e, shortA[temp.e]));
                    city[temp.e] = city[now.e]+temp.e +"-";
                    count[temp.e] = count[now.e]+1;
                }
            }
        }
        System.out.println(shortA[end]);
        System.out.println(count[end]);
        System.out.println(city[end].replace("-", " "));
    }

    static class Bus implements Comparable<Bus>{
        int e;
        int w;

        public Bus(int e, int w){
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Bus o){
            return this.w-o.w;
        }
    }
}