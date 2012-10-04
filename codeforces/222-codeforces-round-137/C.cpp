// Codeforces Round #137
// Problem C -- Reducing Fractions
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int MAXN = 100000;
const int MAXM = 10000000;

int n, m, a[MAXN], b[MAXN];
int divisor[MAXM + 1];
int a_power[MAXM + 1], b_power[MAXM + 1], d_power[MAXM + 1];
vector <int> primes;

void factorize(int n, int power[]) {
    while (n > 1) {
        power[divisor[n]] ++;
        n /= divisor[n];
    }
}

void reduce(int &n, int power[]) {
    int result = n;
    while (n > 1) {
        if (power[divisor[n]] > 0) {
            power[divisor[n]] --;
            result /= divisor[n];
        }
        n /= divisor[n];
    }
    n = result;
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    for (int i = 0; i < m; ++ i) {
        scanf("%d", b + i);
    }
    memset(divisor, -1, sizeof(divisor));
    for (int i = 2; i <= MAXM; ++ i) {
        if (divisor[i] == -1) {
            divisor[i] = i;
            primes.push_back(i);
        }
        for (int k = 0; k < (int)primes.size() && i * primes[k] <= MAXM; ++ k) {
            divisor[i * primes[k]] = primes[k];
            if (i % primes[k] == 0) {
                break;
            }
        }
    }
    memset(a_power, 0, sizeof(a_power));
    for (int i = 0; i < n; ++ i) {
        factorize(a[i], a_power);
    }
    memset(b_power, 0, sizeof(b_power));
    for (int i = 0; i < m; ++ i) {
        factorize(b[i], b_power);
    }
    for (int i = 0; i <= MAXM; ++ i) {
        d_power[i] = min(a_power[i], b_power[i]);
    }
    for (int i = 0; i < n; ++ i) {
        reduce(a[i], d_power);
    }
    for (int i = 0; i <= MAXM; ++ i) {
        d_power[i] = min(a_power[i], b_power[i]);
    }
    for (int i = 0; i < m; ++ i) {
        reduce(b[i], d_power);
    }
    printf("%d %d\n", n, m);
    for (int i = 0; i < n; ++ i) {
        printf("%d%c", a[i], i == n - 1? '\n': ' ');
    }
    for (int i = 0; i < m; ++ i) {
        printf("%d%c", b[i], i == m - 1? '\n': ' ');
    }
    return 0;
}
