// Codeforces Round #150
// Problem D -- Cubes
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <iostream>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

using std::vector;

const int N = 1000;

struct Point {
    int x, y;

    Point(int x, int y): x(x), y(y) {}
};

std::istream &operator >>(std::istream &in, Point &p) {
    return in >> p.x >> p.y;
}

int det(const Point &a, const Point &b) {
    return a.x * b.y - a.y * b.x;
}

int dot(const Point &a, const Point &b) {
    return a.x * b.x + a.y * b.y;
}

int n, height[N][N];
Point v(0, 0);

bool operator <(const Point &a, const Point &b) {
    return dot(a, v) < dot(b, v);
}

const int M = (N + 1) * (N + 1);

int cover[M << 1], minimum[M << 1];

int get_id(int l, int r) {
    return l + r | l != r;
}

void update(int l, int r) {
    int id = get_id(l, r);
    int m = l + r >> 1;
    minimum[id] = std::max(std::min(minimum[get_id(l, m)], minimum[get_id(m + 1, r)]), cover[id]);
}

void insert(int l, int r, int a, int b, int c) {
    if (b < l || r < a) {
        return;
    }
    if (a <= l && r <= b) {
        int id = get_id(l, r);
        cover[id] = std::max(cover[id], c);
        minimum[id] = std::max(minimum[id], c);
        return;
    }
    int m = l + r >> 1;
    insert(l, m, a, b, c);
    insert(m + 1, r, a, b, c);
    update(l, r);
}

int query(int l, int r, int a, int b) {
    if (b < l || r < a) {
        return INT_MAX;
    }
    if (a <= l && r <= b) {
        return minimum[get_id(l, r)];
    }
    int m = l + r >> 1;
    return std::max(cover[get_id(l, r)], std::min(query(l, m, a, b), query(m + 1, r, a, b)));
}

int main() {
    std::ios::sync_with_stdio(false);
    std::cin >> n >> v;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            std::cin >> height[i][j];
        }
    }
    vector <int> values;
    vector <Point> events;
    for (int i = 0; i <= n; ++ i) {
        for (int j = 0; j <= n; ++ j) {
            values.push_back(det(Point(i, j), v));
            if (i < n && j < n) {
                events.push_back(Point(i, j));
            }
        }
    }
    std::sort(values.begin(), values.end());
    values.erase(std::unique(values.begin(), values.end()), values.end());
    std::sort(events.begin(), events.end());
    long long answer = 0;
    foreach (iter, events) {
        int x = iter->x;
        int y = iter->y;
        int begin = INT_MAX;
        int end = INT_MIN;
        for (int i = 0; i < 2; ++ i) {
            for (int j = 0; j < 2; ++ j) {
                Point p(x + i, y + j);
                begin = std::min(begin, det(p, v));
                end = std::max(end, det(p, v));
            }
        }
        begin = std::lower_bound(values.begin(), values.end(), begin) - values.begin();
        end = std::lower_bound(values.begin(), values.end(), end) - values.begin() - 1;
        answer += std::max(height[x][y] - query(0, (int)values.size() - 2, begin, end), 0);
        insert(0, (int)values.size() - 2, begin, end, height[x][y]);
    }
    std::cout << answer << std::endl;
    return 0;
}
