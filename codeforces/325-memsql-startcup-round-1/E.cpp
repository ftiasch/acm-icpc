#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

const int N = 100000;

int n, head[N], to[N], next[N];
std::vector <int> result;

void dfs(int u) {
    while (~head[u]) {
        int edge = head[u];
        int v = to[edge];
        head[u] = next[head[u]];
        dfs(v);
        result.push_back(edge);
    }
}

int main() {
    scanf("%d", &n);
    if (n & 1) {
        puts("-1");
    } else {
        memset(head, -1, sizeof(head));
        for (int i = 0; i < n; ++ i) {
            to[i] = i % (n / 2);
            next[i] = head[i / 2];
            head[i / 2] = i;
        }
        dfs(0);
        std::reverse(result.begin(), result.end());
        printf("0 ");
        for (int i = 0; i < n; ++ i) {
            printf("%d%c", result[i], " \n"[i == n - 1]);
        }
    }
    return 0;
}
