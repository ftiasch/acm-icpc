#include <algorithm>
#include <cstdio>
#include <cstdlib>
#include <climits>
#include <deque>
#include <set>
#include <utility>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

struct Buffer {
    Buffer() : value(0), index(INT_MAX), sum(0)
    {
        set.insert({INT_MAX, 0});
    }

    void add(int k, int d)
    {
        if (k <= index) {
            value += d;
        }
        sum += d;
        const auto &iterator = set.lower_bound({k, INT_MIN});
        if (iterator != set.end() && iterator->first == k) {
            d += iterator->second;
            set.erase(iterator);
        }
        set.insert({k, d});
    }

    void unshift(int k, int v)
    {
        add(set.begin()->first, -v);
        const auto &iterator = set.begin();
        if (iterator->second == 0) {
            set.erase(iterator);
        }
        add(k, v);
    }

    std::pair <int, int> last() {
        auto e = *set.rbegin();
        int k = e.first;
        int v = std::min(value, sum);
        if ((int)set.size() == 1 && sum == v) {
            k = 0;
        }
        return {k, v};
    }

    void pop() {
        auto iterator = set.lower_bound(*set.rbegin());
        auto e = *iterator;
        set.erase(iterator);
        if (sum < value) {
            value = sum;
            index = e.first;
        }
        sum -= e.second;
    }

    int min()
    {
        int result = value;
        int tsum = 0;
        for (const auto &iterator : set) {
            tsum += iterator.second;
            result = std::min(result, tsum);
        }
        return result;
    }

    int value, index, sum;
    std::set <std::pair <int, int>> set;
};

int main()
{
    int n, r;
    scanf("%d%d", &n, &r);
    std::vector <std::pair <int, int>> ducks;
    for (int i = 0; i < n; ++ i) {
        int h, t;
        scanf("%d%d", &h, &t);
        h = std::max(h, 0);
        if (t >= 0) {
            ducks.push_back({h, t + 1});
        }
    }
    std::sort(ALL(ducks));
    n = ducks.size();
    Buffer buffer;
    for (int t = INT_MAX; t > 0; ) { // [t, \infty) is done
        while (t <= buffer.last().first - r) {
            buffer.pop();
        }
        auto e = buffer.last();
        int next = e.first - r;
        if (!ducks.empty()) {
            next = std::max(next, ducks.back().first);
        }
        buffer.unshift(next, e.second);
        t = next;
        if (t <= e.first - r) {
            buffer.pop();
        }
        while (!ducks.empty() && t == ducks.back().first) {
            buffer.add(ducks.back().second, 1);
            ducks.pop_back();
        }
    }
    printf("%d\n", n - buffer.min());
    return 0;
}
