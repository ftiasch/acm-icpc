#include <cstdio>
#include <climits>
#include <vector>

int count(int n, int limit = INT_MAX)
{
    if (n & 1) {
        return 0;
    }
    n >>= 1;
    // (a * b + b * c + c * a) + 2 * (a + b + c) * d + 2 * d * (d - 1) = n
    int result = 0;
    for (int d = 0; 3 + 6 * d + 2 * d * (d - 1) <= n; ++ d) {
        for (int a = 1; 2 * a + 1 + 2 * (a + 2) * d + 2 * d * (d - 1) <= n; ++ a) {
            for (int b = 1; b <= a && a * b + a + b + 2 * (a + b + 1) * d + 2 * d * (d - 1) <= n; ++ b) {
                int rem = n - a * b - 2 * (a + b) * d - 2 * d * (d - 1);
                int div = a + b + 2 * d;
                if (rem % div == 0 && rem / div <= b) {
                    result ++;
                    if (result > limit) {
                        return result;
                    }
                }
            }
        }
    }
    return result;
}

int find(int limit)
{
    int n = 1;
    while (count(n, limit) != limit) {
        n ++;
    }
    return n;
}

int main()
{
    for (auto&& n : std::vector<int> {22, 46, 78, 118}) {
        fprintf(stderr, "f(%d) = %d\n", n, count(n));
    }
    fprintf(stderr, "%d\n", find(10));
    printf("%d\n", find(1000));
}
