import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        List<Water> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            list.add(new Water(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list);

        int cnt = 0;
        int s = list.get(0).s;
        for(int i = 0; i < N; i++){
            int e = list.get(i).e;
            if(s < list.get(i).s) s = list.get(i).s;
            if(e < s) continue;
            cnt+=(e-s)/L;
            s+=(e-s)/L*L;
            if((e-s)%L != 0){
                cnt++;
                s+=L;
            }
        }
        System.out.println(cnt);
    }

    static class Water implements Comparable<Water>{
        int s;
        int e;
        public Water(int s, int e){
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Water o){
            return this.s - o.s;
        }
    }
}