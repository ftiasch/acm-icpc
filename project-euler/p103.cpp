#include <algorithm>
#include <cstdlib>
#include <cstdio>
#include <climits>

const int N = 7;
const int M = 100000;

int a[N], sums[1 << N], stamp, visited[M];

void dfs(int i)
{
    if (i < N) {
        a[i] = 1;
        while (a[i] < a[i - 1]) {
            stamp ++;
            for (int j = 0; j < 1 << i; ++ j) {
                visited[sums[j]] = stamp;
            }
            bool valid = true;
            for (int j = 1 << i; j < 1 << i + 1 && valid; ++ j) {
                sums[j] = sums[j - (1 << i)] + a[i];
                valid &= visited[sums[j]] != stamp;
            }
            if (valid) {
                dfs(i + 1);
            }
            a[i] ++;
        }
    } else {
        int sum = a[N - 1];
        for (int i = 0; i < N - 1; ++ i) {
            sum += a[N - 2 - i] - a[i];
            if (sum <= 0) {
                return;
            }
        }
        for (int j = N - 1; j >= 0; -- j) {
            printf("%d", a[j]);
        }
        puts("");
        exit(0);
    }
}

int main()
{
    while (true) {
        a[0] ++;
        sums[1] = a[0];
        dfs(1);
    }
}
