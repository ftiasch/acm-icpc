#include <algorithm>
#include <cstdio>
#include <cstring>
#include <climits>
#include <iostream>
#include <vector>
#include <queue>

typedef long long Long;

const int N = 100000;
const int MAX = 314000000;

int m, n, head[N], next[N], source[N], degree[N], bonus[N];
bool valid[N];
Long values[N];
std::vector <int> splits[N], included[N];

Long minimum[N], maximum[N];

int main() {
    scanf("%d%d", &m, &n);
    memset(head, -1, sizeof(head));
    memset(values, 0, sizeof(values));
    std::fill(minimum, minimum + n, (MAX + 1LL) * n);
    for (int i = 0; i < m; ++ i) {
        int count;
        scanf("%d%d", source + i, &count);
        source[i] --;
        while (count --) {
            int target;
            scanf("%d", &target);
            target --;
            if (target >= 0) {
                values[i] += minimum[target];
                splits[i].push_back(target);
                included[target].push_back(i);
            } else {
                bonus[i] ++;
            }
        }
        values[i] += bonus[i];
        next[i] = head[source[i]];
        head[source[i]] = i;
    }
    {
        std::priority_queue <std::pair <Long, int>> queue;
        for (int i = 0; i < m; ++ i) {
            queue.push(std::make_pair(-values[i], i));
        }
        while (!queue.empty()) {
            auto pair = queue.top();
            queue.pop();
            int i = pair.second;
            if (-pair.first == values[i] && values[i] < minimum[source[i]]) {
                int new_values = std::min(values[i], (Long)MAX);
                for (int j : included[source[i]]) {
                    values[j] += new_values - minimum[source[i]];
                    queue.push(std::make_pair(-values[j], j));
                }
                minimum[source[i]] = new_values;
            }
        }
    }
    memset(degree, 0, sizeof(degree));
    std::fill(maximum, maximum + n, -2);
    for (int i = 0; i < n; ++ i) {
        included[i].clear();
    }
    for (int i = 0; i < m; ++ i) {
        valid[i] = true;
        for (int v : splits[i]) {
            valid[i] &= minimum[v] <= (Long)MAX;
        }
        if (valid[i]) {
            for (int v : splits[i]) {
                degree[source[i]] ++;
                included[v].push_back(i);
            }
        }
    }
    {
        std::queue <int> queue;
        for (int i = 0; i < n; ++ i) {
            if (!degree[i]) {
                queue.push(i);
            }
        }
        while (!queue.empty()) {
            int u = queue.front();
            queue.pop();
            for (int i = head[u]; ~i; i = next[i]) {
                if (valid[i]) {
                    long long value = bonus[i];
                    for (int v : splits[i]) {
                        value += maximum[v];
                    }
                    maximum[u] = std::max(maximum[u], std::min(value, (Long)MAX));
                }
            }
            for (int i : included[u]) {
                degree[source[i]] --;
                if (!degree[source[i]]) {
                    queue.push(source[i]);
                }
            }
        }
    }
    for (int i = 0; i < n; ++ i) {
        if (minimum[i] > MAX) {
            minimum[i] = maximum[i] = -1;
        } else if (maximum[i] > MAX) {
            maximum[i] = -1;
        }
        std::cout << minimum[i] << " " << maximum[i] << std::endl;
    }
    return 0;
}
