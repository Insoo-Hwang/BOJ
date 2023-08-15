import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int [][] A;
    static int [][] B;
    static boolean[] visited;
    static int house;
    static int chicken;
    static int delete;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        A = new int[N*2][2]; //집의 위치 저장 배열
        B = new int[13][2]; //치킨집의 위치 저장 배열
        house = 0;
        chicken = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 1){
                    A[house][0] = i;
                    A[house][1] = j;
                    house++;
                }
                else if(temp == 2){
                    B[chicken][0] = i;
                    B[chicken][1] = j;
                    chicken++;
                }
            }
        }
        visited = new boolean[chicken]; //폐업이 선택된 치킨집 구분 배열
        delete = chicken - M;

        DFS(0, 0);
        System.out.println(min);
    }

    static void DFS(int p, int n){ //p는 선택된 치킨집의 수, n은 치킨집의 순서
        if(n == chicken && p != delete) return;
        if(p == delete){
            chickenRoute();
            return;
        }
        visited[n] = true; //폐업이 선택된 경우의 수
        DFS(p+1, n+1);
        visited[n] = false; //폐업이 선택되지 않은 경우의 수
        DFS(p, n+1);
    }

    static void chickenRoute(){
        int temp = 0; //전체 치킨 거리의 합
        for(int i = 0; i < house; i++){
            int tempmin = Integer.MAX_VALUE;
            for(int j = 0; j < chicken; j++){
                if(!visited[j]){ //폐업이 선택되지 않은 치킨집인 경우
                    tempmin = Math.min(tempmin, Math.abs(A[i][0] - B[j][0]) + Math.abs(A[i][1] - B[j][1])); //치킨 거리 업데이트
                }
            }
            temp += tempmin;
        }
        min = Math.min(min, temp);
    }
}
