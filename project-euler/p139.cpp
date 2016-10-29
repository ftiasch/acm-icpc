#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstdlib>

const int N = 100000000;

int result;

void generate(int p1, int q1, int p2, int q2)
{
    int p = p1 + p2;
    int q = q1 + q2;
    if (2 * q * q + 2 * q < N) {
        if (q - p & 1) {
            int a = q * q - p * p;
            int b = 2 * q * p;
            int c = q * q + p * p;
            if (c % std::abs(a - b) == 0) {
                result += (N - 1) / (a + b + c);
            }
        }
        generate(p1, q1, p, q);
        generate(p, q, p2, q2);
    }
}

int main()
{
    generate(0, 1, 1, 1);
    printf("%d\n", result);
}
