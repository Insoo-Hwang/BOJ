import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        boolean [][] apple = new boolean[N+1][N+1];
        boolean [][] snake = new boolean[N+2][N+2];
        snake[1][1] = true;
        List<int[]> snakelist = new ArrayList<>();
        snakelist.add(new int[] {1,1});
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            apple[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }
        int L = Integer.parseInt(br.readLine());
        List<Move> list = new ArrayList<>();
        for(int i = 0; i < L; i++){
            st = new StringTokenizer(br.readLine());
            list.add(new Move(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        int time = 0;
        Move limit = new Move(-1, "a");
        char now = 'R';
        int [] head = {1, 1};
        while(true){
            if(!list.isEmpty()) {
                limit = list.get(0); //방향 바꿀 시간 및 방향 확인
            }
            if(limit.x == time){ //방향을 바꿀 시간이 되면
                list.remove(0);
                time++;
                if(limit.c.equals("L")){ //왼쪽으로 방향 바꾸기
                    if(now == 'R'){ //현재 방향이 오른쪽일 때
                        if(head[0] == 1 || snake[head[0]-1][head[1]]){ //더 이상 이동할 수 없는 경우(벽에 부딪히거나 자신의 몸에 닿았을 때)
                            break;
                        }
                        snake[head[0]-1][head[1]] = true; //위쪽 방향으로 이동
                        now = 'U'; //현재 방향 업데이트
                        head[0]-=1;
                        snakelist.add(new int[] {head[0], head[1]}); //꼬리 체크를 위해 이동한 칸 업데이트
                    }
                    else if(now == 'U'){ //현재 방향이 위쪽일 때
                        if(head[1] == 1 || snake[head[0]][head[1]-1]){
                            break;
                        }
                        snake[head[0]][head[1]-1] = true;
                        now = 'L';
                        head[1]-=1;
                        snakelist.add(new int[] {head[0], head[1]});
                    }
                    else if(now == 'L'){ //현재 방향이 왼쪽일 때
                        if(head[0] == N || snake[head[0]+1][head[1]]){
                            break;
                        }
                        snake[head[0]+1][head[1]] = true;
                        now = 'D';
                        head[0]+=1;
                        snakelist.add(new int[] {head[0], head[1]});
                    }
                    else{ //현재 방향이 아래쪽일 때
                        if(head[1] == N || snake[head[0]][head[1]+1]){
                            break;
                        }
                        snake[head[0]][head[1]+1] = true;
                        now = 'R';
                        head[1]+=1;
                        snakelist.add(new int[] {head[0], head[1]});
                    }
                }
                else{ //오른른쪽으로 방향 바꾸기
                    if(now == 'R'){
                        if(head[0] == N || snake[head[0]+1][head[1]]){
                            break;
                        }
                        snake[head[0]+1][head[1]] = true;
                        now = 'D';
                        head[0]+=1;
                        snakelist.add(new int[] {head[0], head[1]});
                    }
                    else if(now == 'D'){
                        if(head[1] == 1 || snake[head[0]][head[1]-1]){
                            break;
                        }
                        snake[head[0]][head[1]-1] = true;
                        now = 'L';
                        head[1]-=1;
                        snakelist.add(new int[] {head[0], head[1]});
                    }
                    else if(now == 'L'){
                        if(head[0] == 1 || snake[head[0]-1][head[1]]){
                            break;
                        }
                        snake[head[0]-1][head[1]] = true;
                        now = 'U';
                        head[0]-=1;
                        snakelist.add(new int[] {head[0], head[1]});
                    }
                    else로
            }
        }
        System.out.println(time);
    }

    static class Move{
        int x;
        String c;
        public Move(int x, String c){
            this.x = x;
            this.c = c;
        }
    }
}
