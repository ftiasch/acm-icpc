// Codeforces Round #157
// Problem E -- Little Elephant and Tree
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <algorithm>

using std::vector;
using std::pair;
using std::make_pair;

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i) 

const int N = 100000;

int n, m, label_count, label[N], size[N], position[N];
vector <int> tree[N];

void dfs(int p, int u) {
    label[u] = label_count ++;
    position[label[u]] = u;
    size[u] = 1;
    foreach (iter, tree[u]) {
        if (*iter != p) {
            dfs(u, *iter);
            size[u] += size[*iter];
        }
    }
}

int a[N], b[N];

vector <pair <int, int> > events;

int get_id(int l, int r) {
    return l + r | l != r;
}

int cover[N << 1], total[N << 1];

void update(int l, int r) {
    int m = l + r >> 1;
    total[get_id(l, r)] = total[get_id(l, m)] + total[get_id(m + 1, r)];
    if (cover[get_id(l, r)]) {
        total[get_id(l, r)] = r - l + 1;
    }
}

void modify(int l, int r, int a, int b, int c) {
    if (b < l || r < a) {
        return;
    }
    if (a <= l && r <= b) {
        cover[get_id(l, r)] += c;
        if (l == r) {
            total[get_id(l, r)] = cover[get_id(l, r)] > 0;
        } else {
            update(l, r);
        }
        return;
    }
    int m = l + r >> 1;
    modify(l, m, a, b, c);
    modify(m + 1, r, a, b, c);
    update(l, r);
}

int answer[N];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --, b --;
        tree[a].push_back(b);
        tree[b].push_back(a);
    }
    label_count = 0;
    dfs(-1, 0);
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d", a + i, b + i);
        a[i] --;
        b[i] --;
        events.push_back(make_pair(label[a[i]], i << 1));
        events.push_back(make_pair(label[a[i]] + size[a[i]], (i << 1) | 1));
        events.push_back(make_pair(label[b[i]], i << 1));
        events.push_back(make_pair(label[b[i]] + size[b[i]], (i << 1) | 1));
    }
    std::sort(events.begin(), events.end());
    memset(cover, 0, sizeof(cover));
    for (int now = 0, pt = 0; now < n; ++ now) {
        while (pt < (int)events.size() && events[pt].first == now) {
            int id = events[pt].second >> 1;
            int delta = (events[pt].second & 1) ? -1 : 1;
            modify(0, n - 1, label[a[id]], label[a[id]] + size[a[id]] - 1, delta);
            modify(0, n - 1, label[b[id]], label[b[id]] + size[b[id]] - 1, delta);
            pt ++;
        }
        answer[position[now]] = std::max(total[get_id(0, n - 1)] - 1, 0);
    }
    for (int i = 0; i < n; ++ i) {
        printf("%d%c", answer[i], i == n - 1 ? '\n' : ' ');
    }
    return 0;
}
