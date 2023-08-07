import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        String N = br.readLine();
        int [] A = new int[N.length()];
        for(int i = 0; i < N.length(); i++){
            A[i] = N.charAt(i) - '0';
        }
        if(A[0] == 0){ //맨 앞이 0일 경우 암호 성립이 불가능
            System.out.println(0);
            return;
        }

        int [][] D = new int[N.length()][2]; //D[i][0]은 A[i]혼자만으로 알파벳을 이룰 경우, D[i][1]은 A[i-1]과 결합하여 알파벳을 이룰 경우
        D[0][0] = 1; D[0][1] = 0;
        for(int i = 1; i < N.length(); i++){
            if(A[i] != 0){ //A[i]가 0일 경우에는 혼자 알파벳을 이룰 수 없음
                D[i][0] = (D[i-1][0] + D[i-1][1])%1000000; //A[i]가 혼자 알파벳을 이룰 수 있으므로 이전 단계의 경우의 수를 모두 가져올 수 있음
            }
            if(A[i-1]*10 + A[i] > 26){ //A[i-1]가 결합할 수 없을 경우
                D[i][1] = 0;
            }
            else if(A[i-1]*10 + A[i] == 0){ //암호 중간에 00이 섞여있으면 암호가 성립할 수 없음
                System.out.println(0);
                return;
            }
            else{ //결합해서 알파벳이 되는 경우 이전 단계 알파벳 혼자로만 이루어져 있어야만 함
                D[i][1] = D[i-1][0];
            }
        }
        System.out.println((D[N.length()-1][0] + D[N.length()-1][1])%1000000);
    }
}
