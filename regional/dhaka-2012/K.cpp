// Dhaka 2012 Problem K -- Beauty of Regular Polyhedron
#include <cassert>
#include <cstdio>
#include <cstring>
#include <climits>
#include <vector>
#include <utility>
#include <iostream>
#include <algorithm>

using std::vector;
using std::ostream;
using std::min;
using std::pair;
using std::make_pair;

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i) 

// {{{ class 2D Point
struct Point2 {
    int x, y;

    Point2(int x, int y) : x(x), y(y) {}

    Point2 &operator +=(const Point2 &o) {
        x += o.x;
        y += o.y;
        return *this;
    }

    Point2 &operator -=(const Point2 &o) {
        x -= o.x;
        y -= o.y;
        return *this;
    }
};

bool operator <(const Point2 &a, const Point2 &b) {
    return a.x < b.x || a.x == b.x && a.y < b.y;
}

bool operator !=(const Point2 &a, const Point2 &b) {
    return a < b || b < a;
}

bool operator ==(const Point2 &a, const Point2 &b) {
    return !(a != b);
}

ostream &operator <<(ostream &out, const Point2 &p) {
    return out << "(" << p.x << ", " << p.y << ")";
}

Point2 operator +(Point2 a, const Point2 &b) {
    return a += b;
}

Point2 operator -(Point2 a, const Point2 &b) {
    return a -= b;
}
// }}}

// {{{ class View
struct View {
    vector <Point2> grids;

    View() {}

    View(vector <Point2> grids) : grids(grids) {}

    int size() const {
        return grids.size();
    }

    const Point2 &operator[](int i) const {
        return grids[i];
    }

    View &operator +=(const Point2 &p) {
        grids.push_back(p);
        return *this;
    }

    View minimize() const {
        vector <Point2> new_grids;
        Point2 standard = *std::min_element(grids.begin(), grids.end());
        foreach (iter, grids) {
            new_grids.push_back(*iter - standard);
        }
        std::sort(new_grids.begin(), new_grids.end());
        return new_grids;
    }

    int find(const Point2 &o) const {
        int id = std::find(grids.begin(), grids.end(), o) - grids.begin();
        return id < size() ? id : -1;
    }
};

bool operator ==(const View &a, const View &b) {
    if (a.size() != b.size()) {
        return false;
    }
    int n = a.size();
    for (int i = 0; i < n; ++ i) {
        if (a[i] != b[i]) {
            return false;
        }
    }
    return true;
}

View operator +(View a, const Point2 &b) {
    return a += b;
}

ostream &operator <<(ostream &out, const View &v) {
    int min_x = INT_MAX;
    int min_y = INT_MAX;
    int n = v.size();
    for (int i = 0; i < n; ++ i) {
        min_x = min(min_x, v[i].x);
        min_y = min(min_y, v[i].y);
    }
    for (int j = 5; j >= 0; -- j) {
        for (int i = 0; i < 6; ++ i) {
            bool found = false;
            foreach (iter, v.grids) { 
                found |= iter->x - min_x == i && iter->y - min_y == j;
            }
            out << (found ? '*' : '.');
        }
        out << std::endl;
    }
    return out;
}

// }}}

// {{{ class 3D Point
struct Point3 {
    int x, y, z;

    Point3(int x, int y, int z) : x(x), y(y), z(z) {}
};

bool operator ==(const Point3 &a, const Point3 &b) {
    return a.x == b.x && a.y == b.y && a.z == b.z;
}

ostream &operator <<(ostream &out, const Point3 &p) {
    return out << "(" << p.x << ", " << p.y << ", " << p.z << ")";
}

Point3 det(const Point3 &a, const Point3 &b) {
    return Point3(a.y * b.z - a.z * b.y,
                  a.z * b.x - a.x * b.z,
                  a.x * b.y - a.y * b.x);
}

Point3 rotate(const Point3 &z, Point3 p, int times = 1) {
    while (times --) {
        p = det(p, z);
    }
    return p;
}
// }}}

// {{{ class Coordinate
struct Coordinate {
    Point3 x, y;

    Coordinate() : x(0, 0, 0), y(0, 0, 0) {}
    Coordinate(const Point3 &x, const Point3 &y) : x(x), y(y) {}

    Coordinate transform(int type) const {
        if (type == 0) {
            return Coordinate(rotate(y, x, 3), y);
        }
        if (type == 1) {
            return Coordinate(x, rotate(x, y));
        }
        if (type == 2) {
            return Coordinate(x, rotate(x, y, 3));
        }
        if (type == 3) {
            return Coordinate(rotate(y, x), y);
        }
        assert(false);
    }

    Point3 z() const {
        return det(x, y);
    }
};

ostream &operator <<(ostream &out, const Coordinate &c) {
    return out << c.x << ", " << c.y << ", " << c.z();
}

bool operator ==(const Coordinate &a, const Coordinate &b) {
    return a.x == b.x && a.y == b.y;
}

const Point2 DELTA[4] = {Point2(-1, 0), Point2(0, -1), Point2(0, 1), Point2(1, 0)};

vector <View> views;
vector <vector <pair <int, int> > > edges;

const Point3 DIRECTIONS[6] = {Point3(-1, 0, 0), Point3(1, 0, 0), Point3(0, -1, 0), Point3(0, 1, 0), Point3(0, 0, -1), Point3(0, 0, 1)};
// }}}

namespace Folder {
    bool success;
    View view;
    vector <Coordinate> coordinates;
    vector <pair <int, int> > edges;
    bool visit[6];

    void dfs(int i, const Coordinate &current) {
        if (!visit[i]) {
            visit[i] = true;
            coordinates[i] = current;
            for (int k = 0; k < 4; ++ k) {
                Point2 p = view[i] + DELTA[k];
                int j = view.find(p);
                if (j != -1) {
                    dfs(j, current.transform(k));
                }
            }
        }
    }

    void fold(const View &v) {
        view = v;
        coordinates.resize(6);
        memset(visit, 0, sizeof(visit));
        dfs(0, Coordinate(Point3(1, 0, 0), Point3(0, 1, 0)));
        success = true;
        for (int i = 0; i < 6; ++ i) {
            bool found = false;
            foreach (iter, coordinates) {
                found |= iter->z() == DIRECTIONS[i];
            }
            success &= found;
        }
        if (success) {
            edges.clear();
            for (int i = 0; i < 6; ++ i) {
                for (int k = 0; k < 4; ++ k) {
                    Coordinate c = coordinates[i].transform(k);
                    int j = 0;
                    while (j < 6 && !(coordinates[j].z() == c.z())) {
                        j ++;
                    }
                    assert(j < 6);
                    for (int kk = 0; kk < 4; ++ kk) {
                        if (coordinates[j].transform(kk).z() == coordinates[i].z()) {
                            edges.push_back(make_pair(i << 2 | k, j << 2 | kk));
                        }
                    }
                }
            }
        }
    }
}

void generate_views(const View &current) {
    if (current.size() < 6) {
        foreach (iter, current.grids) {
            for (int i = 0; i < 4; ++ i) {
                Point2 p = *iter + DELTA[i];
                if (current.find(p) == -1) {
                    generate_views(current + p);
                }
            }
        }
    } else {
        View view = current.minimize();
        if (std::find(views.begin(), views.end(), view) == views.end()) {
            Folder::fold(view);
            if (Folder::success) {
                views.push_back(view);
            }
        }
    }
}

int parent[6];

int find_parent(int i) {
    if (parent[i] == -1) {
        return i;
    }
    return parent[i]  = find_parent(parent[i]);
}

void merge(int u, int v) {
    if (find_parent(u) != find_parent(v)) {
        parent[find_parent(u)] = find_parent(v);
    }
}

bool has_port(int mask, int d) {
    if (mask == 0) {
        return d == 1 || d == 2;
    }
    if (mask == 1) {
        return d == 0 || d == 3;
    }
    if (mask == 2) {
        return d == 0 || d == 2;
    }
    if (mask == 3) {
        return d == 2 || d == 3;
    }
    if (mask == 4) {
        return d == 1 || d == 3;
    }
    if (mask == 5) {
        return d == 0 || d == 1;
    }
    assert(false);
}

int main() {
    generate_views(vector <Point2>(1, Point2(0, 0)));
    foreach (iter, views) {
        Folder::fold(*iter);
        edges.push_back(Folder::edges);
    }
    int test_count;
    scanf("%d", &test_count);
    for (int t = 1; t <= test_count; ++ t) {
        int n, m;
        scanf("%d%d", &m, &n);
        int map[n][m];
        for (int j = m - 1; j >= 0; -- j) {
            for (int i = 0; i < n; ++ i) {
                scanf("%d", &map[i][j]);
            }
        }
        int answer = 0;
        for (int k = 0; k < (int)views.size(); ++ k) {
            View &view = views[k];
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    bool valid = true;
                    int types[6];
                    for (int p = 0; p < 6; ++ p) {
                        int x = i + view[p].x;
                        int y = j + view[p].y;
                        valid &= 0 <= x && x < n && 0 <= y && y < m;
                        if (valid) {
                            types[p] = map[x][y];
                        }
                    }
                    if (valid) {
                        int degree[6];
                        memset(degree, 0, sizeof(degree));
                        memset(parent, -1, sizeof(parent));
                        foreach (iter, edges[k]) {
                            int u = iter->first;
                            int v = iter->second;
                            if (has_port(types[u >> 2], u & 3) && has_port(types[v >> 2], v & 3)) {
                                degree[u >> 2] ++;
                                degree[v >> 2] ++;
                                merge(u >> 2, v >> 2);
                            }
                        }
                        for (int p = 0; p < 6; ++ p) {
                            valid &= degree[p] == 4;
                            valid &= find_parent(p) == find_parent(0);
                        }
                    }
                    answer += valid;
                }
            }
        }
        printf("Case %d: %d\n", t, answer);
    }
    return 0;
}
