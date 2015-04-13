#include <cmath>
#include <cstdio>
#include <cstring>
#include <queue>
#include <vector>
#include <algorithm>

const int N = 100;
const int M = 400;

int a[M], b[M], c[M], head[N], count, to[M << 1], next[M << 1];
double capacity[M << 1];

void add_edge(int u, int v, double w)
{
    to[count] = v;
    capacity[count] = w;
    next[count] = head[u];
    head[u] = count ++;
}

const double EPSILON = 1e-9;

int level[N];

bool bfs(int n, int source, int target)
{
    memset(level, -1, sizeof(*level) * n);
    level[target] = 0;
    std::queue <int> queue;
    queue.push(target);
    while (!queue.empty() && level[source] == -1) {
        int v = queue.front();
        queue.pop();
        for (int it = head[v]; ~it; it = next[it]) {
            int u = to[it];
            if (capacity[it ^ 1] > EPSILON && level[u] == -1) {
                level[u] = level[v] + 1;
                queue.push(u);
            }
        }
    }
    return level[source] != -1;
}

double dfs(int u, int target, double delta)
{
    if (u == target) {
        return delta;
    }
    double left = delta;
    for (int it = head[u]; ~it; it = next[it]) {
        int v = to[it];
        if (capacity[it] > EPSILON && level[u] == level[v] + 1) {
            double ret = dfs(v, target, std::min(left, capacity[it]));
            capacity[it] -= ret;
            capacity[it ^ 1] += ret;
            left -= ret;
            if (left < EPSILON) {
                return delta;
            }
        }
    }
    level[u] = -1;
    return delta - left;
}

int main()
{
    int n, m;
    bool first = true;
    while (scanf("%d%d", &n, &m) == 2) {
        double lambda = 0;
        for (int i = 0; i < m; ++ i) {
            scanf("%d%d%d", a + i, b + i, c + i);
            a[i] --;
            b[i] --;
            lambda += c[i];
        }
        lambda /= m;
        for (int _ = 5; ~_; -- _) {
            count = 0;
            memset(head, -1, sizeof(*head) * n);
            std::vector <int> solution;
            for (int i = 0; i < m; ++ i) {
                if (c[i] > lambda) {
                    add_edge(a[i], b[i], c[i] - lambda);
                    add_edge(b[i], a[i], c[i] - lambda);
                } else {
                    solution.push_back(i);
                }
            }
            while (bfs(n, 0, n - 1)) {
                dfs(0, n - 1, INFINITY);
            }
            for (int i = 0; i < m; ++ i) {
                if (c[i] > lambda && (!~level[a[i]] ^ !~level[b[i]])) {
                    solution.push_back(i);
                }
            }
            lambda = 0;
            for (int i = 0; i < (int)solution.size(); ++ i) {
                lambda += c[solution[i]];
            }
            lambda /= (int)solution.size();
            if (!_) {
                if (first) {
                    first = false;
                } else {
                    puts("");
                }
                std::sort(solution.begin(), solution.end());
                printf("%d\n", (int)solution.size());
                for (int i = 0; i < (int)solution.size(); ++ i) {
                    printf("%d%c", solution[i] + 1, " \n"[i == (int)solution.size() - 1]);
                }
            }
        }
    }
    return 0;
}
