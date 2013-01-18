// 2011 Multi-Univ Training Contest #3
// Problem C -- The King's Problem  
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <stack>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i) 

const int N = 5000 + 1;

std::vector <int> graph[N], bigraph[N];

int depth[N], lowest[N], label_count, label[N], match[N];
bool visit[N];
std::stack <int> stack;

void dfs(int p, int u) {
    if (depth[u] == -1) {
        int buffer = lowest[u] = depth[u] = p == -1 ? 0 : depth[p] + 1;
        stack.push(u);
        foreach (iter, graph[u]) {
            int v = *iter;
            dfs(u, v);
            buffer = std::min(buffer, lowest[v]);
        }
        lowest[u] = buffer;
        if (depth[u] == lowest[u]) {
            int v;
            do {
                depth[v = stack.top()] = INT_MAX;
                stack.pop();
                label[v] = label_count;
            } while (u != v);
            label_count ++;
        }
    }
}

bool find(int i) {
    if (visit[i]) {
        return false;
    }
    visit[i] = true;
    foreach (iter, bigraph[i]) {
        int j = *iter;
        if (match[j] == -1 || find(match[j])) {
            match[j] = i;
            return true;
        }
    }
    return false;
}

int main() {
    int test_count;
    scanf("%d", &test_count);
    while (test_count --) {
        int n, m;
        scanf("%d%d", &n, &m);
        for (int i = 1; i <= n; ++ i) {
            graph[i].clear();
        }
        for (int i = 0; i < m; ++ i) {
            int a, b;
            scanf("%d%d", &a, &b);
            graph[a].push_back(b);
        }
        memset(depth, -1, sizeof(depth));
        label_count = 0;
        for (int i = 1; i <= n; ++ i) {
            dfs(-1, i);
        }
        for (int i = 0; i < label_count; ++ i) {
            bigraph[i].clear();
        }
        for (int i = 1; i <= n; ++ i) {
            foreach (iter, graph[i]) {
                int j = *iter;
                if (label[i] != label[j]) {
                    bigraph[label[i]].push_back(label[j]);
                }
            }
        }
        int answer = label_count;
        memset(match, -1, sizeof(match));
        memset(visit, 0, sizeof(visit));
        for (int i = 0; i < label_count; ++ i) {
            if (find(i)) {
                answer --;
                memset(visit, 0, sizeof(visit));
            }
        }
        printf("%d\n", answer);
    }
    return 0;
}
