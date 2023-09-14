import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        List<Belt> list = new ArrayList<>();
        for(int i = 1; i < 2*N+1; i++){
            list.add(new Belt(Integer.parseInt(st.nextToken()), false));
        }

        int cnt = 1;
        while(true){
            Belt tb = list.remove(list.size()-1);
            list.add(0, tb);
            list.get(N-1).b = false;
            for(int i = N-2; i >= 0; i--){
                if(list.get(i).b && !list.get(i+1).b && list.get(i+1).n > 0){
                    list.get(i).b = false;
                    list.get(i+1).b = true;
                    list.get(i+1).n--;
                    if(list.get(i+1).n == 0){
                        K--;
                    }
                }
            }
            list.get(N-1).b = false;
            if(list.get(0).n > 0){
                list.get(0).b = true;
                list.get(0).n--;
                if(list.get(0).n == 0){
                    K--;
                }
            }
            if(K <= 0){
                break;
            }
            cnt++;
        }
        System.out.println(cnt);
    }

    static class Belt{
        int n;
        boolean b;
        public Belt(int n, boolean b){
            this.n = n;
            this.b = b;
        }
    }
}