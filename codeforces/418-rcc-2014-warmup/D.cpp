#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <functional>
#include <utility>
#include <vector>

const int N = 100000;
const int D = 20;

int n;
std::vector<int> tree[N];

int depth[N], shortcut[N][D];

void prepare(int p, int u) {
    depth[u] = ~p ? depth[p] + 1 : 0;
    shortcut[u][0] = p;
    for (int i = 0; ~shortcut[u][i]; ++ i) {
        shortcut[u][i + 1] = shortcut[shortcut[u][i]][i];
    }
    for (int v : tree[u]) {
        if (v != p) {
            prepare(u, v);
        }
    }
}

int get_lca(int a, int b) {
    if (depth[a] > depth[b]) {
        std::swap(a, b);
    }
    for (int i = D - 1; i >= 0; -- i) {
        if (depth[b] - depth[a] >> i & 1) {
            b = shortcut[b][i];
        }
    }
    if (a == b) {
        return a;
    }
    for (int i = D - 1; i >= 0; -- i) {
        if (shortcut[a][i] != shortcut[b][i]) {
            a = shortcut[a][i];
            b = shortcut[b][i];
        }
    }
    return shortcut[a][0];
}

int get_distance(int a, int b) {
    return depth[a] + depth[b] - 2 * depth[get_lca(a, b)];
}

std::pair<int, int> diameters[2][N];

std::pair<int, int> preprocess(std::pair<int, int> *diameters, int p, int u) {
    std::vector<std::pair<int, int>> paths;
    paths.push_back(std::make_pair(0, u));
    paths.push_back(std::make_pair(0, u));
    std::pair<int, std::pair<int, int>> best = std::make_pair(-1, std::make_pair(0, 0));
    for (int v : tree[u]) {
        if (v != p) {
            paths.push_back(preprocess(diameters, u, v));
            best = std::max(best, std::make_pair(get_distance(diameters[v].first, diameters[v].second), diameters[v]));
        }
    }
    std::sort(paths.begin(), paths.end(), std::greater<std::pair<int, int>>());
    best = std::max(best, std::make_pair(paths[0].first + paths[1].first, std::make_pair(paths[0].second, paths[1].second)));
    diameters[u] = best.second;
    std::pair <int, int> returned = paths.front();
    returned.first ++;
    return returned;
}

void update(int &x, int u, int v, int a) {
    x = std::max(x, std::min(get_distance(u, a), get_distance(v, a)));
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
    memset(shortcut, -1, sizeof(shortcut));
    prepare(-1, 0);
    int a = 0;
    for (int i = 0; i < n; ++ i) {
        if (get_distance(0, i) > get_distance(0, a)) {
            a = i;
        }
    }
    int b = 0;
    for (int i = 0; i < n; ++ i) {
        if (get_distance(a, i) > get_distance(a, b)) {
            b = i;
        }
    }
    std::vector<std::pair<int, int>> path;
    for (int i = 0; i < n; ++ i) {
        if (get_distance(a, i) + get_distance(i, b) == get_distance(a, b)) {
            path.push_back(std::make_pair(get_distance(a, i), i));
        }
    }
    std::sort(path.begin(), path.end());
    preprocess(diameters[0], -1, a);
    preprocess(diameters[1], -1, b);
    int m;
    scanf("%d", &m);
    while (m --) {
        int u, v;
        scanf("%d%d", &u, &v);
        u --;
        v --;
        int low = 0;
        int high = (int)path.size() - 1;
        while (low < high) {
            int left = low + high >> 1;
            int right = left + 1;
#define VALUE(p) (std::max(get_distance(u, path[p].second), get_distance(v, path[p].second)))
            //assert(VALUE(left) != VALUE(right));
            if (VALUE(left) > VALUE(right)) {
                low = right;
            } else {
                high = left;
            }
#undef VALUE
        }
        assert(low == high);
        int p = path[low].second;
        int result = 0;
        update(result, u, v, a);
        update(result, u, v, b);
        update(result, u, v, diameters[1][p].first);
        update(result, u, v, diameters[1][p].second);
        if (low + 1 < (int)path.size()) {
            p = path[low + 1].second;
            update(result, u, v, diameters[0][p].first);
            update(result, u, v, diameters[0][p].second);
        }
        printf("%d\n", result);
    }
    return 0;
}
