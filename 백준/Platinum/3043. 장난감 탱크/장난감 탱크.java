import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        List<Tank> queue1 = new ArrayList<>();
        List<Tank> queue2 = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            queue1.add(new Tank(i+1, Integer.parseInt(st.nextToken())));
            queue2.add(new Tank(i+1, Integer.parseInt(st.nextToken())));
        }
        Collections.sort(queue1);
        Collections.sort(queue2);

        int cnt = 0;
        for(int i = 1; i < N+1; i++){
            Tank temp = queue1.get(i-1);
            while(temp.n > i){
                sb.append(temp.num).append(" U\n");
                temp.n--;
                cnt++;
            }
        }
        for(int i = N; i > 0; i--){
            Tank temp = queue1.get(i-1);
            while(temp.n < i){
                sb.append(temp.num).append(" D\n");
                temp.n++;
                cnt++;
            }
        }for(int i = 1; i < N+1; i++){
            Tank temp = queue2.get(i-1);
            while(temp.n > i){
                sb.append(temp.num).append(" L\n");
                temp.n--;
                cnt++;
            }
        }
        for(int i = N; i > 0; i--){
            Tank temp = queue2.get(i-1);
            while(temp.n < i){
                sb.append(temp.num).append(" R\n");
                temp.n++;
                cnt++;
            }
        }
        System.out.println(cnt);
        System.out.println(sb);
    }

    static class Tank implements Comparable<Tank>{
        int num;
        int n;
        public Tank(int num, int n){
            this.num = num;
            this.n = n;
        }

        @Override
        public int compareTo(Tank o) {
            return this.n-o.n;
        }
    }
}