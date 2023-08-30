import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                list.add(s.charAt(j)-'0');
            }
        }

        int K = Integer.parseInt(br.readLine());
        while(K-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int L = list.get((n-1)*8+6);
            int R = list.get((n-1)*8+2);
            int tempd = d;
            for(int i = n-2; i >= 0; i--){
                if(list.get(i*8+2) != L){
                    L = list.get(i*8+6);
                    if(tempd == -1){
                        int temp = list.remove(i*8+7);
                        list.add(i*8, temp);
                        tempd = 1;
                    }
                    else{
                        int temp = list.remove(i*8);
                        list.add(i*8+7, temp);
                        tempd = -1;
                    }
                }
                else{
                    break;
                }
            }
            tempd = d;
            for(int i = n; i < 4; i++){
                if(list.get(i*8+6) != R){
                    R = list.get(i*8+2);
                    if(tempd == -1){
                        int temp = list.remove(i*8+7);
                        list.add(i*8, temp);
                        tempd = 1;
                    }
                    else{
                        int temp = list.remove(i*8);
                        list.add(i*8+7, temp);
                        tempd = -1;
                    }
                }
                else{
                    break;
                }
            }
            if(d == 1){
                int temp = list.remove((n-1)*8+7);
                list.add((n-1)*8, temp);
            }
            else{
                int temp = list.remove((n-1)*8);
                list.add((n-1)*8+7, temp);
            }
        }
        System.out.println(list.get(0) + list.get(8)*2 + list.get(16)*4 + list.get(24)*8);
    }
}