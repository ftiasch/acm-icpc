// Problem C -- Wormhole Transport
#include <cstdio>
#include <cstring>
#include <algorithm>
#include <iostream>
using namespace std;

const int N = 222;
const int M = 22222;
const long long INF = 1000000000000000000LL;

struct Data {
    int got;
    long long cost;

    Data(int got = 0, long long cost = 0): got(got), cost(cost) {}
};

Data operator +(const Data &a, const Data &b) {
    return Data(a.got + b.got, a.cost + b.cost);
}

bool operator <(const Data &a, const Data &b) {
    if (a.got == b.got) {
        return a.cost < b.cost;
    }
    return a.got > b.got;
}

int n, m, p[N], s[N], edge_count, first_edge[N], to[M], length[M], next_edge[M], id[N], queue[N];
long long minimum_cost[N][1 << 8];
Data optimal[1 << 8];
bool visit[N];

void add_edge(int u, int v, int w) {
    to[edge_count] = v;
    length[edge_count] = w;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

int main() {
    while (scanf("%d", &n) == 1) {
        for (int i = 0; i < n; ++ i) {
            scanf("%d%d", p + i, s + i);
        }
        edge_count = 0;
        memset(first_edge, -1, sizeof(first_edge));
        scanf("%d", &m);
        for (int i = 0; i < m; ++ i) {
            int a, b, c;
            scanf("%d%d%d", &a, &b, &c);
            add_edge(a - 1, b - 1, c);
            add_edge(b - 1, a - 1, c);
        }
        m = 0;
        memset(id, -1, sizeof(id));
        for (int i = 0; i < n; ++ i) {
            if (p[i] > 0 || s[i] > 0) {
                id[i] = m ++;
            }
        }
        for (int i = 0; i < n; ++ i) {
            for (int mask = 0; mask < (1 << m); ++ mask) {
                minimum_cost[i][mask] = INF;
            }
        }
        for (int i = 0; i < n; ++ i) {
            minimum_cost[i][0] = 0;
            if (id[i] != -1) {
                minimum_cost[i][1 << id[i]] = 0;
            }
        }
        for (int mask = 1; mask < (1 << m); ++ mask) {
            for (int i = 0; i < n; ++ i) {
                for (int sub = (mask - 1) & mask; sub > 0; sub = (sub - 1) & mask) {
                    minimum_cost[i][mask] = min(minimum_cost[i][sub] + minimum_cost[i][mask ^ sub], minimum_cost[i][mask]);
                }
            }
            int head = 0;
            int tail = 0;
            for (int i = 0; i < n; ++ i) {
                visit[i] = true;
                queue[tail ++] = i;
            }
            while (head != tail) {
                int u = queue[head];
                visit[u] = false;
                head = (head + 1) % N;
                for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
                    int v = to[iter];
                    if (minimum_cost[u][mask] + length[iter] < minimum_cost[v][mask]) {
                        minimum_cost[v][mask] = minimum_cost[u][mask] + length[iter];
                        if (!visit[v]) {
                            visit[v] = true;
                            queue[tail] = v;
                            tail = (tail + 1) % N;
                        }
                    }
                }
            }
        }
        Data result = Data(0, 0);
        optimal[0] = Data(0, 0);
        for (int mask = 1; mask < (1 << m); ++ mask) {
            int factory_count = 0;
            int resource_count = 0;
            for (int i = 0; i < n; ++ i) {
                if (id[i] != -1 && (mask >> id[i] & 1) == 1) {
                    factory_count += p[i];
                    resource_count += s[i];
                }
            }
            long long ret = INF;
            for (int i = 0; i < n; ++ i) {
                ret = min(ret, minimum_cost[i][mask]);
            }
            if (ret < INF) {
                optimal[mask] = Data(min(factory_count, resource_count), ret);
            }
            for (int sub = (mask - 1) & mask; sub > 0; sub = (sub - 1) & mask) {
                optimal[mask] = min(optimal[mask], optimal[sub] + optimal[mask ^ sub]);
            }
            result = min(result, optimal[mask]);
        }
        cout << result.got << " " << result.cost << endl;
    }
    return 0;
}
