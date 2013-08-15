// School Team Contest #2
// Problem F -- BerPain
#include <cassert>
#include <cmath>
#include <cstdio>
#include <cstring>
#include <set>
#include <map>
#include <queue>
#include <string>
#include <vector>
#include <algorithm>

#define ALL(v) (v).begin(), (v).end()
#define SIZE(v) ((int)(v).size())
#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i) 

const int N = 100 + 4;
const double EPS = 1e-8;

int signum(double x, double eps = EPS) {
    return x < -eps ? -1 : x > eps;
}

// {{{ Point class
struct Point {
    double x, y;

    Point() : x(0), y(0) {}
    Point(double x, double y) : x(x), y(y) {}

    Point &operator +=(const Point &o) {
        x += o.x;
        y += o.y;
        return *this;
    }

    Point &operator -=(const Point &o) {
        x -= o.x;
        y -= o.y;
        return *this;
    }

    Point &operator *=(double k) {
        x *= k;
        y *= k;
        return *this;
    }

    Point &operator /=(double k) {
        x /= k;
        y /= k;
        return *this;
    }

    double norm2() const {
        return x * x + y * y;
    }

    double norm() const {
        return sqrt(norm2());
    }

    double arg() const {
        return atan2(y, x);
    }

    bool on(const Point &, const Point &) const;
    bool in(const std::vector <Point> &) const;
};

bool operator <(const Point &a, const Point &b) {
    return signum(a.x - b.x) < 0 || signum(a.x - b.x) == 0 && signum(a.y - b.y) < 0;
}

bool operator ==(const Point &a, const Point &b) {
    return signum(a.x - b.x) == 0 && signum(a.y - b.y) == 0;
}

Point operator +(Point a, const Point &b) {
    return a += b;
}

Point operator -(Point a, const Point &b) {
    return a -= b;
}

Point operator *(Point a, double k) {
    return a *= k;
}

Point operator *(double k, Point a) {
    return a *= k;
}

Point operator /(Point a, double k) {
    return a /= k;
}

double det(const Point &a, const Point &b) {
    return a.x * b.y - a.y * b.x;
}

double dot(const Point &a, const Point &b) {
    return a.x * b.x + a.y * b.y;
}

bool parallel(const Point &a, const Point &b, const Point &c, const Point &d) {
    return signum(det(b - a, d - c)) == 0;
}

Point intersect(const Point &a, const Point &b, const Point &c, const Point &d) {
    double s1 = det(b - a, c - a);
    double s2 = det(b - a, d - a);
    return (c * s2 - d * s1) / (s2 - s1);
}

bool Point::on(const Point &a, const Point &b) const {
    const Point &p = *this;
    return signum(det(p - a, p - b)) == 0 && signum(dot(p - a, p - b)) <= 0;
}

bool Point::in(const std::vector <Point> &polygon) const {
    const Point &p = *this;
    int n = SIZE(polygon);
    int count = 0;
    for (int i = 0; i < n; ++ i) {
        const Point &a = polygon[i];
        const Point &b = polygon[(i + 1) % n];
        if (p.on(a, b)) {
            return false;
        }
        int t0 = signum(det(a - p, b - p));
        int t1 = signum(a.y - p.y);
        int t2 = signum(b.y - p.y);
        count += t0 > 0 && t1 <= 0 && t2 > 0;
        count -= t0 < 0 && t2 <= 0 && t1 > 0;
    }
    return count != 0;
}
// }}}

int width, height, n;
Point segments[N][2];

void addSegment(const Point &a, const Point &b) {
    segments[n][0] = a;
    segments[n][1] = b;
    n ++;
}

std::vector <Point> points;

int getPointID(const Point &p) {
    return std::lower_bound(ALL(points), p) - points.begin();
}

const int V = N * 2 + N * (N - 1) / 2;
const int E = V * 3 * 2;

int edgeCount, firstEdge[V], to[E], nextEdge[E], next[E];
bool visit[E];
std::set <std::pair <int, int> > geometricEdges;

void addGeometricEdge(int u, int v) {
    if (!geometricEdges.count(std::make_pair(u, v))) {
        geometricEdges.insert(std::make_pair(u, v));
        to[edgeCount] = v;
        nextEdge[edgeCount] = firstEdge[u];
        firstEdge[u] = edgeCount ++;
    }
}

std::vector <double> areas, allAreas;
std::vector <std::vector <Point> > regions;

const int VERTEX = 0;
const int EDGE = 1;
const int REGION = 2;

int getID(int type, int id) {
    if (type == VERTEX) {
        return id;
    }
    if (type == EDGE) {
        return SIZE(points) + id;
    }
    if (type == REGION) {
        return SIZE(points) + (edgeCount >> 1) + id;
    }
    assert(false);
}

double getArea(int id) {
    id -= SIZE(points) + (edgeCount >> 1);
    return id < 0 ? 0 : areas[id];
}

int locate(const Point &p) {
    for (int i = 0; i < edgeCount; i += 2) {
        if (p.on(points[to[i]], points[to[i ^ 1]])) {
            return getID(EDGE, i >> 1);
        }
    }
    int best = -1;
    for (int i = 0; i < SIZE(regions); ++ i) {
        if (p.in(regions[i]) && (best == -1 || allAreas[best] > allAreas[i])) {
            best = i;
        }
    }
    return getID(REGION, best);
}

const int VV = V * 5;

std::vector <int> edges[VV];

void addTopologicalEdge(int u, int v) {
//printf("%d -- %d\n", u, v);
    edges[u].push_back(v);
    edges[v].push_back(u);
}

std::vector <std::string> colorNames;
std::map <std::string, int> colorIDs;

int getColorID(const char *color) {
    if (!colorIDs.count(color)) {
        colorNames.push_back(color);
        int newID = colorIDs.size();
        colorIDs[color] = newID;
    }
    return colorIDs[color];
}

int color[VV];

void paint(const Point &p, const char* c) {
    int start = locate(p);
    int old = color[start];
    int cid = getColorID(c);
    if (old != cid) {
        std::queue <int> queue;
        queue.push(start);
        color[start] = cid;
        while (!queue.empty()) {
            int u = queue.front();
            queue.pop();
            foreach (iter, edges[u]) {
                int v = *iter;
                if (color[v] == old) {
                    color[v] = cid;
                    queue.push(v);
                }
            }
        }
    }
}

int main() {
    scanf("%d%d%d", &width, &height, &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%lf%lf%lf%lf", &segments[i][0].x, &segments[i][0].y, &segments[i][1].x, &segments[i][1].y);
    }
    addSegment(Point(0, 0), Point(width, 0));
    addSegment(Point(width, 0), Point(width, height));
    addSegment(Point(width, height), Point(0, height));
    addSegment(Point(0, height), Point(0, 0));

    // farmland
    for (int i = 0; i < n; ++ i) {
        points.push_back(segments[i][0]);
        points.push_back(segments[i][1]);
        for (int j = 0; j < i; ++ j) {
            if (!parallel(segments[i][0], segments[i][1], segments[j][0], segments[j][1])) {
                Point p = intersect(segments[i][0], segments[i][1], segments[j][0], segments[j][1]);
                if (p.on(segments[i][0], segments[i][1]) && p.on(segments[j][0], segments[j][1])) {
                    points.push_back(p);
                }
            }
        }
    }
    std::sort(ALL(points));
    points.erase(std::unique(ALL(points)), points.end());

    edgeCount = 0;
    memset(firstEdge, -1, sizeof(firstEdge));
    for (int i = 0; i < n; ++ i) {
        std::vector <std::pair <double, int> > pairs;
        const Point &a = segments[i][0];
        const Point &b = segments[i][1];
        for (int j = 0; j < SIZE(points); ++ j) {
            if (points[j].on(a, b)) {
                pairs.push_back(std::make_pair((points[j] - a).norm(), j));
            }
        }
        std::sort(ALL(pairs));
        for (int j = 1; j < SIZE(pairs); ++ j) {
            int u = pairs[j - 1].second;
            int v = pairs[j].second;
            addGeometricEdge(u, v);
            addGeometricEdge(v, u);
        }
    }

    for (int u = 0; u < SIZE(points); ++ u) {
        std::vector <std::pair <double, int> > pairs;
        for (int iter = firstEdge[u]; ~iter; iter = nextEdge[iter]) {
            pairs.push_back(std::make_pair((points[to[iter]] - points[u]).arg(), iter));
        }
        std::sort(ALL(pairs));
        for (int i = 0; i < SIZE(pairs); ++ i) {
            next[pairs[(i + 1) % SIZE(pairs)].second ^ 1] = pairs[i].second;
        }
    }

    std::vector <std::pair <Point, double> > waits;
    memset(visit, 0, sizeof(visit));
    for (int start = 0; start < edgeCount; ++ start) {
        if (!visit[start]) {
            int e = start;
            double totalArea = 0;
            std::vector <Point> region;
            for (; !visit[e]; e = next[e]) {
                visit[e] = true;
                totalArea += det(points[to[e ^ 1]], points[to[e]]);
                region.push_back(points[to[e]]);
            }
            if (signum(totalArea) > 0) {
                regions.push_back(region);
                areas.push_back(totalArea);
                allAreas.push_back(totalArea);
            } else {
                waits.push_back(std::make_pair(region.front(), -totalArea));
            }
        }
    }

    // build graph
    for (int i = 0; i < edgeCount; ++ i) {
        addTopologicalEdge(getID(EDGE, i >> 1), getID(VERTEX, to[i]));
    }
    for (int i = 0; i < SIZE(regions); ++ i) {
        addTopologicalEdge(getID(REGION, i), getID(VERTEX, getPointID(regions[i].front())));
    }
    foreach (iter, waits) {
        const Point &p = iter->first;
        int best = -1;
        for (int i = 0; i < SIZE(regions); ++ i) {
            if (p.in(regions[i]) && (best == -1 || allAreas[best] > allAreas[i])) {
                best = i;
            }
        }
        if (best != -1) {
            areas[best] -= iter->second;
            addTopologicalEdge(getID(REGION, best), getID(VERTEX, getPointID(p)));
        }
    }
//for (int i = 0; i < edgeCount; i += 2) {
//    int a = to[i];
//    int b = to[i ^ 1];
//    printf("(%.2f, %.2f), (%.2f, %.2f)\n", points[a].x, points[a].y, points[b].x, points[b].y);
//}

    getColorID("white");
    getColorID("black");
    getColorID("__COLOR__");
    // main
    for (int i = 0; i < SIZE(points); ++ i) {
        color[getID(VERTEX, i)] = getColorID("black");
    }
    for (int i = 0; i < edgeCount >> 1; ++ i) {
        color[getID(EDGE, i)] = getColorID("black");
    }
    for (int i = 0; i < SIZE(regions); ++ i) {
        color[getID(REGION, i)] = getColorID("white");
    }
    paint(Point(0, 0), "__COLOR__");
    int m;
    scanf("%d", &m);
    while (m --) {
        Point p;
        char buffer[16];
        scanf("%lf%lf%s", &p.x, &p.y, buffer);
        paint(p, buffer);
    }

    std::map <std::string, double> answer;
    for (int i = 0; i < SIZE(points) + (edgeCount >> 1) + SIZE(regions); ++ i) {
        const std::string &name = colorNames[color[i]];
        if (name != "__COLOR__") {
            answer[name] += getArea(i);
        }
    }
    foreach (iter, answer) {
        printf("%s %.8f\n", iter->first.c_str(), 0.5 * iter->second);
    }
    return 0;
}
