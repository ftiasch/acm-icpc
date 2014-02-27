#include <cstdio>
#include <map>

const int MOD = (int)1e9 + 7;

int inverse(int a)
{
    return a == 1 ? 1 : (long long)(MOD - MOD / a) * inverse(MOD % a) % MOD;
}

int main()
{
    int n;
    scanf("%d", &n);
    std::map <int, int> map;
    for (int i = 0; i < n; ++ i) {
        static int a;
        scanf("%d", &a);
        for (int d = 2; d * d <= a; ++ d) {
            for (; a % d == 0; a /= d) {
                map[d] ++;
            }
        }
        if (a > 1) {
            map[a] ++;
        }
    }
    long long result = 1;
    for (auto &iterator : map) {
        int v = iterator.second;
        // binom(v + n - 1, v)
        for (int i = 0; i < v; ++ i) {
            (result *= n + i) %= MOD;
            (result *= inverse(i + 1)) %= MOD;
        }
    }
    printf("%d\n", (int)result);
    return 0;
}
