import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(br.readLine());
        Recommend[] A = new Recommend[N];
        Arrays.fill(A, new Recommend(1001, -1, 1001));
        boolean [] visited = new boolean[1002];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < R; i++){
            int temp = Integer.parseInt(st.nextToken());
            if(visited[temp]){
                for(int j = 0; j < N; j++){
                    if(A[j].num != temp) continue;
                    A[j].cnt++;
                    break;
                }
            }
            else{
                visited[A[N-1].num] = false;
                A[N-1] = new Recommend(temp, 1, i);
                visited[temp] = true;
            }
            Arrays.sort(A);
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            if(A[i].num == 1001) continue;
            list.add(A[i].num);
        }
        Collections.sort(list);
        for(int n : list){
            System.out.print(n + " ");
        }
    }

    static class Recommend implements Comparable<Recommend>{
        int num;
        int cnt;
        int time;

        public Recommend(int num, int cnt, int time){
            this.num = num;
            this.cnt = cnt;
            this.time = time;
        }

        @Override
        public int compareTo(Recommend o) {
            if(this.cnt == o.cnt) return o.time - this.time;
            return o.cnt - this.cnt;
        }
    }
}