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

        int start = A[0].x; //이전 항목의 시작점
        long sum = A[0].y - A[0].x; //전체 길이
        for(int i = 1; i < N; i++){
            if(start < A[i].x) continue; //현재 선이 이전 선 안에 포함되는 경우
            if(start > A[i].y){ //이전 선과 현재 선이 분리되어 있는 경우
                sum += Math.abs(A[i].y - A[i].x);
            }
            else{ //이전 선과 현재 선이 연결된 경우
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
            if(this.y == o.y){ //끝 부분 좌표가 같으면 
                return this.x - o.x; //시작 부분 기준으로 오름차순
            }
            return o.y - this.y; //끝 부분 기준으로 내림차순
        }
    }
}
