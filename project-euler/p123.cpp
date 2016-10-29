#include <cstdio>
#include <cstring>

bool is_prime(int p)
{
    for (int d = 2; d * d <= p; ++ d) {
        if (p % d == 0) {
            return false;
        }
    }
    return true;
}

long long count(long long p, int n)
{
    long long result = n * p + 1;
    if (n & 1) {
        result += n * p - 1;
    } else {
        result -= n * p - 1;
    }
    result %= p * p;
    if (result < 0) {
        result += p * p;
    }
    return result;
}

int main()
{
    int n = 0;
    for (int p = 2; ; ++ p) {
        if (is_prime(p)) {
            n ++;
            if (count(p, n) >= 10000000000) {
                printf("%d\n", n);
                return 0;
            }
        }
    }
}
