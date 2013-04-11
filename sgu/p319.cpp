// SGU 319 -- Kalevich Strikes Back
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <iostream>
#include <algorithm>

const int N = 60000 + 1;

int n, x[N][2], y[N][2], parent[N], status[N], flag[N << 2];
long long area[N];

int get_id(int l, int r) {
    return l + r | l != r;
}

void push_down(int l, int r) {
    int id = get_id(l, r);
    if (flag[id] != -1) {
        int m = l + r >> 1;
        flag[get_id(l, m)] = flag[get_id(m + 1, r)] = flag[id];
        flag[id] = -1;
    }
}

void cover(int l, int r, int a, int b, int c) {
    if (b < l || r < a) {
        return;
    }
    if (a <= l && r <= b) {
        flag[get_id(l, r)] = c;
    } else {
        push_down(l, r);
        int m = l + r >> 1;
        cover(l, m, a, b, c);
        cover(m + 1, r, a, b, c);
    }
}

int query(int l, int r, int a) {
    if (l == a && a == r) {
        return flag[get_id(l, r)];
    }
    push_down(l, r);
    int m = l + r >> 1;
    if (a <= m) {
        return query(l, m, a);
    }
    return query(m + 1, r, a);
}

int main() {
    int w, h;
    scanf("%d%d%d", &n, &w, &h);
    x[0][0] = y[0][0] = 0;
    x[0][1] = w;
    y[0][1] = h;
    for (int i = 1; i <= n; ++ i) {
        scanf("%d%d%d%d", &x[i][0], &y[i][0], &x[i][1], &y[i][1]);
        if (x[i][0] > x[i][1]) {
            std::swap(x[i][0], x[i][1]);
        }
        if (y[i][0] > y[i][1]) {
            std::swap(y[i][0], y[i][1]);
        }
    }
    std::vector <int> values;
    std::vector <std::pair <int, int> > events;
    for (int i = 0; i <= n; ++ i) {
        for (int j = 0; j < 2; ++ j) {
            events.push_back(std::make_pair(x[i][j], i));
            values.push_back(y[i][j]);
        }
    }
    std::sort(values.begin(), values.end());
    values.erase(std::unique(values.begin(), values.end()), values.end());
    std::sort(events.begin(), events.end());
    memset(flag, 0, sizeof(flag));
    memset(status, 0, sizeof(status));
    int m = values.size() - 1;
    for (int k = 0; k < (int)events.size(); ++ k) {
        int id = events[k].second;
#define FIND(v) std::lower_bound(values.begin(), values.end(), v) - values.begin()
        int a = FIND(y[id][0]);
        int b = FIND(y[id][1]) - 1;
        status[id] ^= 1;
        if (status[id]) {
            parent[id] = query(0, m, a);
            cover(0, m, a, b, id);
        } else {
            cover(0, m, a, b, parent[id]);
        }
#undef FIND
    }
    memset(area, 0, sizeof(area));
    for (int i = 0; i <= n; ++ i) {
        long long tmp = (long long)(x[i][1] - x[i][0]) * (y[i][1] - y[i][0]);
        area[i] += tmp;
        if (i) {
            area[parent[i]] -= tmp;
        }
    }
    std::vector <long long> areas;
    for (int i = 0; i <= n; ++ i) {
        areas.push_back(area[i]);
    }
    std::sort(areas.begin(), areas.end());
    for (int i = 0; i <= n; ++ i) {
        std::cout << areas[i] << (i == n ? '\n' : ' ');
    }
    return 0;
}
