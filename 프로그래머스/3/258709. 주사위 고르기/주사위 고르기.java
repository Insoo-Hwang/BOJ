import java.util.*;

class Solution {
    static int [][] copyDice;
    static int [] calc;
    static int max = -1;
    static int win = 0;
    static int goodCase = 0;
    static List<Integer> queueA;
    static List<Integer> queueB;
    public int[] solution(int[][] dice) {
        copyDice = dice;
        int[] answer = new int[dice.length/2];
        calc = new int[dice.length/2];
        for(int i = 1; i < 1<<dice.length; i++){
            if(Integer.bitCount(i) != dice.length/2) continue;
            queueA = new ArrayList<>();
            queueB = new ArrayList<>();
            DFS(0, i, true);
            DFS(0, i, false);
            Collections.sort(queueA);
            Collections.sort(queueB);
            win = 0;
            for(int a : queueA){
                int left = 0;
                int right = queueA.size()-1;
                while(left <= right){
                    int mid = (left+right)/2;
                    if(a > queueB.get(mid)) left = mid+1;
                    else right = mid-1;
                }
                win+=left;
            }
            if(max < win){
                max = win;
                goodCase = i;
            }
        }
        int k = 0;
        for(int i = 0; i < dice.length; i++){
            if ((goodCase&(1<<i)) > 0){
                answer[k] = i+1;
                k++;
            }
        }
        return answer;
    }
    
    static void DFS(int d, int c, boolean check){
        if(d == copyDice.length/2){
            int index = 0;
            int sum = 0;
            for(int i = 0; i < copyDice.length; i++){
                if(check && (c & (1<<i)) > 0){
                    sum+=copyDice[i][calc[index]];
                    index++;
                }
                else if(!check && (c & (1<<i)) == 0){
                    sum+=copyDice[i][calc[index]];
                    index++;
                }
            }
            if(check) queueA.add(sum);
            else queueB.add(sum);
            return;
        }
        
        for(int i = 0; i < 6; i++){
            calc[d] = i;
            DFS(d+1, c, check);
        }
    }
}