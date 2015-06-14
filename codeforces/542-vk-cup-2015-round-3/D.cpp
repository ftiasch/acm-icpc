#include <algorithm>
#include <iostream>
#include <map>
#include <set>
#include <vector>

typedef long long Long;
template <typename K, typename V>
using Map = std::map <K, V>;

const Long N  = 1000000;
const Long N2 = N * N;

#define ALL(v) (v).begin(), (v).end()

std::vector <Long> get_divisors(Long n)
{
    std::vector <Long> divisors;
    for (Long d = 1; d * d <= n; ++ d) {
        if (n % d == 0) {
            divisors.push_back(d);
            if (d * d != n) {
                divisors.push_back(n / d);
            }
        }
    }
    std::sort(ALL(divisors));
    return divisors;
}

Map <Long, int> prime_powers;

int is_prime_power(Long n)
{
    if (n <= 1) {
        return 0;
    }
    const auto &iterator = prime_powers.find(n);
    if (iterator != prime_powers.end()) {
        return iterator->second;
    }
    for (Long d = 2; d * d <= n; ++ d) {
        if (n % d == 0) {
            return 0;
        }
    }
    return n;
}

int main()
{
    std::vector <bool> prime(N + 1, true);
    for (int p = 2; p <= N; ++ p) {
        if (prime[p]) {
            for (Long pp = p; pp <= N2; pp *= p) {
                prime_powers[pp] = p;
            }
            if ((long long)p * p <= N) {
                for (int i = p * p; i <= N; i += p) {
                    prime[i] = false;
                }
            }
        }
    }
    Long a;
    std::cin >> a;
    const auto &divisors = get_divisors(a);
    std::vector <std::pair <Long, Long>> candidates;
    for (Long d : divisors) {
        Long p = is_prime_power(d - 1);
        if (p) {
            candidates.push_back({p, d});
        }
    }
    std::sort(ALL(candidates));
    Map <Long, Long> ways;
    ways[a] = 1;
    for (int i = 0; i < (int)candidates.size(); ) {
        int j = i;
        while (j < (int)candidates.size() && candidates[i].first == candidates[j].first) {
            j ++;
        }
        Map <Long, Long> new_ways;
        for (const auto &iterator : ways) {
            new_ways[iterator.first] += iterator.second;
            for (int k = i; k < j; ++ k) {
                if (iterator.first % candidates[k].second == 0) {
                    new_ways[iterator.first / candidates[k].second] += iterator.second;
                }
            }
        }
        ways.swap(new_ways);
        i = j;
    }
    std::cout << ways[1] << std::endl;
    return 0;
}
