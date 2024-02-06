import java.util.*;
import java.io.*;

public class Main {
    static int [][] A = new int[100][100];
    static int Row = 3;
    static int Column = 3;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken())-1;
        int c = Integer.parseInt(st.nextToken())-1;
        int k = Integer.parseInt(st.nextToken());
        for(int i = 0; i < 3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if(A[r][c] == k){
            System.out.print(0);
            return;
        }
        int time = -1;
        for(int i = 1; i < 101; i++){
            if(Row >= Column) for(int j = 0; j < Row; j++) R(j);
            else for(int j = 0; j < Column; j++) C(j);
            if(A[r][c] == k){
                time = i;
                break;
            }
        }
        System.out.println(time);
    }

    static void R(int n){
        int [] count = new int [101];
        List<Sort> list = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            if(A[n][i] == 0) continue;
            count[A[n][i]]++;
            A[n][i] = 0;
        }
        for(int i = 1; i < 101; i++){
            if(count[i] == 0) continue;
            list.add(new Sort(i, count[i]));
        }
        Collections.sort(list);
        for(int i = 0; i < Math.min(50, list.size()*2); i+=2){
            Sort sort = list.get(i/2);
            A[n][i] = sort.num;
            A[n][i+1] = sort.cnt;
        }
        Column = Math.max(Column, Math.min(100, list.size()*2));
    }

    static void C(int n){
        int [] count = new int [101];
        List<Sort> list = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            if(A[i][n] == 0) continue;
            count[A[i][n]]++;
            A[i][n] = 0;
        }
        for(int i = 1; i < 101; i++){
            if(count[i] == 0) continue;
            list.add(new Sort(i, count[i]));
        }
        Collections.sort(list);
        for(int i = 0; i < Math.min(50, list.size()*2); i+=2){
            Sort sort = list.get(i/2);
            A[i][n] = sort.num;
            A[i+1][n] = sort.cnt;
        }
        Row = Math.max(Row, Math.min(100, list.size()*2));
    }

    static class Sort implements Comparable<Sort>{
        int num;
        int cnt;

        public Sort(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }

        public int compareTo(Sort o){
            if(this.cnt == o.cnt) return this.num-o.num;
            else return this.cnt-o.cnt;
        }
    }
}