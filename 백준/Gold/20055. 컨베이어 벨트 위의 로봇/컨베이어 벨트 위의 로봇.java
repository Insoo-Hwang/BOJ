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
            list.add(0, tb); //컨베이어 벨트를 오른쪽으로 이동
            list.get(N-1).b = false; //내리는 곳에서 박스를 내림
            for(int i = N-2; i >= 0; i--){ //박스가 이동 가능하면 한칸 이동
                if(list.get(i).b && !list.get(i+1).b && list.get(i+1).n > 0){
                    list.get(i).b = false;
                    list.get(i+1).b = true;
                    list.get(i+1).n--;
                    if(list.get(i+1).n == 0){ //내구성이 0이 되면 K감소
                        K--;
                    }
                }
            }
            list.get(N-1).b = false; //내리는 곳에서 박스를 내림
            if(list.get(0).n > 0){ //올리는 곳에서 박스를 올림
                list.get(0).b = true;
                list.get(0).n--;
                if(list.get(0).n == 0){ //내구성이 0이 되면 K감소
                    K--;
                }
            }
            if(K <= 0){ //내구성이 0인 벨트가 K개 이상이면 중지
                break;
            }
            cnt++;
        }
        System.out.println(cnt);
    }

    static class Belt{
        int n; //벨트의 내구성
        boolean b; //박스 유무
        public Belt(int n, boolean b){
            this.n = n;
            this.b = b;
        }
    }
}
