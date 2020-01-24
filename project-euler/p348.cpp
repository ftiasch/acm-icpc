#include <cstdio>
#include <cmath>

bool is_sqr(long long n)
{
    long long r = sqrt(n) + 1;
    if (r * r > n) {
        r --;
    }
    return r > 1 && r * r == n;
}

int check(long long n)
{
    int cnt = 0;
    for (long long r = 2; r * r * r <= n && cnt <= 4; ++ r) {
        cnt += is_sqr(n - r * r * r);
    }
    return cnt == 4;
}

int main()
{
    int cnt = 5;
    long long s = 1, result = 0;
    for (int l = 1; cnt > 0; ++ l) {
        for (int half = s; half < 10 * s && cnt > 0; ++ half) {
            int full = half;
            int left = half;
            if (l & 1) {
                left /= 10;
            }
            while (left) {
                full = full * 10 + (left % 10);
                left /= 10;
            }
            if (check(full)) {
                cnt --;
                printf("%lld\n", full);
                result += full;
            }
        }
        if (l % 2 == 0) {
            s *= 10;
        }
    }
    printf("%lld\n", result);
}
