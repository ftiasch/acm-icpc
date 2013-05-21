// Codeforces Round #168 
// Problem E -- Mirror Room
#include <cstdio>
#include <cstring>
#include <map>
#include <set>
#include <vector>
#include <iostream>
#include <algorithm>

#define ALL(v) (v).begin(), (v).end()
#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

struct Point {
    int x, y;

    Point() : x(0), y(0) {}
    Point(int x, int y) : x(x), y(y) {}

    Point transform() const {
        return Point(x + y, x - y);
    }

    Point inverse() const {
        return Point(x + y >> 1, x - y >> 1);
    }
};

bool operator == (const Point &a, const Point &b) {
    return a.x == b.x && a.y == b.y;
}

bool operator < (const Point &a, const Point &b) {
    return a.x < b.x || (a.x == b.x && a.y < b.y);
}

int n, m;
std::vector <Point> blocks;

std::map <int, std::vector <int> > indices[2];

typedef long long Hash;

Hash get_hash(const Point &p, const Point &d) {
    Hash ret = p.x;
    ret *= m + 2;
    ret += p.y;
    ret <<= 1;
    ret += d.x + 1 >> 1;
    ret <<= 1;
    ret += d.y + 1 >> 1;
    return ret;
}

bool has_block(int x, int y) {
    return std::binary_search(ALL(blocks), Point(x, y));
}

std::set <Hash> state_hashes;

const int LARGE = 1000000;

struct Event {
    int x_1, x_2, y, d;

    Event(int x_1, int x_2, int y, int d) : x_1(x_1), x_2(x_2), y(y + LARGE >> 1), d(d) {}
};

bool operator < (const Event &a, const Event &b) {
    return a.y < b.y || (a.y == b.y && a.d < b.d);
}

int delta[LARGE], count[LARGE];

int get_id(int l, int r) {
    return l + r | (l != r);
}

void cover(int l, int r, int a, int b, int c) {
    if (b < l || r < a) {
        return;
    }
    int id = get_id(l, r);
    int m = l + r >> 1;
    if (a <= l && r <= b) {
        delta[id] += c;
    } else {
        cover(l, m, a, b, c);
        cover(m + 1, r, a, b, c);
    }
    count[id] = l == r ? 0 : count[get_id(l, m)] + count[get_id(m + 1, r)];
    if (delta[id]) {
        count[id] = r - l + 1;
    }
}

int main() {
    int block_count;
    scanf("%d%d%d", &n, &m, &block_count);
    for (int i = 0; i <= n + 1; ++ i) {
        blocks.push_back(Point(i, 0));
        blocks.push_back(Point(i, m + 1));
    }
    for (int j = 0; j <= m + 1; ++ j) {
        blocks.push_back(Point(0, j));
        blocks.push_back(Point(n + 1, j));
    }
    while (block_count --) {
        int x, y;
        scanf("%d%d", &x, &y);
        blocks.push_back(Point(x, y));
    }
    std::sort(ALL(blocks));
    blocks.erase(std::unique(ALL(blocks)), blocks.end());
    int s_x, s_y;
    char names[3];
    scanf("%d%d%s", &s_x, &s_y, names);
    foreach (iter, blocks) {
        Point p(iter->transform());
        indices[0][p.x].push_back(p.y);
        indices[1][p.y].push_back(p.x);
    }
    for (int t = 0; t < 2; ++ t) {
        foreach (iter, indices[t]) {
            std::sort(ALL(iter->second));
        }
    }
    Point now(s_x, s_y);
    Point now_d(names[0] == 'S' ? 1 : -1, 
                names[1] == 'E' ? 1 : -1);
    std::vector <Event> events[2];
    while (true) {
//printf("(%d, %d), (%d, %d)\n", now.x, now.y, now_d.x, now_d.y);
        Hash hash = get_hash(now, now_d);
        if (state_hashes.count(hash)) {
            break;
        }
        state_hashes.insert(hash);
        Point p(now.transform());
        Point q(now_d.transform());
        int t = q.y != 0;
        if (t) {
            std::swap(p.x, p.y);
            std::swap(q.x, q.y);
        }
        std::vector <int> &v = indices[t ^ 1][p.y];
        Point r(*(q.x > 0 ? upper_bound(ALL(v), p.x) : lower_bound(ALL(v), p.x) - 1) - q.x, p.y);
        if (t) {
            std::swap(p.x, p.y);
            std::swap(q.x, q.y);
            std::swap(r.x, r.y);
        }
        int o = p.x & 1;
        int a = p.x >> 1;
        int b = r.x >> 1;
        if (a > b) {
            std::swap(a, b);
        }
        events[o].push_back(Event(a, b, std::max(p.y, r.y), -1));
        events[o].push_back(Event(a, b, std::min(p.y, r.y) - 2, 1));
        now = r.inverse();
        bool hx = has_block(now.x + now_d.x, now.y);
        bool hy = has_block(now.x, now.y + now_d.y);
        if (hx ^ hy) {
            if (hx) {
                now_d.x *= -1;
                now.y += now_d.y;
            } else {
                now_d.y *= -1;
                now.x += now_d.x;
            }
        } else {
            now_d.x *= -1;
            now_d.y *= -1;
        }
    }
    long long answer = 0;
    for (int t = 0; t < 2; ++ t) {
        int max = std::max(n, m);
        memset(delta, 0, sizeof(delta));
        memset(count, 0, sizeof(count));
        std::sort(ALL(events[t]));
        for (int i = 0; i < (int)events[t].size(); ++ i) {
            Event &e = events[t][i];
            if (i) {
                answer += (long long)count[get_id(0, max)] * (e.y - events[t][i - 1].y);
            }
            cover(0, max, e.x_1, e.x_2, e.d);
        }
    }
    std::cout << answer << std::endl;
    return 0;
}
