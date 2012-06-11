// SGU 145 -- Strange People
#include <cstdio>
#include <cstring>
#include <utility>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 100;
const int INF = 1000000000;

int n, m, need, graph[N][N], source, target, floyd[N][N], dist[N], limit, path_count;
bool visit[N];
vector <int> path;
vector <pair <int, vector <int> > > paths;

void dfs(bool keep, int u, int length) {
    if (path_count < need && length + dist[u] <= limit) {
        if (u == target) {
            path_count ++;
            if (keep) {
                paths.push_back(make_pair(length, path));
            }
        } else {
            for (int v = 0; v < n; ++ v) {
                if (!visit[v] && graph[u][v] < INF) {
                    visit[v] = true;
                    if (keep) {
                        path.push_back(v);
                    }
                    dfs(keep, v, length + graph[u][v]);
                    visit[v] = false;
                    if (keep) {
                        path.pop_back();
                    }
                }
            }
        }
    }
}

int main() {
    scanf("%d%d%d", &n, &m, &need);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            graph[i][j] = INF;
        }
    }
    for (int i = 0; i < m; ++ i) {
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        a --;
        b --;
        graph[a][b] = graph[b][a] = c;
    }
    scanf("%d%d", &source, &target);
    source --;
    target --;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            floyd[i][j] = graph[i][j];
        }
        floyd[i][i] = 0;
    }
    for (int k = 0; k < n; ++ k) {
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                floyd[i][j] = min(floyd[i][j], floyd[i][k] + floyd[k][j]);
            }
        }
    }
    for (int i = 0; i < n; ++ i) {
        dist[i] = floyd[i][target];
    }
    int lower = 0;
    int upper = INF; 
    path.push_back(source);
    memset(visit, 0, sizeof(visit));
    visit[source] = true;
    while (lower < upper) {
        int mider = (lower + upper) >> 1;
        limit = mider;
        path_count = 0;
        dfs(false, source, 0);
        if (path_count >= need) {
            upper = mider;
        } else {
            lower = mider + 1;
        }
    }
    limit = upper;
    path_count = 0;
    dfs(true, source, 0);
    sort(paths.begin(), paths.end());
    vector <int> path = paths[need - 1].second;
    printf("%d %d\n", upper, (int)path.size());
    for (int i = 0; i < (int)path.size(); ++ i) {
        printf("%d%c", path[i] + 1, i == (int)path.size() - 1? '\n': ' ');
    }
    return 0;
}
