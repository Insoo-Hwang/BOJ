import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Boat> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            long s = Long.parseLong(st.nextToken());
            long e = Long.parseLong(st.nextToken());
            if(s > e) list.add(new Boat(s, e));
        }
        Collections.sort(list);
        long length = M;
        if(list.size() == 0){
            System.out.print(length);
            return;
        }
        long max = list.get(0).s;
        long min = list.get(0).e;
        for(int i = 1; i < list.size(); i++){
            Boat temp = list.get(i);
            if(temp.e > max){
                length+=2*(max-min);
                max = temp.s;
                min = temp.e;
            }
            else{
                max = Math.max(max, temp.s);
                min = Math.min(min, temp.e);
            }
        }
        length+=2*(max-min);
        System.out.print(length);
    }

    static class Boat implements Comparable<Boat>{
        long s;
        long e;

        public Boat(long s, long e){
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Boat o){
            if(this.e == o.e) return (int)(this.s-o.s);
            else return (int)(this.e-o.e);
        }
    }
}