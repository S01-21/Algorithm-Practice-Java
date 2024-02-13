#include <bits/stdc++.h>
using namespace std;

int board[100005];
int state[100005];
int N;

const int NOT_VISITED = 0;    // 맨 처음 모든 원소
const int CYCLE_IN = -1;        // 사이클 포함 원소

void run(int x){    // 임의의 원소 x에서 부터 계속 이동
    int cur = x;
    while (true){
        state[cur] = x;
        cur = board[cur];
        // 이번 방문에서 지나간 학생에 재방문 시
        if (state[cur] == x){
            while (state[cur] != CYCLE_IN){
                state[cur] = CYCLE_IN;
                cur = board[cur];
            }
            return;
        }
        // 이전 방문에서 지나간 학생에 재방문 시
        if (state[cur] != 0)    return;
    }
}

int main(void){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int T;
    cin >> T;
    while (T--){
        cin >> N;
        fill(state+1, state+1+N, 0);
        for (int i=1; i<=N; i++)    cin >> board[i];
        for (int i=1; i<=N; i++)
            if (state[i] == NOT_VISITED)    run(i);
        int cnt = 0;
        for (int i=1; i<=N; i++)
            if (state[i] != CYCLE_IN)    cnt++;
        cout << cnt << '\n';
    }
}
