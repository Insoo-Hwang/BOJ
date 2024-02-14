import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int S = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int x = 0;
            if(!(s.equals("all") || s.equals("empty"))) x = Integer.parseInt(st.nextToken());
            if(s.equals("add")) S |= (1<<x);
            else if(s.equals("remove")) S &= ~(1<<x);
            else if(s.equals("check")){
                if((S & (1<<x)) > 0) sb.append("1").append("\n");
                else sb.append("0").append("\n");
            }
            else if(s.equals("toggle")) S ^=(1<<x);
            else if(s.equals("all")) S = -1;
            else S = 0;
        }
        System.out.println(sb);
    }
}