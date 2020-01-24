#include <cstdio>
#include <vector>

std::vector<int> get_primes(int n) {
    std::vector<bool> is_prime(n + 1, true);
    std::vector<int> primes;
    for (int d = 2; d <= n; ++ d) {
        if (is_prime[d]) {
            primes.push_back(d);
        }
        for (auto&& p : primes) {
            if (d * p > n) {
                break;
            }
            is_prime[d * p] = false;
            if (d % p == 0) {
                break;
            }
        }
    }
    return primes;
}

const int MOD = 10007;

auto primes = get_primes(200000000);

int pow(int a, int n)
{
    a %= MOD;
    int result = 1;
    while (n) {
        if (n & 1) {
            result = result * a % MOD;
        }
        a = a * a % MOD;
        n >>= 1;
    }
    return result;
}

void work(int n, int k)
{
    std::vector<int> s(n + 1), s_2(n + 1);
    for (int i = 1; i <= n; ++ i) {
        s[i] = pow(primes[i - 1], i);
        s_2[i] = s[i] + s[i / 10000 + 1];
    }
    std::vector<int> cnt(MOD * 2);
    int p = -1, p_cnt = 0;
    long long result = 0;
    for (int i = 1; i <= n; ++ i) {
        if (i > k) {
            cnt[s_2[i - k]] --;
            if (s_2[i - k] <= p) {
                p_cnt --;
            }
        }
        cnt[s_2[i]] ++;
        if (s_2[i] <= p) {
            p_cnt ++;
        }
        if (i >= k) {
            while (p_cnt < k / 2) {
                p_cnt += cnt[++ p];
            }
            while (p_cnt - cnt[p] >= k / 2) {
                p_cnt -= cnt[p --];
            }
            int q = p;
            int q_cnt = p_cnt;
            while (q_cnt < k / 2 + 1) {
                q_cnt += cnt[++ q];
            }
            result += p + q;
        }
    }
    printf("%lld.%c\n", result >> 1, result & 1 ? '5' : '0');
}

int main()
{
    work(100, 10);
    work(100000, 10000);
    work(10000000, 100000);
}
