import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>(); //0~7 : 1번, 8~15 : 2번, 16~23 : 3번, 24~31 : 4번
        for(int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                list.add(s.charAt(j)-'0');
            }
        }

        int K = Integer.parseInt(br.readLine());
        while(K-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int L = list.get((n-1)*8+6); //현재 톱니바퀴의 9시 방향
            int R = list.get((n-1)*8+2); //현재 톱니바퀴의 3시 방향
            int tempd = d;
            for(int i = n-2; i >= 0; i--){ //좌측 톱니바퀴 탐색
                if(list.get(i*8+2) != L){ //현재 톱니바퀴의 3시 방향이 오른쪽 톱니바퀴의 9시 방향과 다르면
                    L = list.get(i*8+6); //다음 톱니바퀴 탐색을 위해 L 업데이트
                    if(tempd == -1){ //이전 톱니바퀴가 반시계 방향이면 시계 방향으로 회전
                        int temp = list.remove(i*8+7);
                        list.add(i*8, temp);
                        tempd = 1; //방향을 바꿈
                    }
                    else{ //이전 톱니바퀴가 시계 방향이면 반시계 방향으로 회전
                        int temp = list.remove(i*8);
                        list.add(i*8+7, temp);
                        tempd = -1;
                    }
                }
                else{ //톱니바퀴가 움직이지 않으면 정지
                    break;
                }
            }
            tempd = d;
            for(int i = n; i < 4; i++){ //우측 톱니바퀴 탐색
                if(list.get(i*8+6) != R){
                    R = list.get(i*8+2);
                    if(tempd == -1){
                        int temp = list.remove(i*8+7);
                        list.add(i*8, temp);
                        tempd = 1;
                    }
                    else{
                        int temp = list.remove(i*8);
                        list.add(i*8+7, temp);
                        tempd = -1;
                    }
                }
                else{
                    break;
                }
            }
            if(d == 1){ //처음 움직인 톱니바퀴 회전
                int temp = list.remove((n-1)*8+7);
                list.add((n-1)*8, temp);
            }
            else{
                int temp = list.remove((n-1)*8);
                list.add((n-1)*8+7, temp);
            }
        }
        System.out.println(list.get(0) + list.get(8)*2 + list.get(16)*4 + list.get(24)*8);
    }
}
