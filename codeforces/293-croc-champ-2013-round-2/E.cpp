// Croc Champ 2013 - Round 2
// Problem E -- Close Vertices
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <utility>
#include <iostream>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 200000;

int n, l, w, edge_count, first_edge[N], to[N], weight[N], next_edge[N];

void add_edge(int u, int v, int w) {
    to[edge_count] = v;
    weight[edge_count] = w;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

int size[N], balance[N];
bool resolved[N];

std::vector <int> nodes;

void prepare(int p, int u) {
    nodes.push_back(u);
    size[u] = 1;
    balance[u] = 0;
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        int v = to[iter];
        if (!resolved[v] && v != p) {
            prepare(u, v);
            size[u] += size[v];
            balance[u] = std::max(balance[u], size[v]);
        }
    }
}

std::vector <std::pair <int, int> > paths, buffer;

void search(int p, int u, int depth, int total) {
    paths.push_back(std::make_pair(depth, total));
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        int v = to[iter];
        if (!resolved[v] && v != p) {
            search(u, v, depth + 1, total + weight[iter]);
        }
    }
}

long long answer;

int treap_count, key[N], priority[N], count[N], total[N], children[N][2];

void update(int &x) {
    total[x] = total[children[x][0]] + count[x] + total[children[x][1]];
}

void rotate(int &x, int t) {
    int y = children[x][t];
    children[x][t] = children[y][1 ^ t];
    children[y][1 ^ t] = x;
    update(x);
    update(y);
    x = y;
}

void insert(int &x, int k) {
    if (!x) {
        x = ++ treap_count;
        key[x] = k;
        priority[x] = rand();
        count[x] = 1;
        children[x][0] = children[x][1] = 0;
    } else if (key[x] == k) {
        count[x] ++;
    } else {
        int t = key[x] < k;
        insert(children[x][t], k);
        if (priority[children[x][t]] < priority[x]) {
            rotate(x, t);
        }
    }
    update(x);
}

int query(int &x, int k) {
    if (x) {
        if (key[x] > k) {
            return query(children[x][0], k);
        }
        return total[children[x][0]] + count[x] + query(children[x][1], k);
    }
    return 0;
}

long long process(std::vector <std::pair <int, int> > &paths) {
    long long ret = 0;
    int root = treap_count = 0;
    std::sort(paths.begin(), paths.end());
    for (int i = 0, j = (int)paths.size() - 1; j >= 0; -- j) {
        while (i < (int)paths.size() && paths[i].first + paths[j].first <= l) {
            insert(root, paths[i ++].second);
        }
        ret += query(root, w - paths[j].second);
    }
    for (int i = 0; i < (int)paths.size(); ++ i) {
        if (paths[i].first << 1 <= l && paths[i].second << 1 <= w) {
            ret --;
        }
    }
    return ret >> 1;
}

void divide(int root) {
    nodes.clear();
    prepare(-1, root);
    int new_root = root;
#define BALANCE(u) std::max(balance[u], size[root] - size[u])
    foreach (iter, nodes) {
        if (BALANCE(*iter) < BALANCE(new_root)) {
            new_root = *iter;
        }
    }
    resolved[new_root] = true;
    buffer.clear();
    buffer.push_back(std::make_pair(0, 0));
    for (int iter = first_edge[new_root]; iter != -1; iter = next_edge[iter]) {
        int v = to[iter];
        if (!resolved[v]) {
            paths.clear();
            search(new_root, v, 1, weight[iter]);
            answer -= process(paths);
            foreach (iter, paths) {
                buffer.push_back(*iter);
            }
        }
    }
    answer += process(buffer);
    for (int iter = first_edge[new_root]; iter != -1; iter = next_edge[iter]) {
        int v = to[iter];
        if (!resolved[v]) {
            divide(v);
        }
    }
}

int main() {
    priority[0] = INT_MAX;
    total[0] = 0;
    scanf("%d%d%d", &n, &l, &w);
    edge_count = 0;
    memset(first_edge, -1, sizeof(first_edge));
    for (int v = 1; v < n; ++ v) {
        int u, w;
        scanf("%d%d", &u, &w);
        u --;
        add_edge(u, v, w);
        add_edge(v, u, w);
    }
    answer = 0;
    memset(resolved, 0, sizeof(resolved));
    divide(0);
    std::cout << answer << std::endl;
    return 0;
}
