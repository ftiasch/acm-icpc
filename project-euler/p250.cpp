#include <cstdio>
#include <cstring>

const auto MOD = 250;

long ways[MOD], new_ways[MOD];

int pow(int a, int n)
{
    int result = 1;
    while (n) {
        if (n & 1) {
            result = (long)result * a % MOD;
        }
        a = (long)a * a % MOD;
        n >>= 1;
    }
    return result;
}

void update(long& x, long a)
{
    x += a;
    if (x >= 10000000000000000) {
        x -= 10000000000000000;
    }
}

int main()
{
    memset(ways, 0, sizeof(ways));
    ways[0] = 1;
    for (auto n = 1; n <= 250250; ++ n) {
        auto r = pow(n, n);
        memset(new_ways, 0, sizeof(new_ways));
        for (auto x = 0; x < MOD; ++ x) {
            update(new_ways[x], ways[x]);
            update(new_ways[(x + r) % MOD], ways[x]);
        }
        memcpy(ways, new_ways, sizeof(ways));
    }
    printf("%ld\n", ways[0] - 1);
}
