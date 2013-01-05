// Codeforces Round #155
// Problem D -- Rats
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

using std::vector;

const int N = 1000;

int n, m, d;
char map[N][N + 1];

struct Point {
    int x, y;

    Point(int x, int y) : x(x), y(y) {}

    Point &operator +=(const Point &o) {
        x += o.x;
        y += o.y;
        return *this;
    }
};

const Point DELTA[4] = {Point(-1, 0), Point(0, -1), Point(0, 1), Point(1, 0)};

Point operator +(Point a, const Point &b) {
    return a += b;
}

bool operator <(const Point &a, const Point &b) {
    return a.x < b.x || a.x == b.x && a.y < b.y;
}

bool operator ==(const Point &a, const Point &b) {
    return a.x == b.x && a.y == b.y;
}

int visit_time, visit[N][N], depth[N][N];

vector <Point> floodfill(int x_0, int y_0) {
    visit_time ++;
    visit[x_0][y_0] = visit_time;
    vector <Point> queue(1, Point(x_0, y_0));
    depth[x_0][y_0] = 0;
    for (int head = 0; head < (int)queue.size(); ++ head) {
        Point p = queue[head];
        if (depth[p.x][p.y] == d) {
            continue;
        }
        for (int i = 0; i < 4; ++ i) {
            Point np = p + DELTA[i];
            if (visit[np.x][np.y] != visit_time && map[np.x][np.y] != 'X') {
                visit[np.x][np.y] = visit_time;
                depth[np.x][np.y] = depth[p.x][p.y] + 1;
                queue.push_back(np);
            }
        }
    }
    return queue;
}

vector <Point> floodfill(Point p) {
    return floodfill(p.x, p.y);
}

template <class T> 
vector <T> intersect(vector <T> a, vector <T> b) {
    sort(a.begin(), a.end());
    sort(b.begin(), b.end());
    vector <T> c;
    for (int i = 0, j = 0; i < (int)a.size() && j < (int)b.size(); ++ i, ++ j) {
        if (a[i] < b[j]) {
            j --;
        } else if (b[j] < a[i]) {
            i --;
        } else {
            c.push_back(a[i]);
        }
    }
    return c;
}

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%d%d%d", &n, &m, &d);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", map[i]);
    }
    vector <Point> rats;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            if (map[i][j] == 'R') {
                rats.push_back(Point(i, j));
            }
        }
    }
    if ((int)rats.size() > 500) {
        puts("-1");
        return 0;
    }
    vector <Point> choices = floodfill(rats.front());
    foreach (p, choices) {
        vector <Point> area = floodfill(*p);
        bool is_empty = true;
        vector <Point> choices2;
        foreach (r, rats) {
            if (std::find(area.begin(), area.end(), *r) == area.end()) {
                if (is_empty) {
                    is_empty = false;
                    choices2 = floodfill(*r);
                } else {
                    choices2 = intersect(choices2, floodfill(*r));
                }
            }
        }
        if (is_empty) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    if ((p->x != i || p->y != j) && map[i][j] != 'X') {
                        printf("%d %d %d %d\n", 1 + p->x, 1 + p->y, 1 + i, 1 + j);
                        return 0;
                    }
                }
            }
        } else if (!choices2.empty()) {
            vector <Point>::iterator q  = choices2.begin();
            printf("%d %d %d %d\n", 1 + p->x, 1 + p->y, 1 + q->x, 1 + q->y);
            return 0;
        }
    }
    puts("-1");
    return 0;
}
