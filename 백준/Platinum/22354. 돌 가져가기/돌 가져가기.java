import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        char[] A = br.readLine().toCharArray();
        List<Integer> queue = new ArrayList<>();
        int max = 0;
        st= new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int temp = Integer.parseInt(st.nextToken());
            if(max < temp) max = temp;
            if(i == N-1)
                continue;
            if(A[i] != A[i+1]){
                queue.add(max);
                max = 0;
            }
        }
        if(!queue.isEmpty())
            queue.remove(0);
        Collections.sort(queue, Collections.reverseOrder());

        long score = 0;
        for(int i = 0; i < (queue.size()+1)/2; i++)
            score += queue.get(i);

        System.out.println(score);
    }
}
