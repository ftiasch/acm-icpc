// Codeforces Round #139
// Problem D -- Snake
#include <cctype>
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <set>
#include <queue>
using namespace std;

struct Point {
    int x, y;

    Point(int x, int y): x(x), y(y) {}

    Point &operator +=(const Point &o) {
        x += o.x;
        y += o.y;
        return *this;
    }

    unsigned hashCode() {
        return x << 4 | y;
    }
};

bool operator ==(const Point &a, const Point &b) {
    return a.x == b.x && a.y == b.y;
}

Point operator +(Point a, const Point &b) {
    return a += b;
}

const Point DELTA[4] = {Point(-1, 0), Point(0, -1), Point(0, 1), Point(1, 0)};

const int N = 15;

int n, m, length;
char map[N][N + 1];

struct Snake {
    int mask;
    Point head;

    Snake(int mask, Point head): mask(mask), head(head) {}

    Snake(vector <Point> snake): mask(0), head(snake.front()) {
        for (int i = length - 2; i >= 0; -- i) {
            mask <<= 2;
            for (int j = 0; j < 4; ++ j) {
                if (snake[i] + DELTA[j] == snake[i + 1]) {
                    mask |= j;
                }
            }
        }
    }

    Snake go(int k) {
        return Snake((mask << 2 | (3 ^ k)) & (1 << (length - 1 << 1)) - 1,
                     head + DELTA[k]);
    }

    bool valid() {
        if (head.x < 0 || head.x >= n || head.y < 0 || head.y >= m
                || map[head.x][head.y] == '#') {
            return false;
        }
        Point p = head;
        for (int i = 0; i < length - 1; ++ i) {
            p += DELTA[mask >> (i << 1) & 3];
            if (head == p) {
                return false;
            }
        }
        return true;
    }

    unsigned hashCode() {
        return mask << 8 | head.hashCode();
    }
};
int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", map[i]);
    }
    vector <Point> position(9, Point(n, m));
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            if (isdigit(map[i][j])) {
                position[map[i][j] - '1'] = Point(i, j);
                map[i][j] = '.';
            }
        }
    }
    while (position.back() == Point(n, m)) {
        position.pop_back();
    }
    length = position.size();
    Snake snake(position);
    queue <pair <int, Snake> > q;
    q.push(make_pair(0, snake));
    set <unsigned> hash;
    hash.insert(snake.hashCode());
    while (!q.empty()) {
        int d = q.front().first + 1;
        Snake s = q.front().second;
        q.pop();
        for (int i = 0; i < 4; ++ i) {
            Snake t = s.go(i);
            if (t.valid() && hash.find(t.hashCode()) == hash.end()) {
                if (map[t.head.x][t.head.y] == '@') {
                    printf("%d\n", d);
                    return 0;
                }
                q.push(make_pair(d, t));
                hash.insert(t.hashCode());
            }
        }
    }
    puts("-1");
    return 0;
}
