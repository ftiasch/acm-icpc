#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <algorithm>
using namespace std;

struct Point {
    int x, y;

    Point(int x = 0, int y = 0): x(x), y(y) {}
};

bool operator <(const Point &a, const Point &b) {
    if (a.x == b.x) {
        return a.y < b.y;
    }
    return a.x < b.x;
}

int n, m;
vector <Point> a, b;
vector <pair <Point, bool> > ps;

const int INF = 1000000000;

struct BinaryIndexTree {
    int data[2222];

    void clear() {
        for (int i = 1; i < 2222; ++ i) {
            data[i] = -INF;
        }
    }

    void insert(int k, int v) {
        for (int i = k; i < 2222; i += i & -i) {
            data[i] = max(data[i], v);
        }
    }

    int query(int k) {
        int result = -INF;
        for (int i = k; i >= 1; i -= i & -i) {
            result = max(result, data[i]);
        }
        return result;
    }
};

BinaryIndexTree bit[2];

int main() {
    while (true) {
        scanf("%d", &n);
        if (n == 0) {
            break;
        }
        a.resize(n);
        for (int i = 0; i < n; ++ i) {
            scanf("%d%d", &a[i].x, &a[i].y);
        }
        scanf("%d", &m);
        b.resize(m);
        for (int i = 0; i < m; ++ i) {
            scanf("%d%d", &b[i].x, &b[i].y);
        }
        int result = INF;
        ps.clear();
        for (int i = 0; i < n; ++ i) {
            ps.push_back(make_pair(a[i], false));
        }
        for (int i = 0; i < m; ++ i) {
            ps.push_back(make_pair(b[i], true));
        }
        for (int direction = 0; direction < 4; ++ direction) {
            int min_x = INT_MAX;
            int min_y = INT_MAX;
            for (int i = 0; i < (n + m); ++ i) {
                ps[i].first = Point(-ps[i].first.y, ps[i].first.x);
                min_x = min(min_x, ps[i].first.x);
                min_y = min(min_y, ps[i].first.y);
            }
            for (int i = 0; i < (n + m); ++ i) {
                ps[i].first.x -= min_x - 1;
                ps[i].first.y -= min_y - 1;
            }
            sort(ps.begin(), ps.end());
            bit[0].clear();
            bit[1].clear();
            for (int i = 0; i < (n + m); ++ i) {
                Point p = ps[i].first;
                int t = ps[i].second;
                result = min(result, p.x + p.y - bit[t ^ 1].query(p.y));
                bit[t].insert(p.y, p.x + p.y);
            }
        }
        printf("%d\n", result);
    }
    return 0;
}
