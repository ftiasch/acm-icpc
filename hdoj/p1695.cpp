// 2008 “Sunline Cup” National Invitational Contest
// HDOJ 1695 -- GCD
#include <cstdio>
#include <cstring>
#include <vector>
#include <iostream>
#include <algorithm>

const int MAX = 100000;

int miu[MAX + 1], miu_sum[MAX + 1];

void find_events(std::vector <int> &events, int n) {
    events.push_back(0);
    for (int i = 1; i * i <= n; ++ i) {
        events.push_back(i);
        events.push_back(n / i);
    }
}

long long pair(int n, int m) {
    return (long long)n * m - (long long)n * (n - 1) / 2;
}

long long solve(int n, int m) {
    if (n > m) {
        std::swap(n, m);
    }
    std::vector <int> events;
    find_events(events, n);
    find_events(events, m);
    std::sort(events.begin(), events.end());
    events.erase(std::unique(events.begin(), events.end()), events.end());
    long long answer = 0;
    for (int i = 1; i < (int)events.size(); ++ i) {
        int d = events[i];
        answer += (long long)(miu_sum[events[i]] - miu_sum[events[i - 1]]) * pair(n / d, m / d);
    }
    return answer;
}

long long solve(int n, int m, int d) {
    return solve(n / d, m / d);
}

int main() {
    // prepare
    miu_sum[0] = 0;
    miu_sum[1] = miu[1] = 1;
    for (int i = 2; i <= MAX; ++ i) {
        int d = 2;
        while (d * d <= i && i % d != 0) {
            d ++;
        }
        if (d * d > i) {
            d = i;
        }
        if ((i / d) % d == 0) {
            miu[i] = 0;
        } else {
            miu[i] = -miu[i / d];
        }
        miu_sum[i] = miu_sum[i - 1] + miu[i];
    }
    int test_count;
    scanf("%d", &test_count);
    for (int t = 1; t <= test_count; ++ t) {
        int a, b, c, d, k;
        scanf("%d%d%d%d%d", &a, &b, &c, &d, &k);
        long long answer = 0;
        if (k) {
            answer += solve(b, d, k);
            answer -= solve(b, c - 1, k);
            answer -= solve(a - 1, d, k);
            answer += solve(a - 1, c - 1, k);
        }
        std::cout << "Case " << t << ": " << answer << std::endl;
    }
    return 0;
}
