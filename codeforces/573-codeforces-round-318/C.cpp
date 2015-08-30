#include <cstdio>
#include <cstring>
#include <vector>

const int N = 100000;

int n;
bool used[N];
std::vector<int> tree[N];

void cut(int p, int u) {
    if (!used[u] && (int)tree[u].size() <= 2) {
        used[u] = true;
        for (int v : tree[u]) {
            cut(u, v);
        }
    }
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        tree[a].push_back(b);
        tree[b].push_back(a);
    }
    memset(used, 0, sizeof(*used) * n);
    for (int i = 0; i < n; ++ i) {
        if ((int)tree[i].size() == 1) {
            cut(-1, i);
        }
    }
    for (int i = 0; i < n; ++ i) {
        if ((int)tree[i].size() == 3) {
            int deg = 0;
            for (int v : tree[i]) {
                deg += !used[v] || (int)tree[v].size() >= 3;
            }
            used[i] = deg <= 1;
        }
    }
    bool valid = true;
    for (int i = 0; i < n; ++ i) {
        if (!used[i]) {
            int deg = 0;
            for (int v : tree[i]) {
                deg += !used[v];
            }
            valid &= deg <= 2;
        }
    }
    puts(valid ? "Yes" : "No");
    return 0;
}
