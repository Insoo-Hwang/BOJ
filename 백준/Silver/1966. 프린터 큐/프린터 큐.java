import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        while (T-->0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Queue<Integer> queue1 = new LinkedList<>(); //중요도를 순서대로 받는 queue
            List<Integer> queue2 = new LinkedList<>();  //중요도를 내림차순으로 정렬할 queue
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                int temp = Integer.parseInt(st.nextToken());
                queue1.add(temp);
                queue2.add(temp);
            }
            Collections.sort(queue2, Collections.reverseOrder());
            int cnt = 0; //몇번째로 인쇄되는지 count
            while (true){
                int temp = queue1.poll(); //프린터 queue 맨 앞 값을 추출
                if(temp == queue2.get(0)){ //오름차순 queue의 맨 앞 값과 같으면 
                    cnt++; //출력하므로 count += 1
                    if(M == 0) //만약에 M번째 인쇄물이면 중지
                        break;
                    M--; //맨 앞 인쇄물이 없어졌으므로 M-1번째로 땡겨짐
                    queue2.remove(0);
                }
                else{
                    M--; //앞 인쇄물이 뒤로 빠짐
                    if(M < 0) M = queue1.size(); //만약 뒤로 빠진 인쇄물이 M번째 인쇄물이었으면 맨 뒤순번을 받음(순번은 0부터 시작)
                    queue1.add(temp); //맨 뒤로 빠짐
                }
            }
            System.out.println(cnt);
        }
    }
}
