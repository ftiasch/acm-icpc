// Croc Champ 2012 - Round 2
// Problem E -- Archaeology
#include <cstdio>
#include <cstring>
#include <set>
#include <iostream>
#include <algorithm>

const int N = 100000;
const int D = 20;

int n, edge_count, first_edge[N], to[N << 1], length[N << 1], next_edge[N << 1], depth[N], jump[N][D], node_count, order[N], position[N];
long long path[N];

void add_edge(int u, int v, int w) {
    to[edge_count] = v;
    length[edge_count] = w;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

void prepare(int p, int u) {
    depth[u] = p == -1 ? 0 : depth[p] + 1;
    jump[u][0] = p;
    for (int i = 0; jump[u][i] != -1; ++ i) {
        jump[u][i + 1] = jump[jump[u][i]][i];
    }
    order[position[u] = node_count ++] = u;
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        int v = to[iter];
        if (v != p) {
            path[v] = path[u] + length[iter];
            prepare(u, v);
        }
    }
}

int goup(int u, int d) {
    for (int i = 0; i < D; ++ i) {
        if (d >> i & 1) {
            u = jump[u][i];
        }
    }
    return u;
}

int lca(int u, int v) {
    if (depth[u] > depth[v]) {
        return lca(v, u);
    }
    v = goup(v, depth[v] - depth[u]);
    if (u == v) {
        return u;
    }
    for (int i = D - 1; i >= 0; -- i) {
        if (jump[u][i] != jump[v][i]) {
            u = jump[u][i];
            v = jump[v][i];
        }
    }
    return jump[u][0];
}

long long dist(int u, int v) {
    return path[u] + path[v] - 2 * path[lca(u, v)];
}

typedef std::set <int> :: iterator Iterator;

std::set <int> nodes;

int pred(Iterator iter) {
    if (iter == nodes.begin()) {
        return -1;
    }
    iter --;
    return order[*iter];
}

int succ(Iterator iter) {
    iter ++;
    if (iter == nodes.end()) {
        return -1;
    }
    return order[*iter];
}

int main() {
    scanf("%d", &n);
    edge_count = 0;
    memset(first_edge, -1, sizeof(first_edge));
    for (int i = 0; i < n - 1; ++ i) {
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        a --, b --;
        add_edge(a, b, c);
        add_edge(b, a, c);
    }
    node_count = 0;
    path[0] = 0;
    memset(jump, -1, sizeof(jump));
    prepare(-1, 0);
    int q;
    scanf("%d", &q);
    long long now = 0;
    while (q --) {
        char buffer[2];
        scanf("%s", buffer);
        int x;
        if (*buffer != '?') {
            scanf("%d", &x);
            x --;
            int signum = *buffer == '+' ? 1 : -1;
            if (signum == 1) {
                nodes.insert(position[x]);
            }
            Iterator iter = nodes.find(position[x]);
            int y = pred(iter);
            int z = succ(iter);
            if (y != -1) {
                now += dist(x, y) * signum;
            }
            if (z != -1) {
                now += dist(x, z) * signum;
            }
            if (y != -1 && z != -1) {
                now -= dist(y, z) * signum;
            }
            if (signum == -1) {
                nodes.erase(position[x]);
            }
        } else {
            if (nodes.empty()) {
                std::cout << 0 << std::endl;
            } else {
                std::cout << ((now + dist(order[*nodes.begin()], order[*nodes.rbegin()])) / 2) << std::endl;
            }
        }
    }
    return 0;
}
