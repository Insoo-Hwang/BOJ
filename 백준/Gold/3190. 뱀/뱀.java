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
                limit = list.get(0);
            }
            if(limit.x == time){
                list.remove(0);
                time++;
                if(limit.c.equals("L")){
                    if(now == 'R'){
                        if(head[0] == 1 || snake[head[0]-1][head[1]]){
                            break;
                        }
                        snake[head[0]-1][head[1]] = true;
                        now = 'U';
                        head[0]-=1;
                        snakelist.add(new int[] {head[0], head[1]});
                    }
                    else if(now == 'U'){
                        if(head[1] == 1 || snake[head[0]][head[1]-1]){
                            break;
                        }
                        snake[head[0]][head[1]-1] = true;
                        now = 'L';
                        head[1]-=1;
                        snakelist.add(new int[] {head[0], head[1]});
                    }
                    else if(now == 'L'){
                        if(head[0] == N || snake[head[0]+1][head[1]]){
                            break;
                        }
                        snake[head[0]+1][head[1]] = true;
                        now = 'D';
                        head[0]+=1;
                        snakelist.add(new int[] {head[0], head[1]});
                    }
                    else{
                        if(head[1] == N || snake[head[0]][head[1]+1]){
                            break;
                        }
                        snake[head[0]][head[1]+1] = true;
                        now = 'R';
                        head[1]+=1;
                        snakelist.add(new int[] {head[0], head[1]});
                    }
                }
                else{
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
                    else{
                        if(head[1] == N || snake[head[0]][head[1]+1]){
                            break;
                        }
                        snake[head[0]][head[1]+1] = true;
                        now = 'R';
                        head[1]+=1;
                        snakelist.add(new int[] {head[0], head[1]});
                    }
                }
            }
            else{
                time++;
                if(now == 'R'){
                    if(head[1] == N || snake[head[0]][head[1]+1]){
                        break;
                    }
                    snake[head[0]][head[1]+1] = true;
                    head[1]+=1;
                    snakelist.add(new int[] {head[0], head[1]});
                }
                else if(now == 'L'){
                    if(head[1] == 1 || snake[head[0]][head[1]-1]){
                        break;
                    }
                    snake[head[0]][head[1]-1] = true;
                    head[1]-=1;
                    snakelist.add(new int[] {head[0], head[1]});
                }
                else if(now == 'U'){
                    if(head[0] == 1 || snake[head[0]-1][head[1]]){
                        break;
                    }
                    snake[head[0]-1][head[1]] = true;
                    head[0]-=1;
                    snakelist.add(new int[] {head[0], head[1]});
                }
                else{
                    if(head[0] == N || snake[head[0]+1][head[1]]){
                        break;
                    }
                    snake[head[0]+1][head[1]] = true;
                    head[0]+=1;
                    snakelist.add(new int[] {head[0], head[1]});
                }
            }
            if(apple[head[0]][head[1]]){
                apple[head[0]][head[1]] = false;
            }
            else{
                int [] temp = snakelist.remove(0);
                snake[temp[0]][temp[1]] = false;
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