#include <algorithm>
#include <cstdio>
#include <tuple>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

typedef std::tuple<int, int, int, int> Query;

int main()
{
    int n;
    scanf("%d", &n);
    std::vector<Query> queries;
    for (int i = 0; i < n; ++ i) {
        int a, t, x;
        scanf("%d%d%d", &a, &t, &x);
        queries.emplace_back(x, i, t, a);
    }
    std::sort(ALL(queries));
    std::vector<int> result(n, -1);
    auto solve = [&](Query* queries, int n) {
        std::vector<int> values;
        for (int i = 0; i < n; ++ i) {
            values.push_back(std::get<2>(queries[i]));
        }
        std::sort(ALL(values));
        std::vector<int> count(n);
        auto add = [&](int k, int v) {
            for (; k < n; k += ~k & k + 1) {
                count[k] += v;
            }
        };
        for (int i = 0; i < n; ++ i) {
            int index = std::lower_bound(ALL(values), std::get<2>(queries[i])) - values.begin();
            int type = std::get<3>(queries[i]);
            if (type == 1) {
                add(index, 1);
            } else if (type == 2) {
                add(index, -1);
            } else {
                int& res = result[std::get<1>(queries[i])];
                res = 0;
                for (; ~index; index -= ~index & index + 1) {
                    res += count[index];
                }
            }
        }
    };
    for (int l = 0; l < n; ) {
        int r = l;
        while (r < n && std::get<0>(queries[l]) == std::get<0>(queries[r])) {
            r ++;
        }
        solve(queries.data() + l, r - l);
        l = r;
    }
    for (int i = 0; i < n; ++ i) {
        if (~result[i]) {
            printf("%d\n", result[i]);
        }
    }
}
