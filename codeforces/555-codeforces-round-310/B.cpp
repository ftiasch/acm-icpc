#include <algorithm>
#include <iostream>
#include <cstdio>
#include <queue>
#include <vector>

typedef long long Long;

const int N = 200000;

Long l[N], r[N], a[N], low[N], high[N];
int result[N];

int main()
{
    std::ios::sync_with_stdio(false);
    int n, m;
    std::cin >> n >> m;
    for (int i = 0; i < n; ++ i) {
        std::cin >> l[i] >> r[i];
    }
    for (int i = 0; i < m; ++ i) {
        std::cin >> a[i];
    }
    n --;
    std::vector <std::pair <Long, int>> events;
    for (int i = 0; i < n; ++ i) {
        low[i] = l[i + 1] - r[i];
        high[i] = r[i + 1] - l[i];
        events.push_back({low[i], i});
    }
    for (int i = 0; i < m; ++ i) {
        events.push_back({a[i], n + i});
    }
    std::sort(events.begin(), events.end());
    std::priority_queue <std::pair <Long, int>> queue;
    for (const auto &e : events) {
        int i = e.second;
        if (i < n) {
            queue.push({-high[i], i});
        } else if (!queue.empty()) {
            i -= n;
            const auto &t = queue.top();
            if (-t.first < a[i]) {
                puts("No");
                return 0;
            }
            result[t.second] = i;
            queue.pop();
        }
    }
    if (!queue.empty()) {
        puts("No");
        return 0;
    }
    puts("Yes");
    for (int i = 0; i < n; ++ i) {
        printf("%d%c", result[i] + 1, " \n"[i == n - 1]);
    }
    return 0;
}
