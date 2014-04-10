#include <algorithm>
#include <cstdio>
#include <cstring>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

typedef long long Long;

const Long MAX = 1000000000000000000LL;
const int MOD = 20120427;
const int PRIMES[4] = {2, 3, 5, 7};

std::vector <Long> products;
int transform[66061][10];

void generate(int i, Long product) {
    if (i < 4) {
        generate(i + 1, product);
        while (MAX / PRIMES[i] >= product) {
            generate(i + 1, product *= PRIMES[i]);
        }
    } else {
        products.push_back(product);
    }
}

int digits[20], value[10][20];
int memory[2][20][2][66063];

int go(bool less, bool first, int length, bool concern, int pid) {
    if (pid == -1) {
        return 0;
    }
    if (length) {
        int& buffer = memory[first][length][concern][pid];
        if (less && ~buffer) {
            return buffer;
        }
        Long result = 0;
        for (int now = less, d = less ? 9 : digits[length - 1]; d >= first; -- d) {
            result += go(now, false, length - 1, concern, transform[pid][d]);
            if (!concern) {
                result += (long long)value[d][length] * go(now, false, length - 1, true, transform[pid][d]) % MOD;
            }
            now = true;
        }
        result %= MOD;
        if (less) {
            buffer = result;
        }
        return result;
    } else {
        return less && (pid == 0 || pid == 2) && concern;
    }
}

int solve(Long n, int pid) {
    int m = 0;
    while (n) {
        digits[m ++] = n % 10;
        n /= 10;
    }
    Long result = 0;
    for (int i = 1; i <= m; ++ i) {
        result += go(i < m, true, i, false, pid);
    }
    return result % MOD;
}

int main() {
    products.push_back(0);
    products.push_back(0);
    generate(0, 1LL);
    std::sort(ALL(products));
    memset(transform, -1, sizeof(transform));
    for (int i = 0; i < 2; ++ i) {
        for (int d = 0; d < 10; ++ d) {
            transform[i][d] = i;
        }
    }
    transform[1][0] = 0;
    for (int i = 2; i < (int)products.size(); ++ i) {
        for (int d = 1; d < 10; ++ d) {
            Long p = products[i];
            if (p % d == 0) {
                transform[i][d] = std::lower_bound(ALL(products), p / d) - products.begin();
            }
        }
    }
    memset(value, 0, sizeof(value));
    for (int d = 1; d < 10; ++ d) {
        value[d][1] = d;
        for (int i = 2; i < 20; ++ i) {
            value[d][i] = value[d][i - 1] * 10 % MOD;
        }
    }
    memset(memory, -1, sizeof(memory));
    int tests;
    scanf("%d", &tests);
    while (tests --) {
        Long a, b, p;
        scanf("%lld%lld%lld", &a, &b, &p);
        Long pp = 1;
        if (p) {
            for (int i = 0; i < 4; ++ i) {
                while (p % PRIMES[i] == 0) {
                    p /= PRIMES[i];
                    pp *= PRIMES[i];
                }
            }
            pp = std::lower_bound(ALL(products), pp) - products.begin();
        }
        printf("%d\n", p > 1 ? 0 : (solve(b + 1, pp) + MOD - solve(a, pp)) % MOD);
    }
    return 0;
}
