#include <cassert>
#include <iostream>

const int N = 100000;

long long result = 0;

void solve(int p, int q)
{
    int a = p;
    int b = q - p;
    int ub = N / (a + b + b);
    for (int k = 1; k <= ub; ++ k) {
        result += std::min(k * q - 1, N - k * q) / q - (k * b - 1) / q;
    }
}

void recur(int p1, int q1, int p2, int q2)
{
    int p = p1 + p2;
    int q = q1 + q2;
    if (q <= N) {
        solve(p, q);
        recur(p1, q1, p, q);
        recur(p, q, p2, q2);
    }
}

int main()
{
    recur(0, 1, 1, 2);
    solve(1, 2);
    std::cout << result << std::endl;
    // for (int a = 1; a <= N; ++ a) {
    //     for (int b = a; a + b <= N; ++ b) {
    //         for (int c = b; c < a + b && a + b + c <= N; ++ c) {
    //             if ((long long)a * c % (a + b) == 0) {
    //                 printf("%d, %d, %d\n", a, b, c);
    //                 result --;
    //             }
    //         }
    //     }
    // }
    // assert(result == 0);
}
