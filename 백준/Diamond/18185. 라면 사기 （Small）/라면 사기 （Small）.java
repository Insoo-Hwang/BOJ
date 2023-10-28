import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int [] A = new int[N+2];
        int sum = 0;
        for(int i = 1; i < N+1; i++) {
            A[i] = sc.nextInt();
            sum += A[i];
        }

        int count = 0;
        for(int i = 1; i < N+1; i++){
            if(A[i-1] > 0){
                if(A[i-1] < A[i] && A[i+1] < A[i]){
                    count += (A[i] - A[i+1]);
                    A[i-1] -= (A[i] - A[i+1]);
                    A[i] = A[i+1];
                    if(A[i-1] > 0){
                        count += A[i-1];
                        A[i] -= A[i-1];
                        A[i+1] -= Math.min(A[i+1], A[i-1]);
                        A[i-1] = 0;
                    }
                }
                else if((A[i-1] < A[i] && A[i+1] > A[i]) || (A[i-1] < A[i] && A[i+1] == A[i])){
                    count += A[i-1];
                    A[i] -= A[i-1];
                    A[i+1] -= A[i-1];
                    A[i-1] = 0;
                }
                else if((A[i-1] == A[i] && A[i+1] < A[i]) || (A[i-1] == A[i] && A[i+1] == A[i])){
                    count += A[i];
                    A[i-1] = 0;
                    A[i+1] = 0;
                    A[i] = 0;
                }
                else if(A[i-1] == A[i] && A[i+1] > A[i]){
                    count += A[i];
                    A[i-1] = 0;
                    A[i+1] -= A[i];
                    A[i] = 0;
                }
            }
            else{
                if(A[i+1] == 0)
                    count += A[i];
                else if(A[i] > A[i+1]){
                    count += (A[i] - A[i+1]);
                    A[i] = A[i+1];
                }
            }
        }
        System.out.println(sum*2 + count);
    }
}
