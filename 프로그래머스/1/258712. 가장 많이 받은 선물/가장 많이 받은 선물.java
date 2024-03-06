import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        HashMap<String, Integer> map = new HashMap();
        for(int i = 0; i < friends.length; i++){
            map.put(friends[i], i);
        }
        int [][] relation = new int [friends.length][friends.length];
        int [] giftPoint = new int[friends.length];
        for(int i = 0; i < gifts.length; i++){
            String [] s = gifts[i].split(" ");
            relation[map.get(s[0])][map.get(s[1])]++;
            giftPoint[map.get(s[0])]++;
            giftPoint[map.get(s[1])]--;
        }
        int [] nextMonth = new int[friends.length];
        for(int i = 0; i < friends.length-1; i++){
            for(int j = i+1; j < friends.length; j++){
                if(relation[i][j] != relation[j][i]){
                    if(relation[i][j] > relation[j][i]) nextMonth[i]++;
                    else nextMonth[j]++;
                }
                else{
                    if(giftPoint[i] > giftPoint[j]) nextMonth[i]++;
                    else if(giftPoint[i] < giftPoint[j]) nextMonth[j]++;
                }
            }
        }
        Arrays.sort(nextMonth);
        return nextMonth[friends.length-1];
    }
}