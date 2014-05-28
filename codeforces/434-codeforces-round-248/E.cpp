#include <algorithm>
#include <iostream>
#include <cstdio>
#include <cstring>
#include <map>
#include <vector>

const int N = 100000;

int n, p, b, r, a[N];
std::vector <int> edges[N];

bool resolved[N];
int size[N], imbalance[N];
std::vector <int> nodes;

void prepare(int p, int u) {
    nodes.push_back(u);
    size[u] = 1;
    imbalance[u] = 0;
    for (int v : edges[u]) {
        if (!resolved[v] && v != p) {
            prepare(u, v);
            size[u] += size[v];
            imbalance[u] = std::max(imbalance[u], size[v]);
        }
    }
}

int in[N], out[N];

int inverse(int a) {
    return a == 1 ? 1 : (long long)(p - p / a) * inverse(p % a) % p;
}

struct Path {
    Path(int v, int length, int prefix, int suffix) : v(v), length(length), prefix(prefix), suffix(suffix) {}

    int v, length, prefix, suffix;
};

int power[N + 1], inversed_power[N + 1];
std::vector <std::vector <Path>> pathss;

void search(int pp, int u, int length, int prefix, int suffix) {
    prefix = ((long long)prefix * b + a[u]) % p;
    suffix += (long long)power[length] * a[u] % p;
    if (suffix >= p) {
        suffix -= p;
    }
    pathss.back().push_back(Path(u, length, prefix, suffix));
    for (int v : edges[u]) {
        if (!resolved[v] && v != pp) {
            search(u, v, length + 1, prefix, suffix);
        }
    }
}

void divide(int root) {
    nodes.clear();
    prepare(-1, root);
    for (int v : nodes) {
        imbalance[v] = std::max(imbalance[v], size[root] - size[v]);
    }
    for (int v : nodes) {
        if (imbalance[v] < imbalance[root]) {
            root = v;
        }
    }
    resolved[root] = true;
    pathss.clear();
    for (int v : edges[root]) {
        if (!resolved[v]) {
            pathss.push_back(std::vector <Path>());
            search(root, v, 1, 0, a[root]);
        }
    }
    std::map <int, int> all_prefixes, all_suffixes;
    all_suffixes[a[root]] ++;
    all_prefixes[r] ++;
    for (std::vector <Path> &paths : pathss) {
        for (Path &path : paths) {
            in[root] += (path.prefix + (long long)power[path.length] * a[root] % p) % p == r;
            out[root] += path.suffix == r;
            all_suffixes[path.suffix] ++;
            all_prefixes[(long long)(r + p - path.prefix) * inversed_power[path.length] % p] ++;
        }
    }
    for (std::vector <Path> &paths : pathss) {
        for (Path &path : paths) {
            all_suffixes[path.suffix] --;
            all_prefixes[(long long)(r + p - path.prefix) * inversed_power[path.length] % p] --;
        }
        for (Path &path : paths) {
            out[path.v] += all_suffixes[(long long)(r + p - path.prefix) * inversed_power[path.length] % p];
            in[path.v] += all_prefixes[path.suffix];
        }
        for (Path &path : paths) {
            all_suffixes[path.suffix] ++;
            all_prefixes[(long long)(r + p - path.prefix) * inversed_power[path.length] % p] ++;
        }
    }
    for (int v : edges[root]) {
        if (!resolved[v]) {
            divide(v);
        }
    }
}

int main() {
    scanf("%d%d%d%d", &n, &p, &b, &r);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    power[0] = inversed_power[0] = 1;
    for (int i = 1; i <= n; ++ i) {
        power[i] = (long long)power[i - 1] * b % p;
        inversed_power[i] = inverse(power[i]);
    }
    for (int i = 0; i < n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        edges[a].push_back(b);
        edges[b].push_back(a);
    }
    memset(resolved, 0, sizeof(resolved));
    divide(0);
    long long result = 0;
    for (int i = 0; i < n; ++ i) {
        if (a[i] == r) {
            in[i] ++;
            out[i] ++;
        }
        result += (long long)in[i] * (n - out[i]);
        result += (long long)out[i] * (n - in[i]);
        result += 2LL * in[i] * (n - in[i]);
        result += 2LL * out[i] * (n - out[i]);
    }
    result >>= 1;
    std::cout << (long long)n * n * n - result << std::endl;
    return 0;
}
