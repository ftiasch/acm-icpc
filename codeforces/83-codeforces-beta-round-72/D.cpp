// Codeforces Beta Round #72
// Problem D -- Numbers
#include <cstdio>
#include <cstring>
#include <vector>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

bool is_prime(int n) {
    if (n <= 1) {
        return false;
    }
    for (int i = 2; i * i <= n; ++ i) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

std::vector <int> primes;

const int N = 5000;
const int M = 5000;

int memory[N][M];

int count(int n, int i) {
    if (n == 0) {
        return 0;
    }
    if (i < 0) {
        return n;
    }
    if (n < N && memory[n][i] != -1) {
        return memory[n][i];
    }
    int ret = count(n, i - 1) - count(n / primes[i], i - 1);
    if (n < N) {
        memory[n][i] = ret;
    }
    return ret;
}

int solve(int n, int m) {
    if (!is_prime(m)) {
        return 0;
    }
    primes.clear();
    for (int i = 2; i < m && i <= n / m; ++ i) {
        if (is_prime(i)) {
            primes.push_back(i);
        }
    }
    return count(n / m, (int)primes.size() - 1);
}

int main() {
    memset(memory, -1, sizeof(memory));
    int a, b, p;
    scanf("%d%d%d", &a, &b, &p);
    printf("%d\n", solve(b, p) - solve(a - 1, p));
    return 0;
}
