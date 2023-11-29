#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N, M;
vector<int> *A;
int *B;

void BFS(int n) {
    bool *visited = new bool[N + 1]();
    queue<int> q;
    visited[n] = true;
    q.push(n);

    while (!q.empty()) {
        int temp = q.front();
        q.pop();

        for (int i : A[temp]) {
            if (visited[i]) continue;
            B[i]++;
            visited[i] = true;
            q.push(i);
        }
    }

    delete[] visited;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M;
    A = new vector<int>[N + 1];

    for (int i = 1; i <= N; i++) {
        A[i] = vector<int>();
    }

    for (int i = 0; i < M; i++) {
        int s, e;
        cin >> s >> e;
        A[s].push_back(e);
    }

    B = new int[N + 1]();

    for (int i = 1; i <= N; i++) {
        BFS(i);
    }

    int max = 0;
    for (int i = 1; i <= N; i++) {
        max = std::max(max, B[i]);
    }

    for (int i = 1; i <= N; i++) {
        if (max == B[i]) {
            cout << i << " ";
        }
    }

    delete[] A;
    delete[] B;

    return 0;
}
