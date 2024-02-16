import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int [] A = new int[n];

        for(int i = 0; i < n; i++)
            A[i] = sc.nextInt();

        int m = sc.nextInt();
        int [] B = new int[m];

        for(int i = 0; i < m; i++)
            B[i] = sc.nextInt();

        Arrays.sort(A);

        for(int i = 0; i < m; i++){
            if(Arrays.binarySearch(A, B[i]) >= 0) B[i] = 1;
            else B[i] = 0;
        }

        for(int i = 0; i < m; i++)
            System.out.println(B[i]);
    }
}