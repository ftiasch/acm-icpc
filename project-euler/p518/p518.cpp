#include <algorithm>
#include <bitset>
#include <cstdio>
#include <map>
#include <vector>

const int N = 1e8;

std::bitset <N> divided;
std::vector <int> primes;
std::map <int, std::vector <int>> group;

void generate(int i, int q, int r)
{
    if (!divided.test(q * q * r - 1)) {
        group[r].push_back(q);
    }
    long long x0 = q * q * r;
    for (; i < (int)primes.size() && x0 * primes[i] <= N; ++ i) {
        int rr = r;
        int qq = q;
        long long x = x0;
        for (int k = 1; (x *= primes[i]) <= N; ++ k) {
            if (k & 1) {
                rr *= primes[i];
                generate(i + 1, qq, rr);
            } else {
                rr /= primes[i];
                qq *= primes[i];
                generate(i + 1, qq, rr);
            }
        }
    }
}

int main()
{
    divided.set(0);
    divided.set(1);
    for (int d = 2; d < N; ++ d) {
        if (!divided.test(d)) {
            primes.push_back(d);
        }
        for (int i = 0; i < (int)primes.size() && (long long)d * primes[i] < N; ++ i) {
            divided.set(d * primes[i]);
            if (d % primes[i] == 0) {
                break;
            }
        }
    }
    generate(0, 1, 1);
    long long result = 0;
    for (const auto &g : group) {
        const std::vector <int> &v = g.second;
        for (int i = 0; i < (int)v.size(); ++ i) {
            for (int j = 0; j < i; ++ j) {
                int c = g.first * v[i] * v[j] - 1;
                if (!divided.test(c)) {
                    int a = g.first * v[i] * v[i] - 1;
                    int b = g.first * v[j] * v[j] - 1;
                    result += a + b + c;
                }
            }
        }
    }
    printf("%lld\n", result);
    return 0;
}
