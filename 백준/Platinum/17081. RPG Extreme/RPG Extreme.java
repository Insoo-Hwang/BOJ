import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static char [][] A;
    static int [] start = new int[2];
    static char [] command;
    static ME me;
    static String message = "Press any key to continue.";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new char[N][M];
        int K = 1;
        int L = 0;
        for(int i = 0; i < N; i++){
            char [] s = br.readLine().toCharArray();
            for(int j = 0; j < M; j++){
                A[i][j] = s[j];
                if(A[i][j] == '@'){
                    start[0] = i;
                    start[1] = j;
                    A[i][j] = '.';
                }
                if(A[i][j] == '&') K++;
                if(A[i][j] == 'B') L++;
            }
        }
        command = br.readLine().toCharArray();
        Monster [][] monsterMap = new Monster[N][M];
        for(int i = 0; i < K; i++){
            String [] s = br.readLine().split(" ");
            int n = Integer.parseInt(s[0])-1;
            int m = Integer.parseInt(s[1])-1;
            int w = Integer.parseInt(s[3]);
            int a = Integer.parseInt(s[4]);
            int h = Integer.parseInt(s[5]);
            int e = Integer.parseInt(s[6]);
            monsterMap[n][m] = new Monster(n, m, s[2], w, a, h, e);
        }
        Item [][] itemMap = new Item[N][M];
        for(int i = 0; i < L; i++){
            String [] s = br.readLine().split(" ");
            int n = Integer.parseInt(s[0])-1;
            int m = Integer.parseInt(s[1])-1;
            itemMap[n][m] = new Item(n, m, s[2], s[3]);
        }
        me = new ME();
        int turn = command.length;
        for(int i = 0; i < command.length; i++){
            //캐릭터 이동
            int y = me.n;
            int x = me.m;
            if(command[i] == 'L') x--;
            else if(command[i] == 'R') x++;
            else if(command[i] == 'U') y--;
            else if(command[i] == 'D') y++;
            if(y > N-1 || y < 0 || x > M-1 || x < 0 || A[y][x] == '#'){
                y = me.n;
                x = me.m;
            }
            me.n = y;
            me.m = x;
            //아이템
            if(A[y][x] == 'B'){ //무기
                Item get = itemMap[y][x];
                if(get.type.equals("W")){
                    me.plusA = Integer.parseInt(get.attr);
                }
                else if(get.type.equals("A")){ //방어구
                    me.plusD = Integer.parseInt(get.attr);
                }
                else{ //장신구
                    int type = -1;
                    String attr = get.attr;
                    if(attr.equals("HR")) type = 0;
                    else if(attr.equals("RE")) type = 1;
                    else if(attr.equals("CO")) type = 2;
                    else if(attr.equals("EX")) type = 3;
                    else if(attr.equals("DX")) type = 4;
                    else if(attr.equals("HU")) type = 5;
                    else if(attr.equals("CU")) type = 6;
                    if(me.BOcnt < 4 && !me.isBO[type]){ //착용가능할떄
                        me.BOcnt++;
                        me.isBO[type] = true;
                    }
                }
                A[y][x] = '.';
            }
            //가시
            else if(A[y][x] == '^'){
                int damage = 5;
                if(me.isBO[4]) damage = 1;
                me.hp-=damage;
                if(me.hp <= 0){ //죽으면
                    if(me.isBO[1]){ //부활 가능
                        me.n = start[0];
                        me.m = start[1];
                        me.hp = me.maxHp;
                        me.BOcnt--;
                        me.isBO[1] = false;
                    }
                    else{
                        turn = i+1;
                        me.hp = 0;
                        message = "YOU HAVE BEEN KILLED BY SPIKE TRAP..";
                        break;
                    }
                }
            }
            //몬스터
            else if(A[y][x] == '&' || A[y][x] == 'M'){
                Monster meet = monsterMap[y][x];
                int CO = 1;
                if(me.isBO[2] && me.isBO[4]) CO = 3;
                else if(me.isBO[2]) CO = 2;
                int bossA = 1;
                if(meet.boss && me.isBO[5]){
                    me.hp = me.maxHp;
                    bossA = 0;
                }
                while(true){
                    meet.h-=Math.max(1, CO*(me.a+me.plusA)-meet.a);
                    CO = 1;
                    if(meet.h <= 0) break;
                    if(bossA != 0) me.hp-=Math.max(1, meet.w-me.d-me.plusD);
                    bossA = 1;
                    if(me.hp <= 0) break;
                }
                if(meet.h <= 0){ //몬스터를 잡은 경우
                    if(me.isBO[0]) me.hp = Math.min(me.hp+3, me.maxHp);
                    double e = 1.0;
                    if(me.isBO[3]) e = 1.2;
                    me.exp+=e*meet.e;
                    if(me.exp >= 5*me.level){ //레벨업
                        me.exp = 0;
                        me.level++;
                        me.maxHp+=5;
                        me.a+=2;
                        me.d+=2;
                        me.hp = me.maxHp;
                    }
                    A[y][x] = '.';
                    if(meet.boss){ //보스를 잡았을 시
                        turn = i+1;
                        message = "YOU WIN!";
                        break;
                    }
                }
                else{
                    if(me.isBO[1]){ //부활 가능
                        me.n = start[0];
                        me.m = start[1];
                        me.hp = me.maxHp;
                        me.BOcnt--;
                        me.isBO[1] = false;
                    }
                    else{
                        turn = i+1;
                        me.hp = 0;
                        message = "YOU HAVE BEEN KILLED BY " + meet.name + "..";
                        break;
                    }
                }
            }
            System.out.print("");
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(i == me.n && j == me.m && me.hp > 0) System.out.print("@");
                else System.out.print(A[i][j]);
            }
            System.out.println();
        }
        System.out.println("Passed Turns : " + turn);
        System.out.println("LV : " + me.level);
        System.out.println("HP : " + me.hp + "/" + me.maxHp);
        System.out.println("ATT : " + me.a + "+" + me.plusA);
        System.out.println("DEF : " + me.d + "+" + me.plusD);
        System.out.println("EXP : " + me.exp + "/" + 5*me.level);
        System.out.println(message);
    }

    static class Monster{
        int n;
        int m;
        String name;
        int w; //공격력
        int a; //방어력
        int h; //최대 체력
        int e; //경험치
        boolean boss;

        public Monster(int n, int m, String name, int w, int a, int h, int e){
            this.n = n;
            this.m = m;
            this.name = name;
            this.w = w;
            this.a = a;
            this.h = h;
            this.e = e;
            if(A[n][m] == 'M') boss = true;
            else boss = false;
        }
    }

    static class Item{
        int n;
        int m;
        String type;
        String attr; //무기/방어구는 각각 공격력/방어력, 장신구 일시 타입

        public Item(int n, int m, String type, String attr){
            this.n = n;
            this.m = m;
            this.type = type;
            this.attr = attr;
        }
    }

    static class ME{
        int n;
        int m;
        int maxHp; //최대 체력
        int hp; //체력
        int a; //공격력
        int plusA; //추가 공격력
        int d; //방어력
        int plusD; //추가 방어력
        int exp; //경험치
        int level; //레벨
        boolean [] isBO; //HR RE CO EX DX HU CU
        int BOcnt; //장착 장신구 수

        public ME(){
            n = start[0];
            m = start[1];
            maxHp = 20;
            hp = 20;
            a = 2;
            plusA = 0;
            d = 2;
            plusD = 0;
            exp = 0;
            level = 1;
            isBO = new boolean[7];
            BOcnt = 0;
        }
    }
}