// SGU 280 -- Trade centers
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <utility>
#include <functional>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 30000;

int n, m, parent[N], distance[N];
std::vector <int> tos[N];
std::vector <std::pair <int, int> > order;

void prepare(int p, int u, int d) {
    parent[u] = p;
    order.push_back(std::make_pair(d, u));
    foreach (iter, tos[u]) {
        int v = *iter;
        if (v != p) {
            prepare(u, v, d + 1);
        }
    }
}

void flood(int u, int d) {
    if (d > m || d >= distance[u]) {
        return;
    }
    distance[u] = d;
    foreach (iter, tos[u]) {
        flood(*iter, d + 1);
    }
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0, a, b; i < n - 1; ++ i) {
        scanf("%d%d", &a, &b);
        a --;
        b --;
        tos[a].push_back(b);
        tos[b].push_back(a);
    }
    prepare(-1, 0, 0);
    std::sort(order.begin(), order.end(), std::greater <std::pair <int, int> >());
    std::fill(distance, distance + n, INT_MAX);
    std::vector <int> answer;
    foreach (iter, order) {
        int u = iter->second;
        if (distance[u] == INT_MAX) {
            for (int k = 0; k < m && parent[u] != -1; ++ k) {
                u = parent[u];
            }
            flood(u, 0);
            answer.push_back(u);
        }
    }
    printf("%d\n", (int)answer.size());
    foreach (iter, answer) {
        printf("%d\n", 1 + *iter);
    }
    return 0;
}
