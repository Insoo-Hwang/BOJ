import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean [] visited;
    static int [] A;
    static Queue<Integer> queue = new LinkedList<>();
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            visited = new boolean[N+1];
            A = new int[N+1];

            cnt = 0; //팀원이 없는 사람의 수
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i < N+1; i++){
                int temp = Integer.parseInt(st.nextToken());
                A[i] = temp;
                if(i == temp) {
                    visited[i] = true; //자기 자신을 선택한 경우는 이미 방문한 것으로 처리
                }
            }

            for(int i = 1; i < N+1; i++){
                if(!visited[i] && A[i] > i) { //방문 안한 사람이 자신보다 뒷 번호인 사람을 고른 경우
                    queue.clear();
                    DFS(i);
                }
                else if(!visited[i] && A[i] < i){ //방문 안한 사람이 자신보다 앞 번호인 사람을 고른 경우
                    visited[i] = true; //방문 처리
                    cnt++; //자신보다 앞사람이 골라주지 않았기 때문에 자신의 차례까지 방문하지 않았음
                }
            }

            sb.append(cnt);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void DFS(int n){
        visited[n] = true; //방문 처리
        queue.add(n); //사이클 여부확인을 위해 queue에 넣음
        if(!visited[A[n]]) { //내가 지목한 사람이 방문이 이미 되지 않았을 때
            DFS(A[n]);
        }else{
            if(queue.isEmpty() || A[n] != queue.peek()){ //queue에 내용이 있고 다음 사람이 queue의 맨 앞에 있는 경우 사이클 형성
                for(int i : queue){ //그 경우가 아닐 경우 queue에 다음 사람이 있는지 확인(앞사람 제외 사이클 여부 확인)
                    if(i == A[n]){ //있는 경우
                        break;
                    }
                    else{ //queue에 없는 경우는 팀원이 없는 경우
                        cnt++;
                    }
                }
            }
        }
    }
}
