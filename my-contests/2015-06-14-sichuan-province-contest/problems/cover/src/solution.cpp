#include <algorithm>
#include <cstdio>
#include <cstring>
#include <numeric>
#include <vector>

const int N = 500;

int result;
int count[N];
std::vector <int> neighbours[N];

void dfs(int i, int now)
{
    if (now < result) {
        if (~i) {
            if (count[i]) {
                dfs(i - 1, now);
            } else {
                count[i] ++;
                dfs(i - 1, now + 1);
                count[i] --;
                for (int j = 0; j < (int)neighbours[i].size(); ++ j) {
                    int v = neighbours[i][j];
                    if (!count[v] ++) {
                        now ++;
                    }
                }
                dfs(i - 1, now);
                for (int j = 0; j < (int)neighbours[i].size(); ++ j) {
                    int v = neighbours[i][j];
                    if (!-- count[v]) {
                        now --;
                    }
                }
            }
        } else {
            result = std::min(result, now);
        }
    }
}

int main()
{
    int n, m;
    while (scanf("%d%d", &n, &m) == 2) {
        int k = 0;
        for (int i = 0; i < n; ++ i) {
            neighbours[i].clear();
        }
        for (int i = 0; i < m; ++ i) {
            int a, b;
            scanf("%d%d", &a, &b);
            if (a > b) {
                std::swap(a, b);
            }
            k = std::max(k, a);
            a --;
            b --;
            neighbours[a].push_back(b);
        }
        memset(count, 0, sizeof(*count) * n);
        for (int i = 0; i < k; ++ i) {
            if ((int)neighbours[i].size() > k) {
                count[i] = 1;
            }
        }
        result = k;
        dfs(k - 1, std::accumulate(count, count + n, 0));
        printf("%d\n", result);
    }
    return 0;
}
