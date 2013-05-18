// Codeforces Round #138 
// Problem E -- Planar Graph
#include <cstdio>
#include <cstring>
#include <vector>
#include <map>
#include <iostream>
#include <algorithm>

const int N = 100000 + 1;

struct Point {
    int x, y;

    Point(int x = 0, int y = 0) : x(x), y(y) {}

    Point &operator -=(const Point &o) {
        x -= o.x;
        y -= o.y;
        return *this;
    }

    int quadrant() const {
        if (y != 0) {
            return y > 0;
        }
        return x < 0;
    }
};

long long det(const Point &a, const Point &b) {
    return (long long)a.x * b.y - (long long)a.y * b.x;
}

bool operator <(const Point &a, const Point &b) {
    if (a.quadrant() == b.quadrant()) {
        return det(a, b) > 0;
    }
    return a.quadrant() < b.quadrant();
}

Point operator -(Point a, const Point &b) {
    return a -= b;
}

std::istream &operator >>(std::istream &in, Point &p) {
    return in >> p.x >> p.y;
}

int n, m;
Point points[N];

int edge_count, first_edge[N], to[N << 1], flow[N << 1], next_edge[N << 1];

void add_edge(int u, int v) {
    to[edge_count] = v;
    flow[edge_count] = 0;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

bool visit[N];

int construct(int u) {
    int size = 1;
    visit[u] = true;
    for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
        int v = to[iter];
        if (!visit[v]) {
            int ret = construct(v);
            flow[iter] = -ret;
            flow[iter ^ 1] = -flow[iter];
            size += ret;
        }
    }
    return size;
}

int cycle[N];
std::map <std::pair <int, int>, int> edges;
std::vector <int> sums[N];

int main() {
    std::cin >> n >> m;
    edge_count = 0;
    memset(first_edge, -1, sizeof(first_edge));
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --, b --;
        add_edge(a, b);
        add_edge(b, a);
    }
    for (int i = 0; i < n; ++ i) {
        std::cin >> points[i];
    }

    int bottom_id = 0;
    for (int i = 0; i < n; ++ i) {
        if (points[i].x < points[bottom_id].x) {
            bottom_id = i;
        }
    }
    points[n ++] = points[bottom_id] - Point(1, 0);
    add_edge(bottom_id, n - 1);
    add_edge(n - 1, bottom_id);

    memset(visit, 0, sizeof(visit));
    construct(n - 1);

    for (int u = 0; u < n; ++ u) {
        std::vector <std::pair <Point, int> > order;
        for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
            int v = to[iter];
            order.push_back(std::make_pair(points[v] - points[u], iter));
        }
        std::sort(order.begin(), order.end());
        sums[u].push_back(0);
        for (int i = 0; i < (int)order.size(); ++ i) {
            int iter = order[i].second;
            edges[std::make_pair(u, to[iter])] = i + 1;
            sums[u].push_back(sums[u].back() + flow[iter]);
        }
    }

    int q;
    std::cin >> q;
    while (q --) {
        int m;
        scanf("%d", &m);
        for (int i = 0; i < m; ++ i) {
            scanf("%d", cycle + i);
            cycle[i] --;
        }
        long long area = 0;
        for (int i = 0; i < m; ++ i) {
            area += det(points[cycle[i]], points[cycle[(i + 1) % m]]);
        }
        if (area < 0) {
            std::reverse(cycle, cycle + m);
        }
        int answer = 0;
        for (int i = 0; i < m; ++ i) {
            int u = cycle[i];
            int from = edges[std::make_pair(u, cycle[(i + m - 1) % m])];
            int to = edges[std::make_pair(u, cycle[(i + 1) % m])];
            if (from <= to) {
                answer += sums[u][to - 1];
                answer -= sums[u][from];
            } else {
                answer += sums[u][(int)sums[u].size() - 1];
                answer -= sums[u][from];
                answer += sums[u][to - 1];
            }
        }
        printf("%d\n", answer);
    }
    return 0;
}
