#include <algorithm>
#include <cstdio>
#include <iostream>
#include <map>
#include <utility>
#include <vector>

const int N = 100000;
const int W = 1e8;

typedef unsigned long long Long;

bool is_prime(int n)
{
    for (int i = 2; i * i <= n; ++ i) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

Long next()
{
    static Long s = 1;
    return s *= 3;
}

std::map<int, Long> cache;

Long get_mask(int p)
{
    Long& ref = cache[p];
    if (!ref) {
        ref = next();
    }
    return ref;
}

int n;
Long mask[N];
std::vector<int> primes;
std::vector<std::pair<int, int>> tree[N];

Long factor(int n)
{
    Long result = 0;
    for (int i = 0; i < (int)primes.size() && primes[i] * primes[i] <= n; ++ i) {
        int exp = 0;
        while (n % primes[i] == 0) {
            exp ++;
            n /= primes[i];
        }
        if (exp & 1) {
            result ^= get_mask(primes[i]);
        }
    }
    if (n > 1) {
        result ^= get_mask(n);
    }
    return result;
}

void prepare(int p, int u)
{
    for (auto&& iterator : tree[u]) {
        int v = iterator.first;
        if (v != p) {
            mask[v] = mask[u] ^ factor(iterator.second);
            prepare(u, v);
        }
    }
}

int main()
{
    for (int p = 2; p * p <= W; ++ p) {
        if (is_prime(p)) {
            primes.push_back(p);
            cache[p] = next();
        }
    }
    scanf("%d", &n);
    for (int _ = 0; _ < n - 1; ++ _) {
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        a --;
        b --;
        tree[a].emplace_back(b, c);
        tree[b].emplace_back(a, c);
    }
    mask[0] = 0;
    prepare(-1, 0);
    std::sort(mask, mask + n);
    long long result = 0;
    for (int i = 0; i < n; ) {
        int j = i;
        while (j < n && mask[i] == mask[j]) {
            j ++;
        }
        long long s = j - i;
        i = j;
        result += s * (s - 1);
    }
    std::cout << result << std::endl;
}
