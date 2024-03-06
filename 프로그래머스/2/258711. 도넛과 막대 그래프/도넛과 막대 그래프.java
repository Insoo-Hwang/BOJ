class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        int edgeSize = edges.length;
        int [][] A = new int[1000001][2];
        boolean [] visited = new boolean[1000001];
        for(int i = 0; i < edgeSize; i++){
            A[edges[i][0]][0]++;
            A[edges[i][1]][1]++;
            visited[edges[i][0]] = true;
            visited[edges[i][1]] = true;
        }
        for(int i = 1; i < 1000001; i++){
            if(!visited[i]) continue;
            if(A[i][0] > 1 && A[i][1] == 0) answer[0] = i;
            else if(A[i][0] == 2 && A[i][1] > 1) answer[3]++;
            else if(A[i][0] == 0) answer[2]++;
        }
        answer[1] = A[answer[0]][0]-answer[2]-answer[3];
        return answer;
    }
}