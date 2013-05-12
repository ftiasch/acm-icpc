// Codeforces Round #178
// Problem E -- Shaass the Great
#include <cstdio>
#include <cstring>
#include <queue>
#include <utility>
#include <iostream>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i) 

const int N = 5000;

int n, a[N], b[N], w[N];
std::vector <std::pair <int, int> > graph[N];

struct Data {
    int size;
    long long price, cost;
    
    Data(int size, long long price, long long cost) : size(size), price(price), cost(cost) {}
};

std::ostream &operator <<(std::ostream &out, const Data &p) {
    return out << p.size << " " << p.price << " " << p.cost;
}

int parent[N], size[N];
long long depth[N];
std::vector <int> queue;

void dfs(int p, int u) {
    size[u] = 1;
    parent[u] = p;
    queue.push_back(u);
    foreach (iter, graph[u]) {
        int v = iter->first;
        if (v != p) {
            dfs(u, v);
            size[u] += size[v];
        }
    }
}

Data solve(int root) {
    queue.clear();
    dfs(-1, root);
    int new_root = -1;
    foreach (iteru, queue) {
        int u = *iteru;
        int maximum = size[root] - size[u];
        foreach (iter, graph[u]) {
            int v = iter->first;
            if (v != parent[u]) {
                maximum = std::max(maximum, size[v]);
            }
        }
        if (maximum * 2 <= size[root]) {
            new_root = u;
            break;
        }
    }
    queue.clear();
    depth[new_root] = 0;
    dfs(-1, new_root);
    long long cost = 0;
    long long price = 0;
    foreach (iteru, queue) {
        int u = *iteru;
        price += depth[u];
        foreach (iter, graph[u]) {
            int v = iter->first;
            if (v != parent[u]) {
                depth[v] = depth[u] + iter->second;
                cost += (long long)iter->second * size[v] * (size[new_root] - size[v]);
            }
        }
    }
    return Data(size[new_root], price, cost);
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n - 1; ++ i) {
        scanf("%d%d%d", a + i, b + i, w + i);
        a[i] --;
        b[i] --;
    }
    long long answer = 1000000000000000000LL;
    for (int i = 0; i < n - 1; ++ i) {
        for (int u = 0; u < n; ++ u) {
            graph[u].clear();
        }
        for (int j = 0; j < n - 1; ++ j) {
            if (i != j) {
#define ADD_EDGE(u, v, w) graph[u].push_back(std::make_pair(v, w))
                ADD_EDGE(a[j], b[j], w[j]);
                ADD_EDGE(b[j], a[j], w[j]);
            }
        }
        Data u = solve(a[i]);
        Data v = solve(b[i]);
        answer = std::min(answer, u.cost + v.cost + u.price * v.size + v.price * u.size + (long long)w[i] * u.size * v.size);
    }
    std::cout << answer << std::endl;
    return 0;
}
