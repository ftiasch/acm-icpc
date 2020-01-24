#include <cstdio>
#include <vector>

using LL = long long;

auto unique_sum(const std::vector<int>& numbers, int size)
{
    // dp[index][sum][size]
    auto max_sum = 1;
    for (auto&& number : numbers) {
        max_sum += number;
    }
    std::vector<LL> one(max_sum), two(max_sum);
    one[0] = 1;
    max_sum = 0;
    for (auto&& number : numbers) {
        max_sum += number;
        for (auto sum = max_sum; sum >= number; -- sum) {
            two[sum] |= two[sum - number] << 1 | one[sum] & one[sum - number] << 1;
            one[sum] |= one[sum - number] << 1;
        }
    }
    auto result = 0LL;
    for (auto sum = 0; sum <= max_sum; ++ sum) {
        if ((one[sum] >> size & 1) && (~two[sum] >> size & 1)) {
            result += sum;
        }
    }
    return result;
}

int main()
{
    printf("%lld\n", unique_sum({1,3,6,8,10,11}, 3));
    std::vector<int> numbers;
    for (auto i = 1; i <= 100; ++ i) {
        numbers.push_back(i * i);
    }
    printf("%lld\n", unique_sum(numbers, 50));
}
