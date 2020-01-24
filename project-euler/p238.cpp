#include <cassert>
#include <cstdio>
#include <string>
#include <tuple>
#include <vector>

const auto MOD = 20300713;
const auto K = 2000000000000000ULL;
const auto SUM = 80846691;

// int link[SUM];
bool visited[SUM + 1];

unsigned long long contribution(int index, int r)
{
    if (1ULL * r > K) {
        return 0ULL;
    }
    return (index + 1ULL) * (1ULL + (K - r) / SUM);
}

int main()
{
    std::string s;
    auto seed = 14025256;
    std::vector<int> memory(MOD, -1);
    while (true) {
        memory[seed] = s.size();
        s += std::to_string(seed);
        seed = 1LL * seed * seed % MOD;
        if (~memory[seed]) {
            break;
        }
    }
    auto n = static_cast<int>(s.size());
    auto sum = 0;
    for (auto i = 0; i < n; ++ i) {
        sum += s[i] - '0';
    }
    assert(sum == SUM);
    auto count = SUM;
    auto result = 0ULL;
    for (auto start = 0; start < n; ++ start) {
        auto prefix_sum = 0;
        for (auto i = start; i < start + n; ++ i) {
            prefix_sum += s[i % n] - '0';
            if (prefix_sum && !visited[prefix_sum]) {
                count --;
                visited[prefix_sum] = true;
                result += contribution(start, prefix_sum);
            }
        }
        printf("%d %d %llu\n", start, count, result);
    }
    // std::vector<int> link(sum);
    // for (auto i = 0; i < sum; ++ i) {
    //     link[i] = i + 1;
    // }
    // prefix[0] = 0;
    // for (int i = 1; i < n << 1; ++ i) {
    //     prefix[i] = prefix[i - 1] + (s[i] - '0');
    // }
    // auto result = contribution(2, 0);
    // for (auto start = 0; start < n; ++ start) {
    //     auto p = 0;
    //     auto q = 1;
    //     auto index = start;
    //     while (q) {
    //         index = std::lower_bound(prefix + index, prefix + (n << 1), prefix[start] + q) - prefix;
    //         if (index < n << 1 && prefix[index] - prefix[start] == q) {
    //             count --;
    //             result += contribution(start, q);
    //             link[p] = link[q];
    //         } else {
    //             p = q;
    //         }
    //         q = link[q];
    //     }
    //     printf("%d %d %llu\n", start, count, result);
    // }
    // printf("%llu\n", result);
}
