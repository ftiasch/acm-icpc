#include <algorithm>
#include <cstdio>
#include <cstring>
#include <vector>

using LL = long long;

const int MOD = 1000267129;

std::vector<int> digits;

int ways[2][2][2][(1 << 10) + 1];

void update(int& x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}


int solve(LL n)
{
    digits.clear();
    LL nn = n;
    while (nn) {
        digits.push_back(nn % 10);
        nn /= 10;
    }
    std::reverse(digits.begin(), digits.end());
    memset(ways, 0, sizeof(ways));
    int row = 0;
    ways[row][0][0][0] = 1;
    for (auto u : digits) {
        memset(ways[row ^ 1], 0, sizeof(ways[row ^ 1]));
        for (int start = 0; start < 2; ++ start) {
            for (int less = 0; less < 2; ++ less) {
                for (int mask = 0; mask < 1 << 10; ++ mask) {
                    if (!ways[row][start][less][mask]) {
                        continue;
                    }
                    for (int d = less ? 9 : u; d >= 0; -- d) {
                        int new_mask = mask;
                        if (start || d) {
                            new_mask |= 1 << d;
                        }
                        update(ways[row ^ 1][start || d][less || d < u][new_mask], ways[row][start][less][mask]);
                    }
                }
            }
        }
        row ^= 1;
    }
    int result = (__int128)(n - 1) * (n - 2) / 2 % MOD;
    for (int a = 0; a < 1 << 10; ++ a) {
        for (int b = a + 1; b < 1 << 10; ++ b) {
            if ((a & b) == 0) {
                update(result, MOD - 1LL * ways[row][1][1][a] * ways[row][1][1][b] % MOD);
            }
        }
    }
    return result;
}

int main()
{
    printf("%d\n", solve(100));
    printf("%d\n", solve(1000000000000000000LL));
}
