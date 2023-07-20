import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int A [][] = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while(true){
            int stop = 0;
            if(A[r][c] == 0){
                A[r][c] = 2;
                cnt++;
            }
            else{
                for(int i = 3; i > -1; i--){
                    int tempr = r;
                    int tempc = c;
                    int tempd = (d+i)%4;
                    switch (tempd){
                        case 0 :
                            if(tempr > 0) tempr--;
                            break;
                        case 1 :
                            if(tempc < M-1) tempc++;
                            break;
                        case 2 :
                            if(tempr < N-1) tempr++;
                            break;
                        default :
                            if(tempc > 0) tempc--;
                            break;
                    }
                    if(tempr == r && tempc == c)
                        continue;
                    if(A[tempr][tempc] == 0){
                        r = tempr;
                        c = tempc;
                        d = tempd;
                        break;
                    }
                    if(i == 0) {
                        tempr = r;
                        tempc = c;
                        switch (d) {
                            case 0:
                                if (tempr < N - 1) tempr++;
                                break;
                            case 1:
                                if (tempc > 0) tempc--;
                                break;
                            case 2:
                                if (tempr > 0) tempr--;
                                break;
                            default:
                                if (tempc < M - 1) tempc++;
                                break;
                        }
                        if (tempr == r && tempc == c){
                            stop = 1;
                            break;
                        }
                        else if(A[tempr][tempc] == 1){
                            stop = 1;
                            break;
                        }
                        else{
                            r = tempr;
                            c = tempc;
                            break;
                        }
                    }
                }
            }
            if(stop == 1)
                break;
        }
        System.out.println(cnt);

    }
}
