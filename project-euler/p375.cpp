#include <iostream>
#include <cstdio>
#include <climits>
#include <utility>
#include <vector>

__int128 solve(int n)
{
    int a = 290797;
    __int128 result = 0, sum = 0;
    std::vector<std::pair<int, int>> st(50515094);
    int t = 0;
    st[0] = {INT_MIN, 0};
    for (int i = 1; i <= n; ++ i) {
        a = (long long)a * a % 50515093;
        while (st[t].first >= a) {
            sum -= (long long)st[t].first * (st[t].second - st[t - 1].second);
            t --;
        }
        st[++ t] = {a, i};
        sum += (long long)st[t].first * (st[t].second - st[t - 1].second);
        result += sum;
        if (i % 10000000 == 0) {
            fprintf(stderr, "%.2f%%\n", (double)i / n * 100);
        }
    }
    return result;
}

int main()
{
    printf("%lld\n", solve(10));
    printf("%lld\n", solve(10000));
    printf("%lld\n", solve(2000000000));
}
