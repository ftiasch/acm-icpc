#include <algorithm>
#include <cstdio>
#include <numeric>
#include <map>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

const int MOD = (int)1e9 + 7;

void update(int &x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

typedef std::vector<int> State;

template<typename K, typename V>
using Map = std::map<K, V>;

int find(State& s, int x)
{
    if (s[x] != x) {
        s[x] = find(s, s[x]);
    }
    return s[x];
}

bool merge(State& s, int x, int y)
{
    x = find(s, x);
    y = find(s, y);
    if (x == y) {
        return false;
    }
    s[x] = y;
    return true;
}

State normalize(State state)
{
    int n = state.size();
    for (auto& x : state) {
        x = find(state, x);
    }
    std::vector<int> label(n, -1);
    for (int i = 0; i < n; ++ i) {
        auto& x = state[i];
        if (!~label[x]) {
            label[x] = i;
        }
        x = label[x];
    }
    return state;
}

int main()
{
    int n, k;
    scanf("%d%d", &n, &k);
    if (k == 1) {
        printf("%d\n", 1);
        return 0;
    }
    std::vector<std::vector<int>> neighbours(n), children(n);
    for (int i = 1; i < n; ++ i) {
        if (i < k) {
            for (int j = 0; j < i; ++ j) {
                neighbours[i].push_back(j);
            }
        } else {
            neighbours[i].resize(k);
            for (int j = 0; j < k; ++ j) {
                scanf("%d", &neighbours[i][j]);
                neighbours[i][j] --;
            }
            std::sort(ALL(neighbours[i]));
        }
        int p = neighbours[i].back();
        children[p].push_back(i);
    }
    std::vector<Map<State, int>> ways(n);
    for (int i = n - 1; i >= 1; -- i) {
        int d = neighbours[i].size();
        Map<State, int> tmp_ways;
        for (int mask = 0; mask < 1 << d; ++ mask) {
            State s(d + 1);
            std::iota(ALL(s), 0);
            for (int i = 0; i < d; ++ i) {
                if (mask >> i & 1) {
                    s[i] = d;
                }
            }
            tmp_ways[normalize(s)] = 1;
        }
        for (int v : children[i]) {
            int d2 = neighbours[v].size();
            std::vector<int> mapping;
            for (auto&& x : neighbours[v]) {
                mapping.push_back(std::find(ALL(neighbours[i]), x) - neighbours[i].begin());
            }
            Map<State, int> new_ways;
            for (auto&& iterator : tmp_ways) {
                for (auto&& iterator2 : ways[v]) {
                    auto state = iterator.first;
                    auto&& state2 = iterator2.first;
                    bool valid = true;
                    for (int i = 0; i < d2 && valid; ++ i) {
                        if (state2[i] != i) {
                            valid &= merge(state, mapping[i], mapping[state2[i]]);
                        }
                    }
                    if (valid) {
                        update(new_ways[normalize(state)], (long long)iterator.second * iterator2.second % MOD);
                    }
                }
            }
            tmp_ways.swap(new_ways);
        }
        for (auto&& iterator : tmp_ways) {
            auto state = iterator.first;
            int tmp = state.back();
            state.pop_back();
            if (std::find(ALL(state), tmp) != state.end()) {
                update(ways[i][normalize(state)], iterator.second);
            }
        }
    }
    printf("%d\n", ways[1].begin()->second);
}
