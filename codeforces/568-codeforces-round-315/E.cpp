#include <algorithm>
#include <cstdio>
#include <climits>
#include <cstring>
#include <utility>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

const int N = 100000;

int n, m, a[N], b[N], pre[N];
bool used[N];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    scanf("%d", &m);
    for (int i = 0; i < m; ++ i) {
        scanf("%d", b + i);
    }
    std::sort(b, b + m);
    std::vector<std::pair<int, int>> low(n + 1, std::make_pair(INT_MAX, -1));
    low[0] = std::make_pair(INT_MIN, -1);
    for (int i = 0; i < n; ++ i) {
        if (~a[i]) {
            int l = std::lower_bound(ALL(low), std::make_pair(a[i], INT_MIN)) - low.begin();
            pre[i] = low[l - 1].second;
            low[l] = std::min(low[l], std::make_pair(a[i], i));
        } else {
            for (int j = m - 1, k = n; j >= 0; -- j) {
                while (low[k].first >= b[j]) {
                    k --;
                }
                low[k + 1] = std::min(low[k + 1], std::make_pair(b[j], low[k].second));
            }
        }
    }
    int ml = n;
    while (low[ml].first == INT_MAX) {
        ml --;
    }
    std::vector<int> seq;
    for (int i = low[ml].second; ~i; i = pre[i]) {
        seq.push_back(i);
    }
    std::sort(ALL(seq));
    std::vector<int> val;
    for (int i = 0; i < m; ++ i) {
        if (!i || b[val.back()] < b[i]) {
            val.push_back(i);
        }
    }
    memset(used, 0, sizeof(used));
    for (int i = 0, j = 0, k = 0; i < n; ++ i) {
        if (a[i] == -1) {
            bool valid = j < (int)val.size();
            if (k < (int)seq.size()) {
                valid &= b[val[j]] < a[seq[k]];
            }
            if (valid) {
                used[val[j]] = true;
                a[i] = b[val[j ++]];
            }
        }
        if (k < seq.size() && i == seq[k]) {
            while (j < (int)val.size() && b[val[j]] <= a[i]) {
                j ++;
            }
            k ++;
        }
    }
    for (int i = 0, j = 0; i < n; ++ i) {
        if (a[i] == -1) {
            while (used[j]) {
                j ++;
            }
            a[i] = b[j ++];
        }
        printf("%d%c", a[i], " \n"[i == n - 1]);
    }
    return 0;
}
