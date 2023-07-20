import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<Integer> queue = new LinkedList<>();
        List<Integer> qtemp = new LinkedList<>();
        int sum = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st.nextToken());
            sum += temp;
            queue.add(temp);
            qtemp.add(temp);
        }
        Collections.sort(queue);
        Collections.sort(qtemp);
        System.out.println(Math.round((float) sum/N));
        System.out.println(queue.get(N/2));
        int range = queue.get(queue.size()-1) - queue.get(0);
        int prev = 4001;
        int count = 1;
        int max = 0;
        while(!queue.isEmpty()){
            int temp = queue.remove(0);
            if(prev == temp){
                count++;
                max = Math.max(max, count);
                prev = temp;
            }
            else{
                count = 1;
                prev = temp;
            }
        }

        prev = 4001;
        count = 1;
        int c = 0;
        int many = 0;
        if(N == 1)
            many = qtemp.get(0);
        else
            many = qtemp.get(1);
        while(!qtemp.isEmpty()){
            int temp = qtemp.remove(0);
            if(prev == temp){
                count++;
                if(max == count){
                    c++;
                    many = temp;
                    if(c == 2)
                        break;
                }
                prev = temp;
            }
            else{
                count = 1;
                prev = temp;
            }
        }
        System.out.println(many);
        System.out.println(range);
    }
}
