class Solution {
    public int solution(int n, int[] tops) {
        int [][] D = new int[n+1][2];
        D[0][0] = 1;
        D[0][1] = 0;
        for(int i = 1; i < n+1; i++){
            if(tops[i-1] == 0){
                D[i][0] = (2*D[i-1][0]+D[i-1][1])%10007;
                D[i][1] = (D[i-1][0]+D[i-1][1])%10007;
            }
            else{
                D[i][0] = (3*D[i-1][0]+2*D[i-1][1])%10007;
                D[i][1] = (D[i-1][0]+D[i-1][1])%10007;
            }
        }
        return (D[n][0]+D[n][1])%10007;
    }
}