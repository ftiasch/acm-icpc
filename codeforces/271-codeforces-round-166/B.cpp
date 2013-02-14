// Codeforces Round #166
// Problem B -- Prime Matrix
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <algorithm>

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

const int N = 500;

int a[N][N];

int main() {
    {
        int p = 100000;
        while (!is_prime(p)) {
            p ++;
        }
        for (int i = 2; i <= p; ++ i) {
            if (is_prime(i)) {
                primes.push_back(i);
            }
        }
    }
    int n, m;
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            scanf("%d", &a[i][j]);
        }
    }
    int answer = INT_MAX;
    for (int i = 0; i < n; ++ i) {
        int cost = 0;
        for (int j = 0; j < m; ++ j) {
            cost += *std::lower_bound(primes.begin(), primes.end(), a[i][j]) - a[i][j];
        }
        answer = std::min(answer, cost);
    }
    for (int j = 0; j < m; ++ j) {
        int cost = 0;
        for (int i = 0; i < n; ++ i) {
            cost += *std::lower_bound(primes.begin(), primes.end(), a[i][j]) - a[i][j];
        }
        answer = std::min(answer, cost);
    }
    printf("%d\n", answer);
    return 0;
}
