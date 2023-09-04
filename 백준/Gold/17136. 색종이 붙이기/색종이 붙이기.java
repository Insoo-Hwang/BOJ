import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int [][] A = new int[10][10];
    static int [] paper = new int[6]; //사이즈 별로 남은 색종이 수 체크
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < 10; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.fill(paper, 5);

        int [] temp = next();
        DFS(temp[0], temp[1], 0);
        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    static void DFS(int n, int m, int c){ //(n, m)부터 붙일 수 있는 색종이 수 탐색 c는 이미 사용된 색종이 수
        if(check(n, m, 10, 1)){
            min = Math.min(min, c);
            return;
        }

        for(int i = 1; i < 6; i++){ //1~5 사이즈의 색종이를 붙일 수 있는지 확인
            if(check(n, m, i, 0) && paper[i] > 0){
                paper[i]--; //해당 사이즈 색종이 수 감소
                change(n, m, i, 0); //색종이를 붙임
                int [] temp = next();
                DFS(temp[0], temp[1], c+1); //다음 공간 탐색
                paper[i]++; //백트래킹
                change(n, m, i, 1);
            }
        }
    }

    static boolean check(int n, int m, int s, int v){ //(n, m)부터 s크기의 만큼 v가 있는지 확인
        if(n+s > 10 || m+s > 10) return false;
        for(int i = n; i < n+s; i++){
            for(int j = m; j < m+s; j++){
                if(A[i][j] == v) return false;
            }
        }
        return true;
    }

    static void change(int n, int m, int s, int v){ (n, m)부터 s크기의 만큼 v로 교체
        for(int i = n; i < n+s; i++){
            for(int j = m; j < m+s; j++){
                A[i][j] = v;
            }
        }
    }

    static int[] next(){ //다음에 색종이를 붙일 수 있는 공간 탐색
        int [] temp = new int[2];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(A[i][j] == 1){
                    temp[0] = i;
                    temp[1] = j;
                    return temp;
                }
            }
        }
        return temp;
    }
}
