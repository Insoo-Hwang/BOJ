import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            int temp = Integer.parseInt(br.readLine());
            if(temp != 0) list.add(temp);
            else list.remove(list.size()-1);
        }
        int sum = 0;
        for(int i : list){
            sum+=i;
        }
        System.out.println(sum);
    }
}