// SGU 323 -- Aviamachinations
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 2000;
const int M = 200000;

int n, m, k, a[M], b[M], c[M], p[M], order[M];
std::vector <int> airlines[N];

bool by_c(int i, int j) {
    return p[i] < p[j];
}

int parent[N];

int find(int i) {
    if (parent[i] == -1) {
        return i;
    }
    return parent[i] = find(parent[i]);
}

int main() {
    scanf("%d%d%d", &n, &m, &k);
    for (int i = 0; i < k; ++ i) {
        scanf("%d%d%d%d", a + i, b + i, c + i, p + i);
        airlines[-- c[i]].push_back(i);
        order[i] = i;
    }
    std::sort(order, order + k, by_c);
    memset(parent, -1, sizeof(parent));
    std::vector <int> mst;
    for (int i = 0; i < k; ++ i) {
        int x = find(-- a[order[i]]);
        int y = find(-- b[order[i]]);
        if (x != y) {
            parent[x] = y;
            mst.push_back(order[i]);
        }
    }
    int answer = INT_MAX;
    int airline_id = -1;
    std::vector <int> choices;
    for (int i = 0; i < m; ++ i) {
        memset(parent, -1, sizeof(parent));
        foreach (iter, airlines[i]) {
            int x = find(a[*iter]);
            int y = find(b[*iter]);
            if (x != y) {
                parent[x] = y;
            }
        }
        int cost = 0;
        std::vector <int> change;
        foreach (iter, mst) {
            int x = find(a[*iter]);
            int y = find(b[*iter]);
            if (x != y) {
                parent[x] = y;
                cost += p[*iter];
                change.push_back(*iter);
            }
        }
        if (cost < answer) {
            answer = cost;
            airline_id = i;
            choices = change;
        }
    }
    printf("%d %d %d\n", answer, airline_id + 1, (int)choices.size());
    foreach (iter, choices) {
        printf("%d ", *iter + 1);
    }
    puts("");
    return 0;
}
