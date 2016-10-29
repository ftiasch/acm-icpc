#include <cstdio>

const int N = 200000;

int cnt_2[N + 1], cnt_5[N + 1];

int main()
{
    for (int n = 1; n <= N; ++ n) {
        cnt_2[n] = cnt_2[n / 2] + n / 2;
    }
    for (int n = 1; n <= N; ++ n) {
        cnt_5[n] = cnt_5[n / 5] + n / 5;
    }
    auto result = 0LL;
    for (int x = 0; x <= N; ++ x) {
        for (int y = 0; x + y <= N; ++ y) {
            int z = N - x - y;
            result += cnt_2[N] - 12 >= cnt_2[x] + cnt_2[y] + cnt_2[z] && cnt_5[N] - 12 >= cnt_5[x] + cnt_5[y] + cnt_5[z];
        }
    }
    printf("%lld\n", result);
}
