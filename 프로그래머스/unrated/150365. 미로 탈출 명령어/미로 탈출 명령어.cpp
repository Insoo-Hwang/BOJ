#include <iostream>
#include <string>
#include <vector>

using namespace std;
int N;
int M;
int startN;
int startM;
int endN;
int endM;
int K;
int dy[4] = {1, 0, 0, -1};
int dx[4] = {0, -1, 1, 0};
string ds[4] = {"d", "l", "r", "u"};
string answer = "impossible";
bool check = false;

void DFS(int n, int m, int c, string s) {
    if (c == K) {
        if (n != endN || m != endM) return;
        answer = s;
        check = true;
        return;
    }
    for (int i = 0; i < 4; i++) {
        int y = n + dy[i];
        int x = m + dx[i];
        if (y < 1 || y > N || x < 1 || x > M) continue;
        if (abs(y - endN) + abs(x - endM) > K - c - 1) continue;
        if ((K - c - 1 - (abs(y - endN) + abs(x - endM))) % 2 == 1) continue;
        DFS(y, x, c + 1, s + ds[i]);
        if (check) return;
    }
}

string solution(int n, int m, int x, int y, int r, int c, int k) {
    N = n;
    M = m;
    startN = x;
    startM = y;
    endN = r;
    endM = c;
    K = k;

    DFS(startN, startM, 0, "");
    return answer;
}


    