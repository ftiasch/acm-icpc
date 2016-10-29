#include <cstdio>
#include <functional>
#include <utility>
#include <vector>

const int MOD = (int)1e9 + 7;

bool is_prime(int n)
{
    for (int i = 2; i * i <= n; ++ i) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

int inverse(int a)
{
    return a == 1 ? 1 : 1LL * (MOD - MOD / a) * inverse(MOD % a) % MOD;
}

void update(int& x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

std::vector<int> factor(int n)
{
    std::vector<int> result;
    for (int p = 2; p <= n; ++ p) {
        if (is_prime(p)) {
            int e = 0;
            int n_ = n;
            while (n_) {
                e += (n_ /= p);
            }
            result.push_back(e);
        }
    }
    return result;
}

int solve(const std::vector<int>& exponents, int m)
{
    int max_e = std::max(m, *std::max_element(exponents.begin(), exponents.end()));
    std::vector<int> factorial(max_e + 1);
    factorial[0] = 1;
    for (int i = 1; i <= max_e; ++ i) {
        factorial[i] = 1LL * factorial[i - 1] * i % MOD;
    }
    int result = 0;
    std::vector<int> ways(max_e + 1);
    ways[0] = 1;
    std::function<void(int, std::vector<int>)> gen = [&](int left, std::vector<int> partition)
    {
        if (left) {
            int start = 1;
            if (!partition.empty()) {
                start = partition.back();
            }
            for (; start <= left; ++ start) {
                partition.push_back(start);
                for (int i = start; i <= max_e; ++ i) {
                    update(ways[i], ways[i - start]);
                }
                gen(left - start, partition);
                for (int i = max_e; i >= start; -- i) {
                    update(ways[i], MOD - ways[i - start]);
                }
                partition.pop_back();
            }
        } else {
            int coefficient = factorial.at(m);
            for (auto&& p : partition) {
                coefficient = 1LL * coefficient * inverse(factorial.at(p)) % MOD * factorial.at(p - 1) % MOD;
                if (~p & 1) {
                    coefficient = coefficient * (MOD - 1LL) % MOD;
                }
            }
            for (int i = 0; i < (int)partition.size(); ) {
                int j = i;
                while (j < (int)partition.size() && partition[i] == partition[j]) {
                    j ++;
                }
                coefficient = 1LL * coefficient * inverse(factorial.at(j - i)) % MOD;
                i = j;
            }
            for (auto&& e : exponents) {
                coefficient = 1LL * coefficient * ways[e] % MOD;
            }
            update(result, coefficient);
        }
    };
    gen(m, {});
    return 1LL * result * inverse(factorial.at(m)) % MOD;
}

int main()
{
    printf("%d\n", solve({4, 2}, 4));
    printf("%d\n", solve(factor(100), 10));
    printf("%d\n", solve(factor(10000), 30));
}
