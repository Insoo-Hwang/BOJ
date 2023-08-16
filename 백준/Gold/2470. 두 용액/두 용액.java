import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Liquid [] A = new Liquid[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = new Liquid(Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(A);

        long min = Integer.MAX_VALUE; //두 용액의 특성값의 합이 0에 가까운 값
        int [] B = new int[2]; //최소값을 만들 수 있는 두 용액
        for(int i = 0; i < N-1; i++){
            if(min > Math.abs(A[i].v + A[i+1].v)){ //0에 가까운 두 용액을 발견하면
                min = Math.abs(A[i].v + A[i+1].v); //0에 가까운 값 업데이트
                B[0] = A[i].v; //두 용액 정보 저장
                B[1] = A[i+1].v;
            }
        }
        Arrays.sort(B);
        for(int i : B){
            System.out.print(i + " ");
        }
    }
    static class Liquid implements Comparable<Liquid>{
        int v; //용액의 특성값
        int tv; //용액의 특성값의 절대값
        public Liquid(int v){
            this.v = v;
            tv = Math.abs(v);
        }
        @Override
        public int compareTo(Liquid o) { //절대값 기준 오름차순, 절대값이 같을 경우 특성값의 오름차순
            if(this.tv == o.tv){
                return this.v - o.v;
            }
            return this.tv - o.tv;
        }
    }
}
