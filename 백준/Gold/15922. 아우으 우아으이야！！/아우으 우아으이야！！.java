import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<Line> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            list.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list);
        long length = list.get(0).e-list.get(0).s;
        int end = list.get(0).e;
        for(int i = 1; i < N; i++){
            if(end >= list.get(i).e) continue;
            else if(end >= list.get(i).s){
                length+=list.get(i).e-end;
                end = list.get(i).e;
            }
            else{
                length+=list.get(i).e-list.get(i).s;
                end = list.get(i).e;
            }
        }
        System.out.println(length);
    }

    static class Line implements Comparable<Line>{
        int s;
        int e;

        public Line(int s, int e){
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Line o){
            if(this.s == o.s) return o.e-this.e;
            return this.s-o.s;
        }
    }
}