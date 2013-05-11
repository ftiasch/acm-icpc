// Codeforces Round #176
// Problem D -- Tourists
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <algorithm>

const int N = 100000;

int n, l[N], r[N], t[N], order[N], next[N << 1], cover[N << 1];

bool by_t(int i, int j) {
    return t[i] < t[j];
}

int find_next(int i) {
    if (next[i] == -1) {
        return i;
    }
    return next[i] = find_next(next[i]);
}

int main() {
    int q;
    scanf("%d%d", &q, &n);
    std::vector <int> ends;
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d%d", l + i, r + i, t + i);
        ends.push_back(l[i]);
        ends.push_back(r[i]);
        order[i] = i;
    }
    std::sort(order, order + n, by_t);
    std::sort(ends.begin(), ends.end());
    ends.erase(std::unique(ends.begin(), ends.end()), ends.end());
    memset(next, -1, sizeof(next));
    memset(cover, -1, sizeof(cover));
    for (int i = 0; i < n; ++ i) {
        for (int k = find_next(std::lower_bound(ends.begin(), ends.end(), l[order[i]]) - ends.begin()); ends[k] < r[order[i]]; k = find_next(k)) {
            cover[k] = t[order[i]];
            next[k] = k + 1;
        }
    }
    std::vector <std::pair <int, int> > events;
    for (int i = 0; i + 1 < (int)ends.size(); ++ i) {
        if (cover[i] != -1) {
            int a = cover[i] - ends[i];
            int b = cover[i] - ends[i + 1];
            events.push_back(std::make_pair(a, 1));
            events.push_back(std::make_pair(b, -1));
        }
    }
    std::sort(events.begin(), events.end());
    int count = 0;
    int total = 0;
    int i = 0;
    while (q --) {
        int t;
        scanf("%d", &t);
        while (i < (int)events.size() && events[i].first < t) {
            count -= events[i].second;
            total -= events[i].second * events[i].first;
            i ++;
        }
        printf("%d\n", t * count - total);
    }
    return 0;
}
