// Codeforces Round #119
// Problem C -- Weak Memory
#include <cstdio>
#include <cstring>
#include <vector>
using namespace std;

const int N = 222222;

int n, m, k, source, target, queue[N], dist[N];
bool has[N], visit[N];
vector <int> adjacent[N];

bool check(int limit) {
    for (int i = 1; i <= n; ++ i) {
        dist[i] = -1;
    }
    dist[source] = limit;
    memset(visit, 0, sizeof(visit));
    int head = 0;
    int tail = 0;
    queue[tail ++] = source;
    while (head != tail) {
        int u = queue[head];
        head = (head + 1) % N;
        visit[u] = false;
        if (has[u]) {
            dist[u] = limit;
        }
        if (dist[u] > 0) {
            for (vector <int> :: iterator iter = adjacent[u].begin(); \
                    iter != adjacent[u].end(); ++ iter) { 
                int v = *iter;
                if (dist[u] - 1 > dist[v]) {
                    dist[v] = dist[u] - 1;
                    if (!visit[v]) {
                        visit[v] = true;
                        queue[tail] = v;
                        tail = (tail + 1) % N;
                    }
                }
            }
        }
    }
    return dist[target] >= 0;
}

int main() {
    scanf("%d%d%d", &n, &m, &k);
    memset(has, 0, sizeof(has));
    for (int i = 0; i < k; ++ i) {
        int x;
        scanf("%d", &x);
        has[x] = true;
    }
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        adjacent[a].push_back(b);
        adjacent[b].push_back(a);
    }
    scanf("%d%d", &source, &target);
    int lower = 1;
    int upper = n;
    while (lower < upper) {
        int mider = (lower + upper) >> 1;
        if (check(mider)) {
            upper = mider;
        } else {
            lower = mider + 1;
        }
    }
    if (check(upper)) {
        printf("%d\n", upper);
    } else {
        puts("-1");
    }
    return 0;
}
