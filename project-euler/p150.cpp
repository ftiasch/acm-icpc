#include <algorithm>
#include <cstdio>

const int N = 1000;

int a[N][N];
long long down[N][N];

int main()
{
    int t = 0;
    for (int i = 0; i < N; ++ i) {
        for (int j = 0; j <= i; ++ j) {
            t = (615949LL * t + 797807LL) % (1 << 20);
            a[i][j] = t - (1 << 19);
        }
    }
    auto result = 0LL;
    for (int i = 0; i < N; ++ i) {
        for (int j = 0; j <= i; ++ j) {
            int x = i;
            int y = j;
            auto sum = 0LL;
            while (x >= 0 && y >= 0) {
                down[x][y] += a[i][y];
                sum += down[x][y];
                result = std::min(result, sum);
                x --;
                y --;
            }
        }
    }
    printf("%lld\n", result);
}
