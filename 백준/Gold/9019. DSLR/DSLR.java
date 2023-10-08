import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            boolean [] visited = new boolean[10000];
            visited[A] = true;
            Queue<Register> queue = new LinkedList<>();
            queue.add(new Register(A, ""));
            while(!queue.isEmpty()){
                Register now = queue.poll();
                if(now.n == B){
                    sb.append(now.s).append("\n");
                    break;
                }
                if(!visited[now.D()]){
                    queue.add(new Register(now.D(), now.s+"D"));
                    visited[now.D()] = true;
                }
                if(!visited[now.S()]){
                    queue.add(new Register(now.S(), now.s+"S"));
                    visited[now.S()] = true;
                }
                if(!visited[now.L()]){
                    queue.add(new Register(now.L(), now.s+"L"));
                    visited[now.L()] = true;
                }
                if(!visited[now.R()]){
                    queue.add(new Register(now.R(), now.s+"R"));
                    visited[now.R()] = true;
                }
            }
        }
        System.out.println(sb);
    }

    static class Register{
        int n;
        String s;

        public Register(int n, String s){
            this.n = n;
            this.s = s;
        }

        public int D(){
            return n*2%10000;
        }

        public int S(){
            if(n == 0) return 9999;
            else return n-1;
        }

        public int L(){
            return n%1000*10+n/1000;
        }

        public int R(){
            return n%10*1000+n/10;
        }
    }
}