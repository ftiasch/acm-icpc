#include <algorithm>
#include <cstdio>
#include <cstring>
#include <functional>
#include <iostream>
#include <map>
#include <set>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

typedef long long Long;

const Long BOUND = (Long)1e16 + 1;

std::map<std::vector<int>, Long> cache;

Long count(std::vector<int>);

Long dfs(const std::vector<int>& upper_bounds, std::vector<int> now)
{
    int i = now.size();
    if (i < (int)upper_bounds.size()) {
        Long result = 0;
        int ub = upper_bounds[i];
        // if (i) {
        //     ub = std::min(ub, now[i - 1]);
        // }
        for (int j = 0; j <= ub; ++ j) {
            now.push_back(j);
            result += dfs(upper_bounds, now);
            if (result > BOUND) {
                return BOUND;
            }
            now.pop_back();
        }
        return result;
    } else {
        return upper_bounds == now ? 0 : count(now);
    }
}

Long count(std::vector<int> exponents)
{
    std::sort(ALL(exponents), std::greater<int>());
    while (!exponents.empty() && !exponents.back()) {
        exponents.pop_back();
    }
    auto& ref = cache[exponents];
    if (ref) {
        return ref;
    }
    return ref = dfs(exponents, {});
}

bool is_prime(int n)
{
    for (int i = 2; i * i <= n; ++ i) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

std::vector<int> primes;

int get_prime(int i)
{
    while ((int)primes.size() <= i) {
        int p = primes.back() + 1;
        while (!is_prime(p)) {
            p ++;
        }
        primes.push_back(p);
    }
    return primes.at(i);
}

std::set<Long> result;

void search(Long product, std::vector<int> exponents)
{
    Long candidate = count(exponents);
    Long n = candidate;
    if (candidate < BOUND) {
        std::vector<int> now;
        for (Long i = 2; i * i <= candidate; ++ i) {
            if (candidate % i == 0) {
                int e = 0;
                while (candidate % i == 0) {
                    candidate /= i;
                    e ++;
                }
                now.push_back(e);
            }
        }
        if (candidate > 1) {
            now.push_back(1);
        }
        if (count(now) == n) {
            result.insert(n);
        }
    }
    int i = exponents.size();
    for (int j = 1; !i || j <= exponents[i - 1]; ++ j) {
        product *= get_prime(i);
        if (product >= BOUND) {
            break;
        }
        exponents.push_back(j);
        search(product, exponents);
        exponents.pop_back();
    }
}

int main()
{
    cache[{}] = 1;
    primes.push_back(2);
    search(1, {});
    Long answer = 0;
    for (auto&& n : result) {
        answer += n;
    }
    std::cout << answer << std::endl;
}
