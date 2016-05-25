#include <cstdio>
#include <complex>
#include <functional>
#include <vector>

typedef long long Long;
typedef std::complex<double> Complex;

const double PI = acos(-1.);

const int MOD = 1000000123;
const int N = 1 << 17;

void update(int& x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

std::vector<int> cov(const std::vector<int>& a_, const std::vector<int>& b_)
{
    auto FFT = [](Complex* p, int n)
    {
        std::vector<Complex> w(n);
        for (int i = 0; i < n; ++ i) {
            w[i] = {cos(2 * i * PI / n), sin(2 * i * PI / n)};
        }
        for (int i = 1, j = 0; i < n - 1; ++ i) {
            for (int s = n; j ^= s >>= 1, ~j & s;);
            if (i < j) {
                swap(p[i], p[j]);
            }
        }
        for (int d = 0; 1 << d < n; ++ d) {
            int m = 1 << d, m2 = m * 2, rm = n >> d + 1;
            for (int i = 0; i < n; i += m2) {
                for (int j = 0; j < m; ++ j) {
                    Complex &p1 = p[i + j + m], &p2 = p[i + j];
                    Complex t = w[rm * j] * p1;
                    p1 = p2 - t;
                    p2 = p2 + t;
                }
            }
        }
    };

    int n = a_.size();
    std::vector<Complex> a, b, c(n), d(n);
    for (int i = 0; i < n; ++ i) {
        a.emplace_back(a_[i] >> 16, a_[i] & 65535);
        b.emplace_back(b_[i] >> 16, b_[i] & 65535);
    }
    FFT(a.data(), n);
    FFT(b.data(), n);
    using std::conj;
    for (int i = 0; i < n; ++ i) {
        int j = (n - i) % n;
        auto da = (a[i] - conj(a[j])) * Complex(0, -.5);
        auto db = (a[i] + conj(a[j])) * Complex(.5, 0);
        auto dc = (b[i] - conj(b[j])) * Complex(0, -.5);
        auto dd = (b[i] + conj(b[j])) * Complex(.5, 0);
        c[j] = da * dd + da * dc * Complex(0, 1);
        d[j] = db * dd + db * dc * Complex(0, 1);
    }
    FFT(c.data(), n);
    FFT(d.data(), n);
    std::vector<int> result;
    for (int i = 0; i < n; ++ i) {
        auto da = (Long)(c[i].imag() / n + 0.5) % MOD;
        auto db = (Long)(c[i].real() / n + 0.5) % MOD;
        auto dc = (Long)(d[i].imag() / n + 0.5) % MOD;
        auto dd = (Long)(d[i].real() / n + 0.5) % MOD;
        result.push_back(((dd << 32) + (db + dc << 16) + da) % MOD);
    }
    return result;
}

int fact[N + 1], inv_fact[N + 1];

int power(int a, int n)
{
    int result = 1;
    while (n) {
        if (n & 1) {
            result = (long long)result * a % MOD;
        }
        a = (long long)a * a % MOD;
        n >>= 1;
    }
    return result;
}

int Q(int n)
{
    fact[0] = inv_fact[0] = 1;
    for (int i = 1; i <= n; ++ i) {
        fact[i] = (long long)fact[i - 1] * power(i, n) % MOD;
        inv_fact[i] = power(fact[i], MOD - 2);
    }
    int result = 0;
    for (int k = 1; k <= n; ++ k) {
        int m = n / k;
        int l = 1;
        while (m >= l) {
            l <<= 1;
        }
        std::vector<int> ways(l);

        std::function<void(int, int)> divide = [&](int l, int r)
        {
            if (m < l) {
                return;
            }
            if (r - l == 1) {
                ways[l] = (Long)ways[l] * fact[l * k] % MOD;
            } else {
                int m = l + r >> 1;
                divide(l, m);
                int len = r - l;
                std::vector<int> a(len), b(len);
                for (int i = l; i < m; ++ i) {
                    a[i - l] = (Long)ways[i] * inv_fact[i * k] % MOD;
                }
                for (int i = 1; i < len; ++ i) {
                    b[i] = i & 1 ? inv_fact[i * k] : MOD - inv_fact[i * k];
                }
                auto c = cov(a, b);
                for (int i = m; i < r; ++ i) {
                    update(ways[i], c[i - l]);
                }
                divide(m, r);
            }
        };

        ways[0] = 1;
        divide(0, l);
        int partial_result = 0;
        if (int rem = n % k) {
            for (int i = 0; i <= m; ++ i) {
                int tmp = (Long)ways[m - i] * inv_fact[rem + i * k] % MOD * inv_fact[n - rem - i * k] % MOD;
                update(partial_result, i & 1 ? MOD - tmp : tmp);
            }
            partial_result = (long long)partial_result * fact[n] % MOD;
        } else {
            partial_result = ways[m];
        }
        update(result, partial_result);
    }
    return result;
}

int main()
{
    printf("%d\n", Q(5));
    printf("%d\n", Q(50));
    printf("%d\n", Q(50000));
}
