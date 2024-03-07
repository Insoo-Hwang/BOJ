import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int total = cards.length;
        List<Integer> turnCard = new ArrayList<>();
        boolean [] hand = new boolean[total+1];
        boolean [] hidden = new boolean[total+1];
        for(int i = 0; i < total; i++){
            if(i < total/3) hand[cards[i]] = true;
            else turnCard.add(cards[i]);
        }
        int turn = 0;
        while(true){
            turn++;
            if(turnCard.isEmpty()) break;
            int one = turnCard.remove(0);
            int two = turnCard.remove(0);
            hidden[one] = true;
            hidden[two] = true;
            boolean check = false;
            for(int i = 1; i < total+1; i++){
                if(!hand[i]) continue;
                if(hand[i] && hand[total+1-i]){
                    hand[i] = false;
                    hand[total+1-i] = false;
                    check = true;
                    break;
                }
            }
            if(check) continue;
            if(coin < 1) break;
            for(int i = 1; i < total+1; i++){
                if(!hand[i]) continue;
                if(hand[i] && hidden[total+1-i]){
                    hand[i] = false;
                    hidden[total+1-i] = false;
                    check = true;
                    coin--;
                    break;
                }
            }
            if(check) continue;
            if(coin < 2) break;
            for(int i = 1; i < total+1; i++){
                if(!hidden[i]) continue;
                if(hidden[i] && hidden[total+1-i]){
                    hidden[i] = false;
                    hidden[total+1-i] = false;
                    check = true;
                    coin-=2;
                    break;
                }
            }
            if(check) continue;
            break;
        }
        return turn;
    }
}