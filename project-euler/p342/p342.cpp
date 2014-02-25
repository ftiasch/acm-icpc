#include <iostream>
#include <vector>

const int N = 100000;

int min_div[N], count[N];
std::vector <int> primes;

typedef long long Long;

void update(int n, int d)
{
    while (n > 1) {
        int id = min_div[n];
        count[id] += d;
        n /= primes[id];
    }
}

Long power(int a, int n)
{
    Long result = 1;
    while (n --) {
        result *= a;
    }
    return result;
}

Long search(int i, Long max)
{
    if (i < 0) {
        return 1;
    } else {
        Long result = 0;
        int r = (1 - count[i]) * 2 % 3;
        if (r < 0) {
            r += 3;
        }
        Long now = power(primes[i], r == 0 ? 3 : r);
        if (now <= max) {
            Long step = power(primes[i], 3);
            update(primes[i] - 1, 1);
            while (true) {
                result += now * search(i - 1, max / now);
                if (now > max / step) {
                    break;
                }
                now *= step;
            }
            update(primes[i] - 1, -1);
        }
        if (count[i] % 3 == 0) {
            result += search(i - 1, max);
        }
        return result;
    }
}

int main()
{
    memset(min_div, -1, sizeof(min_div));
    for (int p = 2; p < N; ++ p) {
        if (min_div[p] == -1) {
            min_div[p] = primes.size();
            primes.push_back(p);
        }
        for (int i = 0; i < (int)primes.size() && p * primes[i] < N; ++ i) {
            min_div[p * primes[i]] = i;
            if (p % primes[i] == 0) {
                break;
            }
        }
    }
    memset(count, 0, sizeof(count));
    std::cout << search(primes.size() - 1, (long long)N * N - 1) - 1 << std::endl;
}
