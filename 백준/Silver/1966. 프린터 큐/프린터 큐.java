import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        while (T-->0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Queue<Integer> queue1 = new LinkedList<>();
            List<Integer> queue2 = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                int temp = Integer.parseInt(st.nextToken());
                queue1.add(temp);
                queue2.add(temp);
            }
            Collections.sort(queue2, Collections.reverseOrder());
            int cnt = 0;
            while (true){
                int temp = queue1.poll();
                if(temp == queue2.get(0)){
                    cnt++;
                    if(M == 0)
                        break;
                    M--;
                    queue2.remove(0);
                }
                else{
                    M--;
                    if(M < 0) M = queue1.size();
                    queue1.add(temp);
                }
            }
            System.out.println(cnt);
        }
    }
}
