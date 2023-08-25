import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int [][] A;
    static boolean [] visited;
    static int [] lineup;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N+1][10];
        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < 10; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[10];
        visited[1] = true;
        lineup = new int[10];
        lineup[4] = 1;
        DFS(1);
        System.out.println(max);
    }

    static void DFS(int d){
        if(d == 4){
            d++;
        }
        if(d == 10){
            max = Math.max(max, game());
            return;
        }

        for(int i = 2; i < 10; i++){
            if(!visited[i]){
                visited[i] = true;
                lineup[d] = i;
                DFS(d+1);
                visited[i] = false;
            }
        }
    }

    static int game(){
        int score = 0;
        int hitter = 1;
        int inning = 1;
        while(inning < N+1){
            int out = 0;
            boolean [] base = new boolean[4];
            while(out < 3){
                int result = A[inning][lineup[hitter]];
                if(result == 0){
                    out++;
                }
                else if(result == 1){
                    if(base[3]){
                        score++;
                        base[3] = false;
                    }
                    base[3] = base[2];
                    base[2] = false;
                    base[2] = base[1];
                    base[1] = true;
                }
                else if(result == 2){
                    if(base[3]){
                        score++;
                        base[3] = false;
                    }
                    if(base[2]){
                        score++;
                        base[2] = false;
                    }
                    base[3] = base[1];
                    base[1] = false;
                    base[2] = true;
                }
                else if(result == 3){
                    if(base[3]){
                        score++;
                        base[3] = false;
                    }
                    if(base[2]){
                        score++;
                        base[2] = false;
                    }
                    if(base[1]){
                        score++;
                        base[1] = false;
                    }
                    base[3] = true;
                }
                else{
                    if(base[3]){
                        score++;
                        base[3] = false;
                    }
                    if(base[2]){
                        score++;
                        base[2] = false;
                    }
                    if(base[1]){
                        score++;
                        base[1] = false;
                    }
                    score++;
                }
                hitter++;
                if(hitter == 10) hitter = 1;
            }
            inning++;
        }
        return score;
    }
}