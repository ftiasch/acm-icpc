// Codeforces Round #135
// Problem D -- Choosing Capital for Treeland
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 200000;

int n, edgeCount, firstEdge[N], to[N << 1], nextEdge[N << 1];
int counter, order[N];
int lead[N], down[N], all[N];

void addEdge(int u, int v) {
    to[edgeCount] = v;
    nextEdge[edgeCount] = firstEdge[u];
    firstEdge[u] = edgeCount ++;
}

void dfs(int p, int u) {
    order[counter ++] = u;
    for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
        if (to[iter] != p) {
            lead[to[iter]] = iter;
            dfs(u, to[iter]);
        }
    }
}

int main() {
    scanf("%d", &n);
    edgeCount = 0;
    memset(firstEdge, -1, sizeof(firstEdge));
    for (int i = 0; i < n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        addEdge(a, b);
        addEdge(b, a);
    }
    counter = 0;
    memset(lead, -1, sizeof(lead));
    dfs(-1, 0);
    for (int k = n - 1; k >= 0; -- k) {
        int u = order[k];
        int p = lead[u] == -1? -1: to[lead[u] ^ 1];
        down[u] = 0;
        for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
            int v = to[iter];
            if (v != p) {
                down[u] += down[v];
                if (lead[v] & 1) {
                    down[u] ++;
                }
            }
        }
    }
    for (int k = 0; k < n; ++ k) {
        int u = order[k];
        int p = lead[u] == -1? -1: to[lead[u] ^ 1];
        all[u] = p == -1? down[u]: all[p];
        if (p != -1) {
            if (lead[u] & 1) {
                all[u] --;
            } else {
                all[u] ++;
            }
        }
    }
    int answer = *min_element(all, all + n);
    printf("%d\n", answer);
    for (int i = 0; i < n; ++ i) {
        if (all[i] == answer) {
            printf("%d ", i + 1);
        }
    }
    puts("");
    return 0;
}
