#include <algorithm>
#include <cstdio>
#include <cstring>
#include <climits>
#include <complex>
#include <vector>
#include <queue>

const int MOD = (int)1e9 + 7;

bool check(const std::vector<int>& distance, int r)
{
    int m = distance.size();
    return distance[r % m] <= r;
}

void update(int& x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

int power(int a, int n)
{
    int result = 1;
    while (n) {
        if (n & 1) {
            result = 1LL * result * a % MOD;
        }
        a = 1LL * a * a % MOD;
        n >>= 1;
    }
    return result;
}

std::vector<int> multiply(const std::vector<int>& a_, const std::vector<int>& b_)
{
    static const double PI = acos(-1.);

    using Complex = std::complex<double>;
    using Long    = long long;

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

std::vector<int> convolution(int n, const std::vector<int>& a, const std::vector<int>& b)
{
    auto c = multiply(a, b);
    for (int i = n; i < (int)a.size(); ++ i) {
        update(c[i % n] , c[i]);
        c[i] = 0;
    }
    return c;
}

std::vector<int> solve(int n, int m, int p)
{
    int length = 1;
    while (m - 1 << 1 >= length) {
        length <<= 1;
    }
    std::vector<int> result(length), a(length);
    result[0] = 1;
    a[0] = 100 - p;
    a[1] = p;
    while (n) {
        if (n & 1) {
            result = convolution(m, result, a);
        }
        a = convolution(m, a, a);
        n >>= 1;
    }
    return result;
}

int main()
{
#ifdef LOCAL_JUDGE
    freopen("I.in", "r", stdin);
#endif
    int T;
    scanf("%d", &T);
    for (int t = 1; t <= T; ++ t) {
        int N, n, p;
        scanf("%d%d%d", &N, &n, &p);
        std::vector<int> c(n);
        for (int i = 0; i < n; ++ i) {
            scanf("%d", &c[i]);
        }
        std::sort(c.begin(), c.end());
        int M = c[0];
        std::vector<int> distance(M, INT_MAX);
        distance[0] = 0;
        {
            std::priority_queue<std::pair<int, int>> queue;
            queue.emplace(0, 0);
            while (!queue.empty()) {
                auto r = queue.top();
                queue.pop();
                int u = r.second;
                if (-r.first == distance[u]) {
                    for (int i = 1; i < n; ++ i) {
                        int v = (u + c[i]) % M;
                        if (distance[v] > distance[u] + c[i]) {
                            distance[v] = distance[u] + c[i];
                            queue.emplace(-distance[v], v);
                        }
                    }
                }
            }
        }
        int bound = 0;
        if (n > 1) {
            bound = (10000 / M + 1) * M;
        }
        int result = 0;
        std::vector<int> cost(M);
        int next = 0;
        for (int i = 0; i < M; ++ i) {
            while (next < i || distance[next % M] == INT_MAX) {
                next ++;
            }
            cost[i] = next - i;
        }
        auto count = solve(N, M, p);
        for (int i = 0; i < M; ++ i) {
            update(result, 1LL * cost[i] * count[i] % MOD);
        }
        next = 0;
        int binom = 1;
        for (int i = 0; i <= N && i < bound; ++ i) {
            while (next < i || distance[next % M] > next) {
                next ++;
            }
            int coefficient = 1LL * binom * power(p, i) % MOD * power(100 - p, N - i) % MOD;
            update(result, 1LL * coefficient * (next - i + MOD - cost[i % M]) % MOD);
            binom = 1LL * binom * (N - i) % MOD * power(i + 1, MOD - 2) % MOD;
        }
        printf("Case #%d: %d\n", t, result);
    }
}
