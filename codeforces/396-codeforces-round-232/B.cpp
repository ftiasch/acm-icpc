#include <algorithm>
#include <iostream>

typedef long long Long;

bool is_prime(int n)
{
    for (int d = 2; d * d <= n; ++ d) {
        if (n % d == 0) {
            return false;
        }
    }
    return true;
}

int search(int n, int d)
{
    while (!is_prime(n)) {
        n += d;
    }
    return n;
}

int main()
{
    int t;
    std::cin >> t;
    while (t --) {
        static int n;
        std::cin >> n;
        int p = search(n, -1);
        int q = search(n + 1, 1);
        Long a = (long long)p * q - 2 * q + 2 * (n - p + 1);
        Long b = 2LL * p * q;
        Long g = std::__gcd(a, b);
        std::cout << a / g << "/" << b / g << std::endl;
    }
    return 0;
}
