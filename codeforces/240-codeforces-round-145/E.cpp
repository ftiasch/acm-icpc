// Codeforces Round #145 
// Problem E -- Road Repairs
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

const int N = 100000;
const int M = 100000;

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i) 

int n, m, from[M], to[M], type[M];

vector <int> graph[N];

int depth[N], lowest[N], componentCount, belong[N];
vector <int> stack;

void dfs(int u, int d) {
    if (depth[u] == -1) {
        stack.push_back(u);
        int tmp = depth[u] = lowest[u] = d;
        foreach (iter, graph[u]) {
            int v = *iter;
            dfs(v, d + 1);
            tmp = min(tmp, lowest[v]);
        }
        lowest[u] = tmp;
        if (depth[u] == lowest[u]) {
            int v;
            do {
                v = stack.back();
                stack.pop_back();
                lowest[v] = INT_MAX;
                belong[v] = componentCount;
            } while (u != v);
            componentCount ++;
        }
    }
}

int inDegree[N];
int firstEdge[N], nextEdge[M];

bool visited[N];

queue <int> opened;

void flood(int u) {
    if (!visited[u]) {
        visited[u] = true;
        opened.push(u);
        foreach (iter, graph[u]) {
            int v = *iter;
            flood(v);
        }
    }
}

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%d%d", &n, &m);
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d%d", from + i, to + i, type + i);
        from[i] --;
        to[i] --;
    }
    for (int i = 0; i < m; ++ i) {
        if (!type[i]) {
            graph[from[i]].push_back(to[i]);
        }
    }
    memset(depth, -1, sizeof(depth));
    componentCount = 0;
    for (int i = 0; i < n; ++ i) {
        dfs(i, 0);
    }
    memset(inDegree, 0, sizeof(inDegree));
    memset(firstEdge, -1, sizeof(firstEdge));
    for (int i = 0; i < componentCount; ++ i) {
        graph[i].clear();
    }
    for (int i = 0; i < m; ++ i) {
        int a = belong[from[i]];
        int b = belong[to[i]];
        if (a != b) {
            if (type[i]) {
                nextEdge[i] = firstEdge[a];
                firstEdge[a] = i;
            } else {
                inDegree[b] ++;
                graph[a].push_back(b);
            }
        }
    }
    vector <int> result;
    memset(visited, 0, sizeof(visited));
    flood(belong[0]);
    while (!opened.empty()) {
        int u = opened.front();
        opened.pop();
        for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
            int v = belong[to[iter]];
            if (!inDegree[v] && !visited[v]) {
                result.push_back(iter);
                flood(v);
            }
        }
    }
    bool valid = true;
    for (int i = 0; i < componentCount; ++ i) {
        valid &= visited[i];
    }
    if (valid) {
        printf("%d\n", (int)result.size());
        for (int i = 0; i < (int)result.size(); ++ i) {
            printf("%d ", result[i] + 1);
        }
        puts("");
    } else {
        puts("-1");
    }
    return 0;
}
