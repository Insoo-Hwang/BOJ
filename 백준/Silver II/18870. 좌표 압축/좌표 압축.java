import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int [] A = new int[N];
        int [] B = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = A[i];
        }
        Arrays.sort(B);
        List<Integer> list = new ArrayList<>();
        int prev = B[0];
        list.add(B[0]);
        for(int i = 1; i < N; i++){
            if(prev == B[i]) continue;
            list.add(B[i]);
            prev = B[i];
        }
        int [] array = new int[list.size()];
        for(int i = 0; i < array.length; i++){
            array[i] = list.get(i);
        }
        for(int i = 0; i < N; i++){
            sb.append(Arrays.binarySearch(array, A[i])).append(" ");
        }
        System.out.println(sb);
    }
}