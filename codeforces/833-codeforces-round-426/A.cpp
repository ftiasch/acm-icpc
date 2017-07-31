#include <cmath>
#include <cstdio>

bool solve(long long a, long long b)
{
    long long r = pow(a * b, 1. / 3.) + 1;
    while (r * r * r > a * b) {
        r --;
    }
    return r * r * r == a * b && a % r == 0 && b % r == 0;
}

int main()
{
    int T;
    scanf("%d", &T);
    while (T --) {
        int a, b;
        scanf("%d%d", &a, &b);
        puts(solve(a, b) ? "Yes" : "No");
    }
}
