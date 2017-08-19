#include <cstdio>
#include <cstring>
#include <map>
#include <vector>

const int N = 300;
const int MOD = (int)1e9 + 7;

int inverse(int a)
{
    return a == 1 ? 1 : (long long)(MOD - MOD / a) * inverse(MOD % a) % MOD;
}

void update(int& x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

int binom[N + 1][N + 1], fact[N + 1], inv_fact[N + 1], dp[N + 1];

int main()
{
#ifdef LOCAL_JUDGE
    freopen("C.in", "r", stdin);
#endif
    for (int i = 0; i <= N; ++ i) {
        binom[i][0] = 1;
        for (int j = 1; j <= i; ++ j) {
            binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
        }
    }
    fact[0] = inv_fact[0] = 1;
    for (int i = 1; i <= N; ++ i) {
        fact[i] = (long long)fact[i - 1] * i % MOD;
        inv_fact[i] = inverse(fact[i]);
    }
    int n;
    while (scanf("%d", &n) == 1) {
        std::map<std::vector<int>, int> m;
        for (int i = 0, a; i < n; ++ i) {
            scanf("%d", &a);
            std::vector<int> v;
            for (int d = 2; d * d <= a; ++ d) {
                if (a % d == 0) {
                    int exp = 0;
                    while (a % d == 0) {
                        a /= d;
                        exp ^= 1;
                    }
                    if (exp) {
                        v.push_back(d);
                    }
                }
            }
            if (a > 1) {
                v.push_back(a);
            }
            m[v] ++;
        }
        memset(dp, 0, sizeof(dp));
        dp[0] = 1;
        for (auto iterator : m) {
            int cnt = iterator.second;
            for (int i = n; i >= 0; -- i) {
                int tmp = 0;
                for (int j = 1; j <= cnt && j <= i; ++ j) {
                    update(tmp, (long long)dp[i - j] * fact[cnt] % MOD * binom[cnt - 1][j - 1] % MOD * inv_fact[j] % MOD);
                }
                dp[i] = tmp;
            }
        }
        int result = 0;
        for (int i = 0; i <= n; ++ i) {
            update(result, (n - i & 1 ? MOD - 1LL : 1LL) * fact[i] % MOD * dp[i] % MOD);
        }
        printf("%d\n", result);
    }
}
