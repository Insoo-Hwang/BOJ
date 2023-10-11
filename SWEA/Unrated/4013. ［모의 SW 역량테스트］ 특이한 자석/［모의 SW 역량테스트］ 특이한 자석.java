import java.util.*;

class Solution
{
    static List<Integer> list;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int K = sc.nextInt();
			list = new ArrayList<>();
			for(int i = 0; i < 32; i++) {
				list.add(sc.nextInt());
			}
			for(int i = 0; i < K; i++) {
				int mag = sc.nextInt();
				int d = sc.nextInt();
				int left = list.get((mag-1)*8+6);
				int right = list.get((mag-1)*8+2);
				rotate(mag, d);
				int dd = d;
				for(int j = mag-1; j > 0; j--) {
					dd = -dd;
					int r = list.get((j-1)*8+2);
					if(r == left) break;
					left = list.get((j-1)*8+6);
					rotate(j, dd);
				}
				dd = d;
				for(int j = mag+1; j < 5; j++) {
					dd = -dd;
					int l = list.get((j-1)*8+6);
					if(l == right) break;
					right = list.get((j-1)*8+2);
					rotate(j, dd);
				}
			}
			int score = 0;
			for(int i = 0; i < 4; i++) {
				score+=list.get(i*8)*Math.pow(2, i);
			}
			System.out.println("#" + test_case + " " + score);
		}
	}
    	static void rotate(int n, int d) {
		if(d == 1) {
			int temp = list.remove(8*(n-1)+7);
			list.add(8*(n-1), temp);
		}
		else {
			int temp = list.remove(8*(n-1));
			list.add(8*(n-1)+7, temp);
		}
	}
}