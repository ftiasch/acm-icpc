#include <algorithm>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <functional>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

const int N = 100000;

int n, m, x[N], y[N];

int main()
{
    scanf("%d%d", &n, &m);
    std::vector<int> values;
    std::vector<std::pair<int, int>> events;
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", x + i, y + i);
        values.push_back(y[i]);
        values.push_back(y[i] + m);
        events.emplace_back(x[i], i << 1);
        events.emplace_back(x[i] + m, i << 1 | 1);
    }
    std::sort(ALL(values));
    values.erase(std::unique(ALL(values)), values.end());
    std::sort(ALL(events));
    std::vector<long long> result(n + 1);
    int l = values.size();
    std::vector<int> from(l + 1);
    std::vector<int> count(l + 1);
    for (auto&& event : events) {
        int index = event.second >> 1;
        int delta = event.second & 1 ? -1 : +1;
        for (int i = std::lower_bound(ALL(values), y[index]) - values.begin(); values[i] < y[index] + m; ++ i) {
            if (count[i]) {
                result[count[i]] += (long long)(values[i + 1] - values[i]) * (event.first - from[i]);
            }
            count[i] += delta;
            from[i] = event.first;
        }
    }
    for (int i = 1; i <= n; ++ i) {
        std::cout << result[i] << " \n"[i == n];
    }
    std::cout << std::flush;
}
