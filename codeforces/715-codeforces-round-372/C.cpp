#include <cstdio>
#include <cstring>
#include <iostream>
#include <map>
#include <vector>
#include <utility>

using Long = long long;

const int N = 100000;

std::pair<int, int> gcd(int a, int b)
{
    if (!b) {
        return {1, 0};
    }
    int x, y;
    std::tie(y, x) = gcd(b, a % b);
    return {x, y - a / b * x};
}

int n, mod, power[N + N];
Long inv10;
std::vector<std::pair<int, int>> tree[N];

using Map = std::map<int, int>;

struct M
{
    M() : l(0), d(0), m(new Map()) {}

    int count(int k) const
    {
        auto iterator = m->find(fix(k));
        return iterator == m->end() ? 0 : iterator->second;
    }

    int eval(const std::pair<int, int>& kv) const
    {
        return (1LL * kv.first * power[n + l] + d) % mod;
    }

    int fix(int k) const
    {
        return 1LL * (k + mod - d) * power[n - l] % mod;
    }

    int size() const
    {
        return m->size();
    }

    void insert(int k, int v)
    {
        (*m)[fix(k)] += v;
    }

    int l, d;
    Map* m;
};

Long intersect(const M& a, const M& b)
{
    Long result = 0;
    for (auto&& kv : *a.m) {
        result += 1LL * kv.second * b.count(a.eval(kv));
    }
    return result;
}

void merge(const M& a, M& b)
{
    for (auto&& kv : *a.m) {
        b.insert(a.eval(kv), kv.second);
    }
}

std::pair<Long, std::pair<M, M>> solve(int p, int u)
{
    Long result = 0;
    M down, up;
    down.insert(0, 1);
    up.insert(0, 1);
    for (auto&& iterator : tree[u]) {
        auto&& v = iterator.first;
        auto&& d = iterator.second;
        if (p == v) {
            continue;
        }
        auto subtree = solve(u, v);
        result += subtree.first;
        auto sdown = subtree.second.first;
        sdown.l --;
        sdown.d = (sdown.d + d) * inv10 % mod;
        auto sup = subtree.second.second;
        sup.l ++;
        sup.d = (sup.d * 10LL + mod - d) % mod;
        if (down.size() < sdown.size()) {
            std::swap(down, sdown);
            std::swap(up, sup);
        }
        result += intersect(sdown, up) + intersect(sup, down);
        merge(sdown, down);
        merge(sup, up);
    }
    return {result, {down, up}};
}

int main()
{
#ifdef LOCAL_JUDGE
    freopen("C.in", "r", stdin);
#endif
    scanf("%d%d", &n, &mod);
    inv10 = (gcd(10, mod).first % mod + mod) % mod;
    power[n] = 1;
    for (int i = 1; i < n; ++ i) {
        power[n + i] = power[n + i - 1] * 10LL % mod;
        power[n - i] = power[n - i + 1] * inv10 % mod;
    }
    for (int i = 0; i < n - 1; ++ i) {
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        tree[a].emplace_back(b, c);
        tree[b].emplace_back(a, c);
    }
    std::cout << solve(-1, 0).first << std::endl;
}
