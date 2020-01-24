#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <set>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

const int N = 50000;

int s[6 * N + 1], x[N][2], y[N][2], z[N][2], cover[N << 2], total[N << 2];

int get_id(int l, int r)
{
    return l + r | l != r;
}

void add(const std::vector<int>& values, int l, int r, int a, int b, int d)
{
    if (b <= values[l] || values[r + 1] <= a) {
        return;
    }
    auto id = get_id(l, r);
    auto m = l + r >> 1;
    if (a <= values[l] && values[r + 1] <= b) {
        cover[id] += d;
    } else {
        add(values, l, m, a, b, d);
        add(values, m + 1, r, a, b, d);
    }
    total[id] = 0;
    if (cover[id]) {
        total[id] = values[r + 1] - values[l];
    } else if (l < r) {
        total[id] = total[get_id(l, m)] + total[get_id(m + 1, r)];
    }
}

long long solve(int n)
{
    for (auto k = 1; k <= 6 * n; ++ k) {
        s[k] = (k <= 55 ? 100003 - 200003LL * k + 300007LL * k * k * k : s[k - 24] + s[k - 55]) % 1000000;
    }
    for (auto i = 0; i < n; ++ i) {
        x[i][0] = s[6 * i + 1] % 10000;
        y[i][0] = s[6 * i + 2] % 10000;
        z[i][0] = s[6 * i + 3] % 10000;
        x[i][1] = x[i][0] + 1 + s[6 * i + 4] % 399;
        y[i][1] = y[i][0] + 1 + s[6 * i + 5] % 399;
        z[i][1] = z[i][0] + 1 + s[6 * i + 6] % 399;
        // printf("(%d, %d, %d) -- (%d, %d, %d)\n", x[i][0], y[i][0], z[i][0], x[i][1], y[i][1], z[i][1]);
    }
    std::vector<std::pair<int, int>> events;
    for (auto i = 0; i < n; ++ i) {
        events.emplace_back(z[i][0], i << 1);
        events.emplace_back(z[i][1], i << 1 | 1);
    }
    std::sort(ALL(events));
    std::set<int> rectangles;
    auto result = 0LL;
    for (auto z = 0, e = 0; e < static_cast<int>(events.size()); ++ z) {
        while (e < static_cast<int>(events.size()) && events[e].first == z) {
            auto&& ref = events[e].second;
            if (ref & 1) {
                rectangles.erase(ref >> 1);
            } else {
                rectangles.insert(ref >> 1);
            }
            e ++;
        }
        if (rectangles.empty()) {
            continue;
        }
        std::vector<int> values;
        for (auto&& i : rectangles) {
            values.push_back(y[i][0]);
            values.push_back(y[i][1]);
        }
        std::sort(ALL(values));
        values.erase(std::unique(ALL(values)), values.end());
        auto m = static_cast<int>(values.size()) - 1;
        std::vector<std::pair<int, int>> events;
        for (auto&& i : rectangles) {
            events.emplace_back(x[i][0], i << 1);
            events.emplace_back(x[i][1], i << 1 | 1);
        }
        std::sort(ALL(events));
        memset(cover, 0, sizeof(*cover) * (m << 1));
        memset(total, 0, sizeof(*total) * (m << 1));
        for (auto i = 0; i < static_cast<int>(events.size()); ++ i) {
            auto&& e = events[i];
            auto id = e.second >> 1;
            if (i) {
                result += 1LL * total[get_id(0, m - 1)] * (events[i].first - events[i - 1].first);
            }
            add(values, 0, m - 1, y[id][0], y[id][1], e.second & 1 ? -1 : 1);
        }
    }
    return result;
}

int main()
{
    assert(solve(100) == 723581599);
    printf("%lld\n", solve(50000));
}
