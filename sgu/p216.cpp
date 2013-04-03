// SGU 216 -- Royal Federation
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 10000;

int n, b;
std::vector <int> adjacent[N];

int parent[N], size[N], belongs[N], capitals[N];

int find(int u) {
    if (belongs[u] != u) {
        belongs[u] = find(belongs[u]);
    }
    return belongs[u];
}

int merge(int u, int v) { // merge v to u
    if (u == -1) {
        return v;
    }
    size[u] += size[v];
    return belongs[v] = u;
}

int solve(int p, int u) {
    parent[u] = p;
    int last = -1;
    foreach (iter, adjacent[u]) {
        int v = *iter;
        if (p == v) {
            continue;
        }
        int ret = solve(u, v);
        last = merge(last, ret);
        if (last != -1 && size[last] >= b) {
            capitals[last] = u;
            last = -1;
        }
    }
    last = merge(last, u);
    if (size[last] >= b) {
        capitals[last] = u;
        last = -1;
    }
    return last;
}

int main() {
    scanf("%d%d", &n, &b);
    for (int i = 0; i < n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        adjacent[a].push_back(b);
        adjacent[b].push_back(a);
    }
    if (n < b) {
        puts("0");
    } else {
        for (int i = 0; i < n; ++ i) {
            size[i] = 1;
            belongs[i] = i;
        }
        int ret = solve(-1, 0);
        if (ret != -1) {
            for (int i = 1; i < n; ++ i) {
                if (find(i) != ret && find(parent[i]) == ret) {
                    ret = merge(ret, find(i));
                    capitals[ret] = 0;
                    break;
                }
            }
        }
        std::vector <int> values;
        for (int i = 0; i < n; ++ i) {
            values.push_back(find(i));
        }
        std::sort(values.begin(), values.end());
        values.erase(std::unique(values.begin(), values.end()), values.end());
        printf("%d\n", (int)values.size());
        for (int i = 0; i < n; ++ i) {
            int id = std::lower_bound(values.begin(), values.end(), find(i)) - values.begin();
            printf("%d%c", id + 1, i == n - 1 ? '\n' : ' ');
        }
        for (int i = 0; i < (int)values.size(); ++ i) {
            printf("%d%c", capitals[values[i]] + 1, i == (int)values.size() - 1 ? '\n' : ' ');
        }
    }
    return 0;
}
