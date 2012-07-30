// POI X Stage III -- Sums(sum)
#include <cstdio>
#include <climits>
#include <cstring>
#include <queue>
using namespace std;

const int N = 5000;
const int M = 50000;

int n, a[N], d[M];
bool visit[M];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    int mod = a[0];
    for (int i = 0; i < mod; ++ i) {
        d[i] = INT_MAX;
    }
    memset(visit, 0, sizeof(visit));
    d[0] = 0;
    queue <int> q;
    q.push(0);
    while (!q.empty()) {
        int u = q.front();
        q.pop();
        visit[u] = false;
        for (int i = 0; i < n; ++ i) {
            int v = (u + a[i]) % mod;
            if (d[u] + a[i] < d[v]) {
                d[v] = d[u] + a[i];
                if (!visit[v]) {
                    visit[v] = true;
                    q.push(v);
                }
            }
        }
    }
    int m;
    scanf("%d", &m);
    while (m > 0) {
        m --;
        int b;
        scanf("%d", &b);
        puts(b >= d[b % mod]? "TAK": "NIE");
    }
    return 0;
}
