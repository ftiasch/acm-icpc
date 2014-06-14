#include <algorithm>
#include <cstdio>
#include <climits>
#include <cstring>
#include <iostream>
#include <utility>
#include <vector>

const int N = 300000;

int n, m, a[N], b[N], count[N];
long long two_sum[N + 1];
std::pair <int, int> two_max[N + 1], two_min[N + 1];

int main() {
    scanf("%d%d", &n, &m);
    std::vector <std::pair <int, int>> ones, twos;
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", a + i, b + i);
        b[i] -= a[i];
        if (a[i] <= b[i]) {
            ones.push_back(std::make_pair(a[i], i));
            ones.push_back(std::make_pair(b[i], i));
        } else {
            twos.push_back(std::make_pair(a[i] + b[i], i));
        }
    }
    std::sort(ones.begin(), ones.end());
    std::sort(twos.begin(), twos.end());
    two_sum[0] = 0;
    two_max[0] = std::make_pair(INT_MIN, -1);
    for (int i = 0; i < (int)twos.size(); ++ i) {
        two_sum[i + 1] = two_sum[i] + twos[i].first;
        two_max[i + 1] = std::max(two_min[i + 1], std::make_pair(b[twos[i].second], twos[i].second));
    }
    two_min[(int)twos.size()] = std::make_pair(INT_MAX, -1);
    for (int i = (int)twos.size() - 1; i >= 0; -- i) {
        two_min[i] = std::min(two_min[i + 1], std::make_pair(a[twos[i].second], twos[i].second));
    }
    long long result = (long long)3e14 + 1;
    long long one_sum = 0;
    int best = -1;
    for (int i = 0; i <= (int)ones.size() && i <= m; ++ i) {
        if (m - i <= 2 * (int)twos.size()) {
            long long cost = one_sum + two_sum[m - i >> 1];
            if (m - i & 1) {
                int start = m - i >> 1;
                int extra = two_min[start].first;
                if (start) {
                    extra = std::min(extra, twos[start].first - two_max[start].first);
                }
                cost += extra;
            }
            if (cost < result) {
                best = i;
                result = cost;
            }
        }
        if (i < (int)ones.size()) {
            one_sum += ones[i].first;
        }
    }
    std::cout << result << std::endl;
    memset(count, 0, sizeof(count));
    long long sum = 0;
    for (int i = 0; i < best; ++ i) {
        sum += ones[i].first;
        count[ones[i].second] ++;
    }
    int start = m - best >> 1;
    for (int i = 0; i < start; ++ i) {
        sum += twos[i].first;
        count[twos[i].second] += 2;
    }
    if (m - best & 1) {
        if (start && sum + twos[start].first - two_max[start].first == result) {
            count[two_max[start].second] --;
            count[twos[start].second] += 2;
        } else {
            count[two_min[start].second] ++;
        }
    }
    for (int i = 0; i < n; ++ i) {
        printf("%d", count[i]);
    }
    puts("");
    return 0;
}
