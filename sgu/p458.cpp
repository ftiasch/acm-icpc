// SGU 458 -- The Monochrome Picture
#include <cstdio>
#include <cstring>
#include <vector>
#include <functional>
#include <utility>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

typedef std::pair <int, int> Pair;

const int N = 100000;

int n, c[N], dp[N + 1], next[N];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", c + i);
    }
    dp[n] = 0;
    std::vector <Pair> choices;
    for (int i = n - 1; i >= 0; -- i) {
        dp[i] = 1;
        next[i] = n;
        foreach (iter, choices) {
            if (std::abs(c[iter->second] - c[i]) != 1) {
                if (iter->first + 1 > dp[i]) {
                    dp[i] = iter->first + 1;
                    next[i] = iter->second;
                }
            }
        }
        bool found = false;
        foreach (iter, choices) {
            if (c[iter->second] == c[i]) {
                found = true;
                iter->first = dp[i];
                iter->second = i;
            }
        }
        if (!found) {
            choices.push_back(std::make_pair(dp[i], i));
        }
        std::sort(choices.begin(), choices.end(), std::greater <Pair>());
        if ((int)choices.size() > 3) {
            choices.pop_back();
        }
    }
    int i = std::max_element(dp, dp + n) - dp;
    printf("%d\n", n - dp[i]);
    for (; i < n; i = next[i]) {
        printf("%d ", c[i]);
    }
    puts("");
    return 0;
}
