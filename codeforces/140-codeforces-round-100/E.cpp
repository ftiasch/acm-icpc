// Codeforces Round #100
// Problem E -- New Year Garland
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

const int N = 1000000;
const int L = 5000;

int n, m, p, l[N];

int alt_ways[L + 1][L + 1], perm[L + 1], ways[L + 1], fact[L + 1];

int main() {
    scanf("%d%d%d", &n, &m, &p);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", l + i);
    }
    memset(alt_ways, 0, sizeof(alt_ways));
    alt_ways[1][1] = 1;
    for (int i = 1; i < L; ++ i) {
        for (int j = 1; j <= i; ++ j) {
            (alt_ways[i + 1][j] += (long long)alt_ways[i][j] * (j - 1) % p) %= p;
            (alt_ways[i + 1][j + 1] += alt_ways[i][j]) %= p;
        }
    }
    memset(perm, 0, sizeof(perm));
    fact[0] = perm[0] = 1 % p;
    for (int i = 0; i < L; ++ i) {
        fact[i + 1] = (long long)fact[i] * (i + 1) % p;
        perm[i + 1] = (long long)perm[i] * (m - i) % p;
    }
    for (int i = 1; i <= l[0]; ++ i) {
        ways[i] = alt_ways[l[0]][i];
    }
    for (int i = 1; i < n; ++ i) {
        int total = 0;
        for (int j = 1; j <= l[i - 1]; ++ j) {
            (total += (long long)ways[j] * perm[j] % p) %= p;
        }
        for (int j = 1; j <= l[i]; ++ j) {
            ways[j] = (long long)(total + p - (long long)(j <= l[i - 1] ? ways[j] : 0) * fact[j] % p) * alt_ways[l[i]][j] % p;
        }
    }
    int answer = 0;
    for (int i = 1; i <= l[n - 1]; ++ i) {
        (answer += (long long)ways[i] * perm[i] % p) %= p;
    }
    printf("%d\n", answer);
    return 0;
}
