#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;

struct Point {
    long long x, y;

    Point(long long x = 0, long long y = 0): x(x), y(y) {}
};

Point operator -(const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y);
}

long long det(const Point &a, const Point &b) {
    return a.x * b.y - a.y * b.x;
}

istream &operator >>(istream &in, Point &p) {
    return in >> p.x >> p.y;
}

const int N = 5000;

int n, id[5];
Point points[N], hull[5];
bool visit[N], found;

void dfs(int k) {
    if (!found) {
        if (k < 5) {
            for (int i = 0; i < n; ++ i) {
                if (!visit[i]) {
                    visit[i] = true;
                    id[k] = i + 1;
                    hull[k] = points[i];
                    dfs(k + 1);
                    visit[i] = false;
                }
            }
        } else {
            bool check = true;
            for (int i = 0; i < 5; ++ i) {
                for (int j = 0; j < 5; ++ j) {
                    check &= det(hull[(i + 1) % 5] - hull[i], hull[j] - hull[i]) >= 0;
                }
            }
            if (check) {
                found = true;
                puts("Yes");
                for (int i = 0; i < 5; ++ i) {
                    printf("%d ", id[i]);
                }
                puts("");
            }
        }
    }
}

int main() {
    cin >> n;
    for (int i = 0; i < n; ++ i) {
        cin >> points[i];
    }
    n = min(n, 20);
    memset(visit, 0, sizeof(visit));
    found = false;
    dfs(0);
    if (!found) {
        puts("No");
    }
    return 0;
}
