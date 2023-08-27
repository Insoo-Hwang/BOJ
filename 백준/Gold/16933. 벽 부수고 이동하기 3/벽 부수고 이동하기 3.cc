#include <iostream>
#include <queue>
#include <cstring>
using namespace std;

struct NM {
    int n, m, k, c, t;
    NM(int n, int m, int k, int c, int t) : n(n), m(m), k(k), c(c), t(t) {}
};

int N, M, K;
int A[1000][1000];
int dy[] = {-1, 1, 0, 0};
int dx[] = {0, 0, -1, 1};
int min_dist = -1;

void BFS() {
    queue<NM> q;
    q.push(NM(0, 0, 0, 1, 0));
    bool visited[1000][1000][11][2];
    memset(visited, false, sizeof(visited));
    visited[0][0][0][0] = true;

    while (!q.empty()) {
        NM now = q.front();
        q.pop();

        if (now.n == N-1 && now.m == M-1) {
            min_dist = now.c;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int tempn = now.n + dy[i];
            int tempm = now.m + dx[i];

            if (tempn < 0 || tempn >= N || tempm < 0 || tempm >= M) continue;

            if (A[tempn][tempm] == 1 && now.k < K && !visited[tempn][tempm][now.k + 1][1] && now.t == 0) {
                q.push(NM(tempn, tempm, now.k + 1, now.c + 1, 1));
                visited[tempn][tempm][now.k + 1][1] = true;
            } else if (A[tempn][tempm] == 1 && now.k < K && !visited[now.n][now.m][now.k][0] && now.t == 1) {
                q.push(NM(now.n, now.m, now.k, now.c + 1, 0));
                visited[now.n][now.m][now.k][0] = true;
            } else if (A[tempn][tempm] == 0 && !visited[tempn][tempm][now.k][(now.t + 1) % 2]) {
                q.push(NM(tempn, tempm, now.k, now.c + 1, (now.t + 1) % 2));
                visited[tempn][tempm][now.k][(now.t + 1) % 2] = true;
            }
        }
    }
}

int main() {
    cin >> N >> M >> K;
    for (int i = 0; i < N; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < M; j++) {
            A[i][j] = s[j] - '0';
        }
    }

    BFS();
    cout << min_dist << endl;
    return 0;
}
