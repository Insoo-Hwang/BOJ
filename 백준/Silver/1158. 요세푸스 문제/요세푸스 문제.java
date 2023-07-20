import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 1; i < N+1; i++)
            queue.add(i);

        System.out.print("<");
        while(!queue.isEmpty()){
            for(int i = 0; i < K; i++){
                if(i == K-1){
                    if(queue.size() != 1)
                        System.out.print(queue.poll() + ", ");
                    else
                        System.out.print(queue.poll());
                }
                else{
                    queue.add(queue.poll());
                }
            }
        }
        System.out.print(">");
    }
}
