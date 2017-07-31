#include <algorithm>
#include <cstdio>
#include <cstring>
#include <map>
#include <set>
#include <utility>
#include <vector>

const int N = 300000;

struct Sum
{
    int add(int id, int value)
    {
        if (a[0].second == id) {
            a[0].first = std::max(a[0].first, value);
        } else if (a[1].first < value) {
            a[1] = {value, id};
        }
        if (a[0].first < a[1].first) {
            std::swap(a[0], a[1]);
        }
    }

    int ask(int id)
    {
        if (a[0].second != id) {
            return a[0].first;
        }
        return a[1].first;
    }

    std::pair<int, int> a[2] = {{0, -1}, {0, -1}};
};

int cost[N + 1], toupd[N];

int main()
{
#ifdef LOCAL_JUDGE
    freopen("E.in", "r", stdin);
#endif
    int n, budget;
    while (scanf("%d%d", &n, &budget) == 2) {
        cost[n] = 0;
        std::vector<std::pair<int, int>> events;
        events.emplace_back(0, n);
        events.emplace_back(2000000000, n);
        for (int i = 0, l, r; i < n; ++ i) {
            scanf("%d%d%d", &l, &r, cost + i);
            events.emplace_back(l, i);
            events.emplace_back(r, i);
        }
        std::sort(events.begin(), events.end());
        std::vector<int> values(cost, cost + n);
        std::sort(values.begin(), values.end());
        values.erase(std::unique(values.begin(), values.end()), values.end());
        std::set<int> covers;
        if (events[0].second < n) {
            covers.insert(events[0].second);
        }
        int curmx = 0;
        std::vector<std::pair<int, int>> parts;
        memset(toupd, 0, sizeof(toupd));
        std::vector<Sum> bit(values.size());
        std::map<std::pair<int, int>, int> length;
        for (int t = 1; t < (int)events.size(); ++ t) {
            int mxlen = events[t].first - events[t - 1].first;
            if (mxlen > 0 && (int)covers.size() <= 2) {
                int p = n, q = n;
                if ((int)covers.size() > 0) {
                    p = *covers.begin();
                }
                if ((int)covers.size() > 1) {
                    q = *covers.rbegin();
                }
                int start = -1;
                if (p == n) { // 0
                    start = curmx;
                } else {
                    if (q == n) { // 1
                        if (cost[p] <= budget) {
                            start = toupd[p];
                            for (int k = (int)(std::upper_bound(values.begin(), values.end(), budget - cost[p]) - values.begin()) - 1; k >= 0; k -= ~k & k + 1) {
                                start = std::max(start, bit[k].ask(p));
                            }
                            auto value = length[{p, q}] + mxlen;
                            for (int k = std::lower_bound(values.begin(), values.end(), cost[p]) - values.begin(); k < (int)values.size(); k += ~k & k + 1) {
                                bit[k].add(p, value);
                            }
                        }
                    } else if (cost[p] + cost[q] <= budget) {
                        start = length[{p, n}] + length[{q, n}];
                        toupd[p] = std::max(toupd[p], length[{q, n}] + length[{p, q}] + mxlen);
                        toupd[q] = std::max(toupd[q], length[{p, n}] + length[{p, q}] + mxlen);
                    }
                    if (~start) {
                        start += length[{p, q}] + length[{n, n}];
                    }
                }
                if (~start && start + mxlen > curmx) {
                    curmx = start + mxlen;
                    parts.emplace_back(curmx, events[t].first);
                }
                length[{p, q}] += mxlen;
            }
            auto&& i = events[t].second;
            if (i < n) {
                if (covers.count(i)) {
                    covers.erase(i);
                } else {
                    covers.insert(i);
                }
            }
        }
        int q, t;
        scanf("%d", &q);
        while (q --) {
            scanf("%d", &t);
            auto it = std::lower_bound(parts.begin(), parts.end(), std::make_pair(t, 0));
            printf("%d\n", it->second - (it->first - t));
        }
    }
}
