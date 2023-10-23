import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int max = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < N; i++){
            int temp = Integer.parseInt(st.nextToken());
            max+=temp;
            queue.add(temp);
        }
        boolean[] D = new boolean[max+1];
        List<Integer> t = new ArrayList<>();
        for(int temp : queue){
            t.clear();
            for(int i = max; i >= 0; i--){
                if(!D[i] && D[Math.abs(i-temp)]){
                    t.add(i);
                }
                if(!D[i] && i+temp < max+1 && D[i+temp]){
                    t.add(i);
                }
            }
            for(int i : t) D[i] = true;
            D[temp] = true;
        }
        int check = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < check; i++){
            int c = Integer.parseInt(st.nextToken());
            if(c < max+1 && D[c]) System.out.print("Y ");
            else System.out.print("N ");
        }
    }
}