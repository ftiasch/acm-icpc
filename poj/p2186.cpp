#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <stack>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 10000;

int n, m, in_deg[N];
std::vector <int> graph[N], new_graph[N];

int depth[N], lowest[N], scc_count, id[N];
std::stack <int> stack;

void dfs(int p, int u) {
    if (depth[u] == -1) {
        int tmp = lowest[u] = depth[u] = p == - 1 ? 0 : depth[p] + 1;
        stack.push(u);
        foreach (iter, graph[u]) {
            int v = *iter;
            dfs(u, v);
            tmp = std::min(tmp, lowest[v]);
        }
        lowest[u] = tmp;
        if (depth[u] == lowest[u]) {
            while (true) {
                int v = stack.top();
                stack.pop();
                id[v] = scc_count;
                lowest[v] = INT_MAX;
                if (u == v) {
                    break;
                }
            }
            scc_count ++;
        }
    }
}

bool visit[N];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0, a, b; i < m; ++ i) {
        scanf("%d%d", &a, &b);
        a --;
        b --;
        graph[b].push_back(a);
    }
    memset(depth, -1, sizeof(depth));
    scc_count = 0;
    for (int i = 0; i < n; ++ i) {
        dfs(-1, i);
    }
    memset(in_deg, 0, sizeof(in_deg));
    for (int i = 0; i < n; ++ i) {
        foreach (iter, graph[i]) {
            int j = *iter;
            if (id[i] != id[j]) {
                new_graph[id[i]].push_back(id[j]);
                in_deg[id[j]] ++;
            }
        }
    }
    memset(visit, 0, sizeof(visit));
    visit[scc_count - 1] = true;
    for (int i = scc_count - 1; i >= 0; -- i) {
        if (visit[i]) {
            foreach (iter, new_graph[i]) {
                int j = *iter;
                visit[j] = true;
            }
        } else {
            puts("0");
            return 0;
        }
    }
    int answer = 0;
    for (int i = 0; i < n; ++ i) {
        answer += id[i] == scc_count - 1;
    }
    printf("%d\n", answer);
    return 0;
}
