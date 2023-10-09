import java.util.*;
import java.io.*;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            int K = sc.nextInt();
            String s = sc.next();
            List<Character> list = new ArrayList<>();
            for(int i = 0; i < N; i++){
                list.add(s.charAt(i));
            }
            HashSet<Long> hash = new HashSet<>();
            int term = N/4;
            for(int i = 0; i < term; i++){
                for(int j = 0; j < 4; j++){
                    String temp = "";
                    for(int k = 0; k < term; k++){
                        temp+=list.get(j*term+k);
                    }
                    hash.add(Long.parseLong(temp, 16));
                }
                char tt = list.remove(N-1);
                list.add(0, tt);
            }
            List<Long> list2 = new ArrayList<>();
            for(long i : hash){
                list2.add(i);
            }
            Collections.sort(list2, Collections.reverseOrder());
            System.out.print("#" + test_case + " ");
            System.out.println(list2.get(K-1));
        }
    }
}