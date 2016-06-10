#include <algorithm>
#include <iostream>
#include <map>
#include <utility>
#include <vector>

typedef long long Long;

std::vector<Long> volume;
std::vector<std::pair<int, Long>> memory;

std::pair<int, Long> solve(Long n)
{
    std::pair<int, Long> tmp {-1, 0};
    auto index = std::upper_bound(volume.begin(), volume.end(), n) - volume.begin();
    auto& ref = n + 1 == volume.at(index) ? memory.at(index) : tmp;
    if (~ref.first) {
        return ref;
    }
    auto a = volume.at(index - 1);
    auto c = n / a;
    auto p = solve(n % a);
    auto q = solve(a - 1);
    auto result = std::max(std::make_pair(p.first + c, p.second + c * a), std::make_pair(q.first + c - 1, q.second + (c - 1) * a));
    return ref = result;
}

int main()
{
    Long n;
    std::cin >> n;
    for (Long a = 1; ; ++ a) {
        Long c = a * a * a;
        volume.push_back(c);
        memory.push_back({-1, 0});
        if (c > n) {
            break;
        }
    }
    memory[0] = {0, 0};
    auto result = solve(n);
    std::cout << result.first << " " << result.second << std::endl;
}
