// Codeforces Round #154
// Problem C -- Text Editor
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <queue>
#include <iostream>
#include <algorithm>

const int N = 200;

struct Point {
    int x, y;

    Point(): x(0), y(0) {}
    Point(int x, int y) : x(x), y(y) {}
};

std::istream &operator >>(std::istream &in, Point &p) {
    return in >> p.x >> p.y;
}

int n, a[N];
Point source, target;
std::vector <int> columns;

bool visit[N][N];
int distance[N][N];

int find_id(int y) {
    return std::lower_bound(columns.begin(), columns.end(), y) - columns.begin();
}

const int DELTA_X[4] = {-1, 1, 0, 0};
const int DELTA_Y[4] = {0, 0, -1, 1};

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    std::cin >> n;
    for (int i = 1; i <= n; ++ i) {
        std::cin >> a[i];
        columns.push_back(a[i] + 1);
    }
    std::cin >> source >> target;
    columns.push_back(source.y);
    columns.push_back(target.y);
    std::sort(columns.begin(), columns.end());
    columns.erase(std::unique(columns.begin(), columns.end()), columns.end());
    for (int i = 1; i <= n; ++ i) {
        for (int j = 0; j < (int)columns.size(); ++ j) {
            distance[i][j] = INT_MAX;
            visit[i][j] = false;
        }
    }
    std::queue <Point> queue;
    queue.push(Point(source.x, find_id(source.y)));
    distance[source.x][find_id(source.y)] = 0;
    visit[source.x][find_id(source.y)] = true;
    while (!queue.empty()) {
        Point p = queue.front();
        queue.pop();
        visit[p.x][p.y] = false;
        for (int k = 0; k < 4; ++ k) {
            Point q = Point(p.x + DELTA_X[k], p.y + DELTA_Y[k]);
            if (1 <= q.x && q.x <= n && 0 <= q.y && q.y < (int)columns.size()) {
                int cost = -1;
                if (p.x == q.x) {
                    if (columns[q.y] <= a[q.x] + 1) {
                        cost = std::abs(columns[q.y] - columns[p.y]);
                    }
                } else {
                    cost = 1;
                    if (columns[q.y] > a[q.x] + 1) {
                        q.y = find_id(a[q.x] + 1);
                    }
                }
                if (cost != -1 && distance[p.x][p.y] + cost < distance[q.x][q.y]) {
                    distance[q.x][q.y] = distance[p.x][p.y] + cost;
                    if (!visit[q.x][q.y]) {
                        visit[q.x][q.y] = true;
                        queue.push(q);
                    }
                }
            }
        }
    }
    printf("%d\n", distance[target.x][find_id(target.y)]);
    return 0;
}

