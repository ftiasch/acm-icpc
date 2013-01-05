// Codeforces Round #157
// Problem C -- Little Elephant and LCM
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

using std::vector;
using std::lower_bound;

const int N = 100000;
const int INF = 1000000000;
const int MOD = 1000000000 + 7;

int n, a[N];
vector <int> divisors[N + 1];

int pow(int a, int n) {
    int ret = 1;
    while (n) {
        if (n & 1) {
            ret = (long long)ret * a % MOD;
        }
        n >>= 1;
        a = (long long)a * a % MOD;
    }
    return ret;
}

int inverse(int a) {
    return a == 1 ? 1 : (long long)(MOD - MOD / a) * inverse(MOD % a) % MOD;
}

int main() {
    for (int d = 1; d <= N; ++ d) {
        for (int i = d; i <= N; i += d) {
            divisors[i].push_back(d);
        }
    }
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    std::sort(a, a + n);
    int answer = 0;
    for (int lcm = 1; lcm <= N; ++ lcm) {
        vector <int> &divisor = divisors[lcm];
        int m = divisor.size();
        int way = 1;
        for (int j = 0; j + 1 < m; ++ j) {
            int size = lower_bound(a, a + n, divisor[j + 1]) - lower_bound(a, a + n, divisor[j]);
            way = (long long)way * pow(j + 1, size) % MOD;
        }
        int size = a + n - lower_bound(a, a + n, lcm);
        if (size) {
            way = (long long)way * (pow(m, size) + MOD - pow(m - 1, size)) % MOD;
            (answer += way) %= MOD;
        }
    }
    printf("%d\n", answer);
    return 0;
}
