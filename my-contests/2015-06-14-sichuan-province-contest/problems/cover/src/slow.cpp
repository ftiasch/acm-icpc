#include <algorithm>
#include <cstdio>
#include <cstring>

const int N = 500;
const int M = N * (N - 1) / 2;

int a[M], b[M], result;
bool marked[N];

void dfs(int i, int now)
{
    if (now < result) {
        if (~i) {
            if (marked[a[i]] || marked[b[i]]) {
                dfs(i - 1, now);
            } else {
                marked[a[i]] = true;
                dfs(i - 1, now + 1);
                marked[a[i]] = false;
                marked[b[i]] = true;
                dfs(i - 1, now + 1);
                marked[b[i]] = false;
            }
        } else {
            result = now;
        }
    }
}

int main()
{
    int n, m;
    while (scanf("%d%d", &n, &m) == 2) {
        for (int i = 0; i < m; ++ i) {
            scanf("%d%d", a + i, b + i);
            a[i] --;
            b[i] --;
        }
        result = n;
        memset(marked, 0, sizeof(*marked) * n);
        dfs(m - 1, 0);
        printf("%d\n", result);
    }
    return 0;
}
