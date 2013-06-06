// Codeforces Round #124
// Problem C -- Paint Tree
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

#define ALL(v) (v).begin(), (v).end()

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 1500;

int n, size[N];
std::vector <int> edges[N];

void prepare(int p, int u) {
    size[u] = 1;
    foreach (iter, edges[u]) {
        int v = *iter;
        if (v != p) {
            prepare(u, v);
            size[u] += size[v];
        }
    }
}

struct Point {
    int x, y;

    Point(int x = 0, int y = 0) : x(x), y(y) {}

    Point &operator-=(const Point &o) {
        x -= o.x;
        y -= o.y;
        return *this;
    }
};

Point operator -(Point a, const Point &b) {
    return a -= b;
}

long long det(const Point &a, const Point &b) {
    return (long long)a.x * b.y - (long long)a.y * b.x;
}

bool operator <(const Point &a, const Point &b) {
    return det(a, b) > 0;
}

Point points[N];
int answer[N];

bool by_position(int i, int j) {
    if (points[i].x != points[j].x) {
        return points[i].x < points[j].x;
    }
    return points[i].y < points[j].y;
}

void solve(int p, int u, std::vector <int> ids) {
    int o = *std::max_element(ALL(ids), by_position);
    answer[o] = u;
    std::vector <std::pair <Point, int> > order;
    foreach (iter, ids) {
        int i = *iter;
        if (i != o) {
            order.push_back(std::make_pair(points[i] - points[o], i));
        }
    }
    std::sort(ALL(order));
    int i = 0;
    foreach (iter, edges[u]) {
        int v = *iter;
        if (v != p) {
            std::vector <int> new_ids;
            for (int j = i; j < i + size[v]; ++ j) {
                new_ids.push_back(order[j].second);
            }
            solve(u, v, new_ids);
            i += size[v];
        }
    }
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --, b --;
        edges[a].push_back(b);
        edges[b].push_back(a);
    }
    prepare(-1, 0);
    std::vector <int> alls;
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", &points[i].x, &points[i].y);
        alls.push_back(i);
    }
    solve(-1, 0, alls);
    for (int i = 0; i < n; ++ i) {
        printf("%d\n", answer[i] + 1);
    }
    return 0;
}
