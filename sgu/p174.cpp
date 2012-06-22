// SGU 174 -- Walls
#include <cstdio>
#include <cstring>
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

bool operator ==(const Point &a, const Point &b) {
    return a.x == b.x && a.y == b.y;
}

const int N = 200000;

int n, parent[N << 1];
Point points[N << 1], segments[N][2];

int find(int i) {
    if (parent[i] != i) {
        parent[i] = find(parent[i]);
    }
    return parent[i];
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < 2; ++ j) {
            scanf("%d%d", &segments[i][j].x, &segments[i][j].y);
            points[(i << 1) | j] = segments[i][j];
        }
    }
    sort(points, points + (n << 1));
    int m = unique(points, points + (n << 1)) - points;
    for (int i = 0; i < m; ++ i) {
        parent[i] = i;
    }
    for (int i = 0; i < n; ++ i) {
        int a = lower_bound(points, points + m, segments[i][0]) - points;
        int b = lower_bound(points, points + m, segments[i][1]) - points;
        if (find(a) == find(b)) {
            printf("%d\n", i + 1);
            return 0;
        }
        parent[find(a)] = find(b);
    }
    puts("0");
    return 0;
}
