#include <algorithm>
#include <iostream>
#include <numeric>
#include <utility>

typedef long long Long;

const int N = 100000;
const Long BOUND = (Long)1e18;

Long x[N], y[N], z[N], low[4], high[4], answer_x, answer_y, answer_z;

void update_min(Long& x, Long a)
{
    x = std::min(x, a);
}

void update_max(Long& x, Long a)
{
    x = std::max(x, a);
}

Long div2(Long n)
{
    return n >= 0 ? n / 2 : (n - 1) / 2;
}

std::pair<Long, Long> helper(Long* low, Long* high, Long radius, Long x)
{
    auto l = std::max(std::max(low[0] - radius + x, low[1] - radius - x), -2 * BOUND);
    auto h = std::min(std::min(high[0] + radius + x, high[1] + radius - x), 2 * BOUND);
    return {l, std::min(l + 1, h)};
}

bool check(Long radius)
{
    for (int i = 0; i < 4; ++ i) {
        if (low[i] - radius > high[i] + radius) {
            return false;
        }
    }
    auto x_low_1 = std::max(div2(low[1] - high[0] + 1) - radius, -BOUND);
    auto x_high_1 = std::min(div2(high[1] - low[0]) + radius, BOUND);
    auto x_low_2 = std::max(div2(low[3] - high[2] + 1) - radius, -BOUND);
    auto x_high_2 = std::min(div2(high[3] - low[2]) + radius, BOUND);
    auto x_low = std::max(x_low_1, x_low_2);
    auto x_high = std::min(x_high_1, x_high_2);
    if (x_low > x_high) {
        return false;
    }
    x_high = std::min(x_high, x_low + 1);
    for (auto x = x_low; x <= x_high; ++ x) {
        auto sum_range = helper(low, high, radius, x);
        auto sub_range = helper(low + 2, high + 2, radius, x);
        for (auto sum = sum_range.first; sum <= sum_range.second; ++ sum) {
            for (auto sub = sub_range.first; sub <= sub_range.second; ++ sub) {
                if (~(sum - sub) & 1) {
                    answer_x = x;
                    answer_y = (sum + sub) / 2;
                    answer_z = (sum - sub) / 2;
                    return true;
                }
            }
        }
    }
    return false;
}

int main()
{
    std::ios::sync_with_stdio(false);

    int T;
    std::cin >> T;
    while (T --) {
        int n;
        std::cin >> n;
        for (int i = 0; i < n; ++ i) {
            std::cin >> x[i] >> y[i] >> z[i];
        }
        Long h = 0;
        for (int i = 0; i < 4; ++ i) {
            low[i] = std::numeric_limits<Long>::min();
            high[i] = std::numeric_limits<Long>::max();
        }
        for (int i = 0; i < n; ++ i) {
            auto&& X = x[i];
            auto&& Y = y[i];
            auto&& Z = z[i];
            update_max(h, std::abs(X) + std::abs(Y) + std::abs(Z));
            update_max(low[0], -X + Y + Z);
            update_min(high[0], -X + Y + Z);
            update_max(low[1], X + Y + Z);
            update_min(high[1], X + Y + Z);
            update_max(low[2], -X + Y - Z);
            update_min(high[2], -X + Y - Z);
            update_max(low[3], X + Y - Z);
            update_min(high[3], X + Y - Z);
        }
        Long l = 0;
        while (l < h) {
            Long m = l + h >> 1;
            if (check(m)) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        std::cerr << "=>" << h << std::endl;
        check(h);
        std::cout << answer_x << " " << answer_y << " " << answer_z << "\n";
    }
    std::cout << std::flush;
}
