import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        int line = sc.nextInt();

        int []A = new int[num];
        long []B = new long[num];
        for(int i = 0; i < num; i++){
            A[i] = sc.nextInt();
            if(i>0) B[i] = B[i-1] + A[i];
            else B[i] = A[i];
        }
        long []C = new long[line];
        for(int i = 0; i < line; i++){
            int f = sc.nextInt();
            int s = sc.nextInt();
            if(f == 1) C[i] = B[s-1];
            else C[i] = B[s-1]-B[f-2];
        }

        for (int i =0; i<line; i++){
            System.out.println(C[i]);
        }
    }
}
