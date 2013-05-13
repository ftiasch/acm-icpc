// Testing Round #6
// Problem B -- Optimizer
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <deque>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

struct Edge {
    int id, u, v;

    Edge(int id, int u, int v) : id(id), u(u), v(v) {}
};

const int N = 2000000 + 1;
const int M = 200000;

int cover[N], distance[N];
bool in[N], removed[M];
Edge* back[N];
std::vector <Edge*> edges[N];

int main() {
    int n, m;
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; ++ i) {
        int a, l;
        scanf("%d%d", &a, &l);
        a --;
        cover[a] ++;
        cover[a + l] --;
        edges[a].push_back(new Edge(i, a, a + l));
    }
    for (int i = 0; i <= n; ++ i) {
        if (i) {
            cover[i] += cover[i - 1];
            edges[i].push_back(new Edge(-1, i, i - 1));
        }
        if (!cover[i] && i + 1 <= n) {
            edges[i].push_back(new Edge(-1, i, i + 1));
        }
    }
    std::fill(distance, distance + (n + 1), INT_MAX);
    distance[0] = 0;
    std::deque <int> queue;
    queue.push_back(0);
    while (!queue.empty()) {
        int u = queue.front();
        queue.pop_front();
        in[u] = false;
        foreach (iter, edges[u]) {
            Edge &edge = **iter;
            int length = edge.id != -1;
            int v = edge.v;
            if (distance[u] + length < distance[v]) {
                distance[v] = distance[u] + length;
                back[v] = *iter;
                if (!in[v]) {
                    if (length) {
                        queue.push_back(v);
                    } else {
                        queue.push_front(v);
                    }
                }
            }
        }
    }
    memset(removed, true, sizeof(removed));
    for (int u = n; u; u = back[u]->u) {
        if (back[u]->id != -1) {
            removed[back[u]->id] = false;
        }
    }
    printf("%d\n", m - distance[n]);
    for (int i = 0; i < m; ++ i) {
        if (removed[i]) {
            printf("%d ", i + 1);
        }
    }
    puts("");
}
