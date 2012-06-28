// SGU 198 -- Get Out!
#include <cmath>
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 333;
const double EPS = 1e-9;

int sgn(double x) {
    return x < -EPS? -1: x > EPS;
}

struct Point {
    double x, y;

    Point(double x = 0, double y = 0): x(x), y(y) {}

    double norm() const {
        return sqrt(x * x + y * y);
    }
};

Point operator -(const Point &a, const Point &b) {
    return Point(a.x - b.x, a.y - b.y);
}

double det(const Point &a, const Point &b) {
    return a.x * b.y - a.y * b.x;
}

double dot(const Point &a, const Point &b) {
    return a.x * b.x + a.y * b.y;
}

double arg(const Point &a, const Point &b) {
    return atan2(det(a, b), dot(a, b));
}

int n, queue[N], times[N];
Point p[N];
double r[N], graph[N][N], dist[N];
bool visit[N], mark[N][N];

int main() {
    scanf("%d", &n);
    for (int i = 0; i <= n; ++ i) {
        scanf("%lf%lf%lf", &p[i].x, &p[i].y, &r[i]);
    }
    memset(mark, 0, sizeof(mark));
    memset(graph, 0, sizeof(graph));
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            if (i != j) {
                if (sgn(r[i] + r[j] + r[n] + r[n] - (p[i] - p[j]).norm()) > 0) {
                    mark[i][j] = true;
                    graph[i][j] = arg(p[i] - p[n], p[j] - p[n]);
                }
            }
        }
    }
    memset(dist, 0, sizeof(dist));
    memset(times, 0, sizeof(times));
    int head = 0;
    int tail = 0;
    for (int i = 0; i < n; ++ i) {
        visit[i] = true;
        queue[tail ++] = i;
    }
    while (head != tail) {
        int i = queue[head];
        visit[i] = false;
        head = (head + 1) % N;
        for (int j = 0; j < n; ++ j) {
            if (mark[i][j] && sgn(dist[i] + graph[i][j] - dist[j]) < 0) {
                dist[j] = dist[i] + graph[i][j];
                if (!visit[j]) {
                    visit[j] = true;
                    queue[tail] = j;
                    tail = (tail + 1) % N;
                    times[j] ++;
                    if (times[j] > n) {
                        puts("NO");
                        return 0;
                    }
                }
            }
        }
    }
    puts("YES");
    return 0;
}
