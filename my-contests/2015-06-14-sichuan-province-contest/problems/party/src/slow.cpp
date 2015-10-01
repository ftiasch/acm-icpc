#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

const int N = 1000;

int n, w[N], p[N], c[N], result;
std::vector <std::vector <int>> graph;

void dfs(int i, int sum)
{
    if (sum < result) {
        if (i < n) {
            for (c[i] = 0; c[i] <= 2; ++ c[i]) {
                if ((p[i] & c[i]) == c[i]) {
                    bool valid = true;
                    if (c[i]) {
                        for (int j : graph[i]) {
                            valid &= c[j] != c[i];
                        }
                    }
                    if (valid) {
                        dfs(i + 1, sum + (c[i] ? 0 : w[i]));
                    }
                }
            }
        } else {
            result = sum;
        }
    }
}

int main()
{
    int m;
    while (scanf("%d%d", &n, &m) == 2) {
        for (int i = 0; i < n; ++ i) {
            scanf("%d", w + i);
        }
        for (int i = 0; i < n; ++ i) {
            scanf("%d", p + i);
        }
        graph.clear();
        graph.resize(n);
        for (int i = 0; i < m; ++ i) {
            int a, b;
            scanf("%d%d", &a, &b);
            a --;
            b --;
            if (a > b) {
                std::swap(a, b);
            }
            graph[b].push_back(a);
        }
        result = INT_MAX;
        dfs(0, 0);
        printf("%d\n", result);
    }
    return 0;
}
