#include <cassert>
#include <cstdio>
#include <cstring>
#include <set>

const int N = (int)2e5;

int n, x[N], y[N], color[N];
std::set <int> edges[N << 1];

void go(int u)
{
    int c = 0;
    while (edges[u].size() & 1) {
        int id = *edges[u].begin();
        edges[u].erase(id);
        color[id] = c;
        c ^= 1;
        int v = u < N ? N + y[id] : x[id];
        edges[v].erase(id);
        u = v;
    }
}

int main()
{
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", x + i, y + i);
        x[i] --;
        y[i] --;
        edges[x[i]].insert(i);
        edges[N + y[i]].insert(i);
    }
    memset(color, -1, sizeof(*color) * n);
    for (int i = 0; i < N + N; ++ i) {
        if (edges[i].size() & 1) {
            go(i);
        }
    }
    for (int i = 0; i < n; ++ i) {
        if (color[i] == -1) {
            color[i] = 1;
            edges[x[i]].erase(i);
            edges[N + y[i]].erase(i);
            go(x[i]);
            assert(~edges[N + y[i]].size() & 1);
        }
    }
    for (int i = 0; i < n; ++ i) {
        putchar("rb"[color[i]]);
    }
    puts("");
    return 0;
}
