#include <algorithm>
#include <cstdio>
#include <iostream>
#include <set>
#include <utility>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

const int N = 200000;

int a[N];
std::vector<int> indices[N + 1];

struct Set
{
    Set(int n) : area(0)
    {
        for (int i = -2; i <= n; ++ i) {
            set.emplace(i, n - i - 1);
        }
    }

    void update(int x, int y)
    {
        auto i = set.lower_bound({x, 0});
        auto j = i;
        j --;
        if (j->second > y) {
            area += (long long)(i->first - x) * j->second;
            while (i->second > y) {
                auto b = i;
                i ++;
                area += (long long)(i->first - b->first) * b->second;
                set.erase(b);
            }
            area -= (long long)(i->first - x) * y;
            if (x < i->first) {
                set.emplace(x, y);
            }
        }
    }

    long long area;
    std::set<std::pair<int, int>> set;
};

int main()
{
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
        indices[a[i]].push_back(i);
    }
    int m = *std::max_element(a, a + n);
    long long result = 0;
    Set set(n);
    for (int d = m; d >= 1; -- d) {
        std::vector<int> d_indices;
        for (int i = d; i <= m; i += d) {
            for (auto&& index : indices[i]) {
                d_indices.push_back(index);
            }
        }
        int s = d_indices.size();
        if (s >= 2) {
            std::sort(ALL(d_indices));
            set.update(-1, n - d_indices[(int)d_indices.size() - 2]);
            set.update(d_indices[0], n - d_indices[(int)d_indices.size() - 1]);
            set.update(d_indices[1], 0);
        }
        result += set.area;
    }
    std::cout << result << std::endl;
}
