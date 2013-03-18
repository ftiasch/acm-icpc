// Codeforces Round #174
// Problem E -- Cow Tennis Tournament
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <iostream>
#include <algorithm>

const int N = 100000;

int n, m, s[N], a[N], b[N];

int delta[N << 1], count[N << 1][2];

int get_id(int l, int r) {
    return l + r | l != r;
}

void build(int l, int r) {
    count[get_id(l, r)][0] = r - l + 1;
    if (l < r) {
        int m = l + r >> 1;
        build(l, m);
        build(m + 1, r);
    }
}

void cover(int l, int r, int a, int b) {
    if (b < s[l] || s[r] < a) {
        return;
    }
    int id = get_id(l, r);
    if (a <= s[l] && s[r] <= b) {
        delta[id] ^= 1;
        std::swap(count[id][0], count[id][1]);
    } else {
        int m = l + r >> 1;
        cover(l, m, a, b);
        cover(m + 1, r, a, b);
        for (int i = 0; i < 2; ++ i) {
            count[id][i ^ delta[id]] = count[get_id(l, m)][i] + count[get_id(m + 1, r)][i];
        }
    }
}

int query(int l, int r, int a, int b, int c) {
    if (b < l || r < a || a > b) {
        return 0;
    }
    int id = get_id(l, r);
    if (a <= l && r <= b) {
        return count[id][c];
    }
    int m = l + r >> 1;
    c ^= delta[id];
    return query(l, m, a, b, c) + query(m + 1, r, a, b, c);
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", s + i);
    }
    std::sort(s, s + n);
    std::vector <std::pair <int, int> > events;
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d", a + i, b + i);
        events.push_back(std::make_pair(a[i], i));
        events.push_back(std::make_pair(b[i] + 1, i));
    }
    std::sort(events.begin(), events.end());
    build(0, n - 1);
    long long answer = (long long)n * (n - 1) * (n - 2) / 6;
    for (int i = 0, j = 0; i < n; ++ i) {
        while (j < (int)events.size() && events[j].first <= s[i]) {
            int id = events[j].second;
            cover(0, n - 1, a[id], b[id]);
            j ++;
        }
        int wins = query(0, n - 1, 0, i - 1, 0) +
                   query(0, n - 1, i + 1, n - 1, 1);
        if (wins >= 2) {
            answer -= (long long)wins * (wins - 1) / 2;
        }
    }
    std::cout << answer << std::endl;
    return 0;
}
