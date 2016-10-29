#include <algorithm>
#include <cstdio>
#include <cstring>

const int N = 40;

long long ways[N + 1][10][10][10];

int main()
{
    memset(ways, 0, sizeof(ways));
    for (int d = 0; d < 10; ++ d) {
        ways[1][d][d][d] = 1;
    }
    for (int i = 1; i + 1 <= N; ++ i) {
        for (int last = 0; last < 10; ++ last) {
            for (int left = 0; left < 10; ++ left) {
                for (int right = left; right < 10; ++ right) {
                    if (last) {
                        ways[i + 1][last - 1][std::min(last - 1, left)][right] += ways[i][last][left][right];
                    }
                    if (last < 9) {
                        ways[i + 1][last + 1][left][std::max(last + 1, right)] += ways[i][last][left][right];
                    }
                }
            }
        }
    }
    long long result = 0;
    for (int i = 1; i <= N; ++ i) {
        for (int last = 1; last < 10; ++ last) {
            result += ways[i][last][0][9];
        }
    }
    printf("%lld\n", result);
}
