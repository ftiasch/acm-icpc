// Codeforces Round #187
// Problem D -- Sereja and Straight Lines
#include <cstdio>
#include <cstring>
#include <climits>
#include <iostream>
#include <algorithm>

const int N = 100000 + 1;

struct Point {
    int x, y;

    Point(int x = 0, int y = 0) : x(x), y(y) {}
};

bool operator < (const Point &a, const Point &b) {
    if (a.x != b.x) {
        return a.x < b.x;
    }
    return a.y < b.y;
}

int n;
Point points[N];

int premin[N], premax[N], sufmin[N], sufmax[N];

bool check(long long limit) {
    for (int i = 0, j = 0; i < n; ++ i) {
        while (points[i].x > points[j].x + limit) {
            j ++;
        }
        int ymax = std::max(sufmax[i + 1], premax[j]);
        int ymin = std::min(sufmin[i + 1], premin[j]);
        if (ymin + limit >= ymax) {
            return true;
        }
    }
    return false;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        int x, y;
        scanf("%d%d", &x, &y);
        points[i].x = x - y;
        points[i].y = x + y;
    }
    std::sort(points, points + n);
    premin[0] = INT_MAX;
    premax[0] = INT_MIN;
    for (int i = 0; i < n; ++ i) {
        premin[i + 1] = std::min(premin[i], points[i].y);
        premax[i + 1] = std::max(premax[i], points[i].y);
    }
    sufmin[n] = INT_MAX;
    sufmax[n] = INT_MIN;
    for (int i = n - 1; i >= 0; -- i) {
        sufmin[i] = std::min(sufmin[i + 1], points[i].y);
        sufmax[i] = std::max(sufmax[i + 1], points[i].y);
    }
    long long low = 0;
    long long high = 4000000000LL;
    while (low < high) {
        long long middle = low + high >> 1;
        if (check(middle)) {
            high = middle;
        } else {
            low = middle + 1;
        }
    }
    std::cout << (high / 2) << "." << (high % 2 * 5) << std::endl;
    return 0;
}
