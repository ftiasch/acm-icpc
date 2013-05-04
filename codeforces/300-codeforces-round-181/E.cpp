// Codeforces Round #181
// Problem E -- Empire Strikes Back
#include <cstdio>
#include <cstring>
#include <vector>
#include <iostream>
#include <algorithm>

const int N = 10000000;

int divisor[N + 1];
std::vector <int> primes;

long long count[N + 1];

long long test(long long n, int p) {
    long long ret = 0;
    for (long long i = p; i <= n; i *= p) {
        ret += n / i;
    }
    return ret;
}

int main() {
    memset(divisor, -1, sizeof(divisor));
    for (int i = 2; i <= N; ++ i) {
        if (divisor[i] == -1) {
            primes.push_back(i);
        }
        for (int k = 0; k < (int)primes.size() && i * primes[k] <= N; ++ k) {
            divisor[i * primes[k]] = primes[k];
            if (i % primes[k] == 0) {
                break;
            }
        }
    }
    memset(count, 0, sizeof(count));
    int m;
    scanf("%d", &m);
    while (m --) {
        int a;
        scanf("%d", &a);
        count[a] ++;
    }
    for (int i = N; i >= 2; -- i) {
        count[i - 1] += count[i];
    }
    for (int i = N; i >= 2; -- i) {
        if (divisor[i] != -1) {
            count[divisor[i]] += count[i];
            count[i / divisor[i]] += count[i];
            count[i] = 0;
        }
    }
    long long answer = 1;
    for (int i = 2; i <= N; ++ i) {
        if (divisor[i] == -1) {
            long long low = 0;
            long long high = 1000000000000000000LL;
            while (low < high) {
                long long middle = low + high >> 1;
                if (test(middle, i) >= count[i]) {
                    high = middle;
                } else {
                    low = middle + 1;
                }
            }
            answer = std::max(answer, high);
        }
    }
    std::cout << answer << std::endl;
    return 0;
}
