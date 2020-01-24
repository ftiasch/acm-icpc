#include <cassert>
#include <cstdio>
#include <functional>
#include <vector>

const int MOD = 1000000993;

void update(int& x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

struct Summary
{
    int product, sum;
};

int get_id(int l, int r)
{
    return l + r | l != r;
}

int inverse(int a)
{
    return a == 1 ? 1 : (long long)(MOD - MOD / a) * inverse(MOD % a) % MOD;
}

int solve(int n)
{
    std::vector<int> min_div(n + 1);
    std::vector<int> primes;
    primes.push_back(1);
    for (int d = 2; d <= n; ++ d) {
        if (!min_div[d]) {
            min_div[d] = d;
            primes.push_back(d);
        }
        for (auto&& p : primes) {
            if (p == 1) {
                continue;
            }
            if ((long long)d * p > n) {
                break;
            }
            min_div[d * p] = p;
            if (d % p == 0) {
                break;
            }
        }
    }
    primes.push_back(n + 1);

    auto m = primes.size();
    std::vector<int> position(n + 1);
    for (int i = 0; i < m; ++ i) {
        position[primes[i]] = i;
    }

    std::vector<Summary> tree(m << 1);

    std::function<void(int, int)> build = [&](int l, int r)
    {
        if (l < r) {
            int m = l + r >> 1;
            build(l, m);
            build(m + 1, r);
        }
        int id = get_id(l, r);
        tree[id].product = 1;
        tree[id].sum = primes[r + 1] - primes[l];
    };

    build(0, primes.size() - 2);

    std::function<void(int, int, int, int)> modify = [&](int l, int r, int k, int md)
    {
        int id = get_id(l, r);
        if (l == r) {
            tree[id].product = (long long)tree[id].product * md % MOD;
            tree[id].sum = (long long)tree[id].sum * md % MOD;
        } else {
            int m = l + r >> 1;
            if (k <= m) {
                modify(l, m, k, md);
            } else {
                modify(m + 1, r, k, md);
            }
            int lt = get_id(l, m);
            int rt = get_id(m + 1, r);
            tree[id].product = (long long)tree[lt].product * tree[rt].product % MOD;
            tree[id].sum = tree[lt].sum;
            update(tree[id].sum, (long long)tree[lt].product * tree[rt].sum % MOD);
        }
    };

    int result = n;
    for (int r = 0; r < n; ++ r) {
        for (int _ = 0; _ < 2; ++ _) {
            int x = _ ? (n - r) : (r + 1);
            while (x > 1) {
                int p = min_div[x];
                int p_idx = position[p];
                int d = _ ? p : inverse(p);
                modify(0, primes.size() - 2, p_idx, d);
                x /= p;
            }
        }
        update(result, tree[get_id(0, primes.size() - 2)].sum);
    }
    return result;
}

int main()
{
    printf("%d\n", solve(11));
    printf("%d\n", solve(1111));
    printf("%d\n", solve(111111));
    printf("%d\n", solve(11111111));
}
