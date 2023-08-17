import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        XY [] A = new XY[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            A[i] = new XY(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(A);

        int start = A[0].x;
        long sum = A[0].y - A[0].x;
        for(int i = 1; i < N; i++){
            if(start < A[i].x) continue;
            if(start > A[i].y){
                sum += Math.abs(A[i].y - A[i].x);
            }
            else{
                sum += Math.abs(start - A[i].x);
            }
            start = A[i].x;
        }
        System.out.println(sum);
    }
    static class XY implements Comparable<XY>{
        int x;
        int y;
        public XY(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(XY o) {
            if(this.y == o.y){
                return this.x - o.x;
            }
            return o.y - this.y;
        }
    }
}