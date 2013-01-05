// Codeforces Round #150
// Problem C -- Colorado Potato Beetle
#include <cstdio>
#include <cstring>
#include <vector>
#include <string>
#include <queue>
#include <climits>
#include <iostream>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

using std::vector;
using std::min;
using std::max;
using std::lower_bound;

struct Point {
    int x, y;

    Point(int x, int y) : x(x), y(y) {}

    Point &operator +=(const Point &o) {
        x += o.x;
        y += o.y;
        return *this;
    }

    Point &operator *=(int k) {
        x *= k;
        y *= k;
        return *this;
    }
};

Point operator +(Point a, const Point &b) {
    return a += b;
}

Point operator *(Point a, int k) {
    return a *= k;
}


struct Rectangle {
    Point a, b;

    Rectangle(const Point &a, const Point &b) : a(a), b(b) {}
};

const std::string DIRECTIONS = "LRUD";
const Point DELTA[4] = {Point(-1, 0), Point(1, 0), Point(0, 1), Point(0, -1)};

const int SIZE = 2001;

int mark[SIZE][SIZE];

int main() {
    vector <Rectangle> rectangles;
    {
        int m;
        Point p(0, 0);
        for (scanf("%d", &m); m; -- m) {
            char buffer[2];
            int length;
            scanf("%s%d", buffer, &length);
            Point q(p + DELTA[DIRECTIONS.find(*buffer)] * length);
            rectangles.push_back(Rectangle(Point(min(p.x, q.x), min(p.y, q.y)), Point(max(p.x, q.x) + 1, max(p.y, q.y) + 1)));
            p = q;
        }
    }
    vector <int> xvalues, yvalues;
    xvalues.push_back(INT_MIN);
    xvalues.push_back(INT_MAX);
    yvalues.push_back(INT_MIN);
    yvalues.push_back(INT_MAX);
    foreach (r, rectangles) {
        xvalues.push_back(r->a.x);
        xvalues.push_back(r->b.x);
        yvalues.push_back(r->a.y);
        yvalues.push_back(r->b.y);
    }
    std::sort(xvalues.begin(), xvalues.end());
    xvalues.erase(std::unique(xvalues.begin(), xvalues.end()), xvalues.end());
    std::sort(yvalues.begin(), yvalues.end());
    yvalues.erase(std::unique(yvalues.begin(), yvalues.end()), yvalues.end());
    memset(mark, 0, sizeof(mark));
    foreach (r, rectangles) {
        for (int i = lower_bound(xvalues.begin(), xvalues.end(), r->a.x) - xvalues.begin(); xvalues[i] < r->b.x; ++ i) {
            for (int j = lower_bound(yvalues.begin(), yvalues.end(), r->a.y) - yvalues.begin(); yvalues[j] < r->b.y; ++ j) {
                mark[i][j] = 1;
            }
        }
    }
    std::queue <Point> queue;
    queue.push(Point(0, 0));
    mark[0][0] = -1;
    while (!queue.empty()) {
        Point p = queue.front();
        queue.pop();
        for (int i = 0; i < 4; ++ i) {
            Point q(p + DELTA[i]);
            if (0 <= q.x && q.x < SIZE && 0 <= q.y && q.y < SIZE 
                    && !mark[q.x][q.y]) {
                mark[q.x][q.y] = -1;
                queue.push(q);
            }
        }
    }
    long long answer = 0;
    for (int i = 0; i + 1 < (int)xvalues.size(); ++ i) {
        for (int j = 0; j + 1 < (int)yvalues.size(); ++ j) {
            if (mark[i][j] != -1) {
                answer += (long long)(xvalues[i + 1] - xvalues[i]) * (yvalues[j + 1] - yvalues[j]);
            }
        }
    }
    std::cout << answer << std::endl;
    return 0;
}
