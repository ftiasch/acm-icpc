#include <algorithm>
#include <cmath>
#include <cstdio>
#include <climits>

bool is_square(int n)
{
    int s = sqrt(n);
    return s * s == n;
}

int main()
{
    int result = INT_MAX;
    for (int c = 1; c * c < result; ++ c) {
        for (int b = 1; b < c && b * b / 2 + c * c < result; ++ b) {
            for (int a = 2 - b % 2; a < b && (a * a + b * b) / 2 + c * c < result; a += 2) {
                int x = (b * b - a * a) / 2;
                int y = b * b - x;
                int z = c * c - y;
                if (y < z && is_square(z - y) && is_square(z - x) && is_square(x + z)) {
                    printf("%d, %d, %d\n", x, y, z);
                    result = std::min(result, x + y + z);
                }
            }
        }
    }
    printf("%d\n", result);
}
