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

        visited = new boolean[10]; //라인업에 선택됐는지 확인용 배열
        visited[1] = true; //1번 선수는 항상 4번 타자
        lineup = new int[10]; //라인업 정보 배열
        lineup[4] = 1; //1번 선수가 항상 4번 타자
        DFS(1); //1번 타자부터 찾기 시작
        System.out.println(max);
    }

    static void DFS(int d){ //d번 타자 찾기
        if(d == 4){ //4번 타자는 스킵
            d++;
        }
        if(d == 10){ //9번 타자까지 다 찾으면 게임 플레이
            max = Math.max(max, game());
            return;
        }

        for(int i = 2; i < 10; i++){
            if(!visited[i]){
                visited[i] = true; //i번 선수를 d번 타자로
                lineup[d] = i;
                DFS(d+1);
                visited[i] = false; //i번 선수는 d번 타자에서 스킵
            }
        }
    }

    static int game(){
        int score = 0; //점수
        int hitter = 1; //현재 타자
        int inning = 1; //현재 이닝
        while(inning < N+1){
            int out = 0; //현재 아웃
            boolean [] base = new boolean[4]; //현재 베이스 상황
            while(out < 3){
                int result = A[inning][lineup[hitter]]; //타석의 결과
                if(result == 0){ //아웃
                    out++;
                }
                else if(result == 1){ //안타
                    if(base[3]){
                        score++;
                        base[3] = false;
                    }
                    base[3] = base[2];
                    base[2] = false;
                    base[2] = base[1];
                    base[1] = true;
                }
                else if(result == 2){ //2루타
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
                else if(result == 3){ //3루타
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
                else{ //홈런
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
                hitter++; //타석이 넘어감
                if(hitter == 10) hitter = 1; //9번 타자 다음은 1번 타자
            }
            inning++;
        }
        return score;
    }
}
