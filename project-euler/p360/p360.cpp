#include <algorithm>
#include <iostream>
#include <unordered_map>
#include <vector>

typedef long long Long;

const int R = 9765625; // 5^10
const int M = R << 1;

int min_div[M + 1];

void factor(std::unordered_map <int, int> &factors, int n)
{
    while (n > 1) {
        int p = min_div[n];
        factors[p] ++;
        n /= p;
    }
}

Long count(int x)
{
    if (x == R) {
        return 1;
    }
    std::unordered_map <int, int> factors;
    factor(factors, R - x);
    factor(factors, R + x);
    factors.erase(2);
    Long result = 4;
    for (auto &iterator : factors) {
        if ((iterator.first & 3) == 1) {
            result *= iterator.second + 1;
        } else if (iterator.second & 1) {
            return 0;
        }
    }
    return result;
}

int main()
{
    memset(min_div, -1, sizeof(min_div));
    std::vector <int> primes;
    for (int p = 2; p <= M; ++ p) {
        if (min_div[p] == -1) {
            primes.push_back(p);
            min_div[p] = p;
        }
        for (int i = 0; i < (int)primes.size() && p * primes[i] <= M; ++ i) {
            min_div[p * primes[i]] = primes[i];
            if (p % primes[i] == 0) {
                break;
            }
        }
    }
    Long result = 0;
    for (int x = 1; x <= R; ++ x) {
        result += 2 * x * count(x);
    }
    std::cout << 3 * 1024 * result << std::endl;
}
