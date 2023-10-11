import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static int p;
	static int [][] stair;
	static int [][] move;
	static int min;
	static PriorityQueue<Person> queue1 = new PriorityQueue<>();
	static PriorityQueue<Person> queue2 = new PriorityQueue<>();
	static List<Person> s1 = new ArrayList<>();
	static List<Person> s2 = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			stair = new int[2][3];
			int s = 0;
			int [][] people = new int[10][2];
			p = 0;
			int [][] A = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					A[i][j] = sc.nextInt();
					if(A[i][j] > 1) {
						stair[s][0] = i;
						stair[s][1] = j;
						stair[s][2] = A[i][j];
						s++;
					}
					else if(A[i][j] == 1) {
						people[p][0] = i;
						people[p][1] = j;
						p++;
					}
				}
			}
			move = new int [p][3];
			for(int i = 0; i < p; i++) {
				move[i][0] = Math.abs(stair[0][0]-people[i][0])+Math.abs(stair[0][1]-people[i][1]);
				move[i][1] = Math.abs(stair[1][0]-people[i][0])+Math.abs(stair[1][1]-people[i][1]);
			}
			min = Integer.MAX_VALUE;
			DFS(0);
			System.out.println("#" + test_case + " " + min);
		}
	}
	
	static void DFS(int d) {
		if(d == p) {
			queue1.clear();
			queue2.clear();
			s1.clear();
			s2.clear();
			for(int i = 0; i < p; i++) {
				if(move[i][2] == 1) {
					queue1.add(new Person(move[i][0]));
				}
				else {
					queue2.add(new Person(move[i][1]));
				}
			}
			int time = 0;
			int cnt = 0;
			while(cnt < p) {
				time++;
				int remove = -1;
				for(int i = 0; i < s1.size(); i++){
					Person temp = s1.get(i);
					if(temp.d+stair[0][2] == time) {
						remove = i;
					}
					else break;
				}
				for(int i = remove; i >= 0; i--) {
					s1.remove(i);
				}
				remove = -1;
				for(int i = 0; i < s2.size(); i++){
					Person temp = s2.get(i);
					if(temp.d+stair[1][2] == time) {
						remove = i;
					}
					else break;
				}
				for(int i = remove; i >= 0; i--) {
					s2.remove(i);
				}
				while(s1.size() < 3 && queue1.size() > 0) {
					Person temp = queue1.remove();
					if(time <= temp.d) {
						queue1.add(temp);
						break;
					}
					temp.d = time;
					s1.add(temp);
					cnt++;
				}
				while(s2.size() < 3 && queue2.size() > 0) {
					Person temp = queue2.remove();
					if(time <= temp.d) {
						queue2.add(temp);
						break;
					}
					temp.d = time;
					s2.add(temp);
					cnt++;
				}
			}
			if(s1.size() != 0 && s2.size() != 0) {
				time = Math.max(s1.get(s1.size()-1).d+stair[0][2], s2.get(s2.size()-1).d+stair[1][2]);
			}
			else if(s1.size() == 0 && s2.size() != 0) {
				time = s2.get(s2.size()-1).d+stair[1][2];
			}
			else if(s1.size() != 0 && s2.size() == 0) {
				time = s1.get(s1.size()-1).d+stair[0][2];
			}
			min = Math.min(min, time);
			return;
		}
		
		move[d][2] = 1;
		DFS(d+1);
		move[d][2] = 2;
		DFS(d+1);
	}
	
	static class Person implements Comparable<Person>{
		int d;
		
		public Person(int d) {
			this.d = d;
		}

		@Override
		public int compareTo(Person o) {
			return this.d-o.d;
		}
		
	}
}