// Problem I -- Lich
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>
using namespace std;

const int N = 1111;

int n, range, limit, damage, x[N], y[N], hp[N], times[N], dist[N];
bool graph[N][N], visit[N];

long long sqr(int x) {
    return (long long)x * x;
}

int main() {
    int testCount;
    scanf("%d", &testCount);
    while (testCount > 0) {
        testCount --;
        scanf("%d%d%d%d", &n, &range, &limit, &damage);
        for (int i = 0; i < n; ++ i) {
            scanf("%d%d%d", x + i, y + i, hp + i);
            times[i] = (hp[i] + damage - 1) / damage;
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                graph[i][j] = sqr(x[i] - x[j]) + sqr(y[i] - y[j]) <= sqr(range);
            }
        }
        for (int i = 0; i < n; ++ i) {
            dist[i] = INT_MAX;
        }
        dist[0] = 0;
        memset(visit, 0, sizeof(visit));
        for (int k = 0; k < n; ++ k) {
            int i = 0;
            while (visit[i]) {
                i ++;
            }
            for (int j = 0; j < n; ++ j) {
                if (!visit[j] && dist[j] < dist[i]) {
                    i = j;
                }
            }
            if (dist[i] == INT_MAX) {
                break;
            }
            visit[i] = true;
            for (int j = 0; j < n; ++ j) {
                if (graph[i][j] && dist[i] + 1 < dist[j]) {
                    dist[j] = dist[i] + 1;
                }
            }
        }
        if (dist[n - 1] > limit) {
            puts("NO");
        } else {
            if (damage >= hp[n - 1]) {
                puts("YES");
            } else {
                limit -= dist[n - 1];
                int strike = 0;
                for (int i = 1; i < n - 1; ++ i) {
                    if (graph[i][n - 1]) {
                        strike += times[i];
                    }
                }
                if (dist[n - 1] > 1) {
                    strike --;
                }
                puts(min(strike, limit / 2) * damage + damage >= hp[n - 1]? "YES": "NO");
            }
        }
        puts("");
    }
    return 0;
}
