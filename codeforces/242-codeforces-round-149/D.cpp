// Codeforces Round #149
// Problem D -- Dispute
#include <cstdio>
#include <cstring>
#include <vector>
#include <queue>
using namespace std;

const int N = 100000;

int n, m, a[N], counter[N], solution[N];
vector <int> adjacent[N];

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        adjacent[i].push_back(i);
    }
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --, b --;
        adjacent[a].push_back(b);
        adjacent[b].push_back(a);
    }
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    queue <int> events;
    for (int i = 0; i < n; ++ i) {
        if (counter[i] == a[i]) {
            events.push(i);
        }
    }
    while (!events.empty()) {
        int u = events.front();
        events.pop();
        if (counter[u] == a[u]) {
            solution[u] ^= 1;
            foreach (iter, adjacent[u]) {
                int v = *iter;
                counter[v] += solution[u] ? 1 : -1;
                if (counter[v] == a[v]) {
                    events.push(v);
                }
            }
        }
    }
    int size = 0;
    for (int i = 0; i < n; ++ i) {
        size += solution[i];
    }
    printf("%d\n", size);
    for (int i = 0; i < n; ++ i) {
        if (solution[i]) {
            printf("%d ", i + 1);
        }
    }
    puts("");
    return 0;
}
