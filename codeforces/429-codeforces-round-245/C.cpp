#include <algorithm>
#include <cstdio>
#include <cstring>
#include <functional>

const int N = 24;

int n, children[N], count[N];

bool go(int u, int start) {
    if (u < n) {
        for (int i = children[u] == children[u - 1] ? start : 0; i < u; ++ i) {
            if (children[i] > children[u]) {
                count[i] ++;
                children[i] -= children[u];
                if (go(u + 1, i)) {
                    return true;
                }
                count[i] --;
                children[i] += children[u];
            }
        }
        return false;
    } else {
        return *std::max_element(children, children + n) == 1 && std::find(count, count + n, 1) - count == n;
    }
}

bool check() {
    std::sort(children, children + n, std::greater <int>());
    if (*children != n) {
        return false;
    }
    memset(count, 0, sizeof(count));
    return go(1, 0);
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", children + i);
    }
    puts(check() ? "YES" : "NO");
    return 0;
}
