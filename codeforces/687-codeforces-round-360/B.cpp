#include <cstdio>
#include <vector>

int main()
{
    int n, mod;
    scanf("%d%d", &n, &mod);
    int m = mod;
    std::vector<int> c(n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", &c[i]);
        m = std::max(m, c[i]);
    }
    std::vector<int> primes, div(m + 1);
    for (int d = 2; d <= m; ++ d) {
        if (!div[d]) {
            div[d] = 1;
            primes.push_back(d);
        }
        for (auto&& p : primes) {
            if ((long long)d * p > m) {
                break;
            }
            div[d * p] = p;
            if (d % p == 0) {
                div[d * p] = div[d] * p;
                break;
            }
        }
    }
    std::vector<int> mask(m + 1);
    mask[mod] |= 1;
    for (int i = 0; i < n; ++ i) {
        mask[c[i]] |= 2;
    }
    for (int i = m; i >= 2; -- i) {
        mask[div[i]] |= mask[i];
        mask[i / div[i]] |= mask[i];
    }
    bool ok = true;
    for (auto&& p : primes) {
        for (int pk = p; ; pk *= p) {
            ok &= mask[pk] != 1;
            if ((long long)pk * p > m) {
                break;
            }
        }
    }
    puts(ok ? "Yes" : "No");
}
