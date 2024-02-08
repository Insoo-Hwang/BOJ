import java.util.*;

public class Main {
    static long [] tree;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int Q = sc.nextInt();
        int h = 0;
        int length = N;
        while(length != 0){
            length /= 2;
            h++;
        }
        int size = (int)Math.pow(2, h+1);
        int startindex = size/2 - 1;
        tree = new long[size + 1];
        for(int i = startindex + 1; i <= startindex + N; i++)
            tree[i] = sc.nextLong();
        setTree(size-1);

        for(int i = 0; i < Q; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(x > y){
                x^=y;
                y^=x;
                x^=y;
            }
            int a = sc.nextInt();
            long b = sc.nextLong();
            x += startindex;
            y += startindex;
            System.out.println(sum(x, y));
            change(startindex+a, b);
        }
    }
    static void setTree(int i){
        while(i != 1){
            tree[i/2] += tree[i];
            i--;
        }
    }
    static void change(int i, long v){
        long diff = v - tree[i];
        while (i > 0){
            tree[i] += diff;
            i /= 2;
        }
    }
    static long sum(int s, int e){
        long temp = 0;
        while(s <= e){
            if(s % 2 == 1) {
                temp += tree[s];
                s++;
            }
            if(e % 2 == 0){
                temp += tree[e];
                e--;
            }
            s /= 2;
            e /= 2;
        }
        return temp;
    }
}
