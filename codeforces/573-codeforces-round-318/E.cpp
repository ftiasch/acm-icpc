#include <algorithm>
#include <cstdio>
#include <cstring>
#include <deque>
#include <iostream>
#include <utility>
#include <vector>

typedef long long Long;

const int N = 100000;

int  a[N];
Long b[N];
bool used[N];

bool by_a(int i, int j) {
    return a[i] < a[j];
}

bool check(int i, int j, int k) {
    return (long long)(b[i] - b[j]) * (a[k] - a[j]) < (long long)(b[j] - b[k]) * (a[j] - a[i]);
}

void build(const std::vector<int> &order, std::deque<int> &block) {
    block.clear();
    for (int i : order) {
        if (!used[i]) {
            if (!block.empty()) {
                int j = block.back();
                if (a[j] == a[i] && b[j] >= b[i]) {
                    continue;
                }
            }
            while ((int)block.size() >= 2 && !check(block[(int)block.size() - 2], block.back(), i)) {
                block.pop_back();
            }
            block.push_back(i);
        }
    }
}

int main() {
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    int m = 0;
    while (m * m < n) {
        m ++;
    }
    std::vector<std::vector<int>> order(m);
    for (int i = 0; i < n; ++ i) {
        order[i / m].push_back(i);
    }
    for (int i = 0; i < m; ++ i) {
        std::sort(order[i].begin(), order[i].end(), by_a);
    }
    memset(b, 0, sizeof(b));
    memset(used, 0, sizeof(used));
    std::vector<int>  count(m, 1);
    std::vector<Long> delta(m);
    std::vector<std::deque<int>> block(m);
    for (int i = 0; i < m; ++ i) {
        build(order[i], block[i]);
    }
    Long sum = 0;
    Long max = 0;
    for (int _ = 0; _ < n; ++ _) {
        std::pair<Long, int> best(-(Long)1e18, 0);
        for (int i = 0; i < m; ++ i) {
            int x = count[i];
            while ((int)block[i].size() >= 2) {
                int u = block[i][0];
                int v = block[i][1];
                if ((Long)a[u] * x + b[u] > (Long)a[v] * x + b[v]) {
                    break;
                }
                block[i].pop_front();
            }
            if (!block[i].empty()) {
                int u = block[i].front();
                best = std::max(best, std::make_pair((Long)a[u] * x + b[u] + delta[i], u));
            }
        }
        sum += best.first;
        max = std::max(max, sum);
        int u = best.second;
        used[u] = true;
        int t = u / m;
        for (int i = 0; i < t; ++ i) {
            delta[i] += a[u];
        }
        for (int i = t + 1; i < m; ++ i) {
            count[i] ++;
        }
        for (int i = t * m; i < u; ++ i) {
            b[i] += a[u];
        }
        for (int i = u + 1; i < (t + 1) * m && i < n; ++ i) {
            b[i] += a[i];
        }
        build(order[t], block[t]);
    }
    std::cout << max << std::endl;
    return 0;
}
