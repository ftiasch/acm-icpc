#include <algorithm>
#include <iostream>
#include <future>
#include <thread>
#include <vector>

using LL = long long;

struct Table
{
public:
    Table(int d, const std::vector<int>& count, int n, int add)
    {
        sum = 0;
        auto minimum = 0;
        auto maximum = 0;
        for (auto i = 0; i < n; ++ i) {
            sum += add + count[i] - 1;
            minimum = std::min(minimum, sum);
            maximum = std::max(maximum, sum);
        }
        offset = minimum;
        auto range = maximum - minimum + 1;
        table.resize(range);
        sum = 0;
        for (auto i = 0; i < n; ++ i) {
            sum += add + count[i] - 1;
            auto& ref = table[sum - offset];
            ref.first ++;
            ref.second += i;
        }
    }

    std::pair<int, LL> query(LL target)
    {
        target -= offset;
        if (target < 0 || target >= (int)table.size()) {
            return {0, 0LL};
        }
        return table.at(target);
    }

    int sum, offset;
    std::vector<std::pair<int, LL>> table;
};

LL solve(int d)
{
    std::vector<int> count(1e6);
    for (auto i = 1; i < 1e6; ++ i) {
        count[i] = count[i / 10] + (i % 10 == d);
    }
    std::vector<Table> tables;
    for (int add = 0; add <= 6; ++ add) {
        tables.emplace_back(d, count, 1e5, add);
    }
    auto result = 0LL;
    auto sum = 1LL;
    for (auto prefix = 0; prefix < 1e6; ++ prefix) {
        auto&& precomputed = tables[count[prefix]];
        auto p = precomputed.query(-sum);
        result += 100000LL * prefix * p.first;
        result += p.second;
        sum += precomputed.sum;
    }
    return result;
}

const int D = 10;

int main()
{
    auto max_threads = std::thread::hardware_concurrency();
    auto result = 0LL;
    for (auto s = 1; s < D; s += max_threads) {
        std::vector<std::future<LL>> futures;
        for (auto i = 0U; i < max_threads; ++ i) {
            auto d = s + i;
            if (d < D) {
                std::packaged_task<LL()> task(std::bind(solve, d));
                futures.push_back(task.get_future());
                std::thread(std::move(task)).detach();
            }
        }
        for (auto& future : futures) {
            future.wait();
            auto r = future.get();
            std::cout << r << std::endl;
            result += r;
        }
    }
    std::cout << result << std::endl;
}
