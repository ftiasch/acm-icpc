// SGU 229 -- Divide and conquer
#include <cstdio>
#include <cstring>
#include <complex>
#include <algorithm>

const int N = 20;

typedef std::complex <int> Point;

int n;
char map[N][N + 1];

int next[N * N], back[N * N];

int get_id(int x, int y) {
    return x * n + y;
}

int parent[N * N], size[N * N], color[N][N];
bool visit[N * N];

int find(int i) {
    if (parent[i] == -1) {
        return i;
    }
    return parent[i] = find(parent[i]);
}

void merge(int i, int j) {
    if (find(i) != find(j)) {
        size[find(j)] += size[find(i)];
        parent[find(i)] = find(j);
    }
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", map[i]);
    }
    Point rotate(1, 0);
    for (int r = 0; r < 4; ++ r) {
        for (int dx = -2 * n; dx <= 2 * n; ++ dx) {
            for (int dy = -2 * n; dy <= 2 * n; ++ dy) {
                Point translate = Point(dx, dy);
                memset(next, -1, sizeof(next));
                memset(back, -1, sizeof(back));
                memset(parent, -1, sizeof(parent));
                std::fill(size, size + n * n, 1);
                for (int i = 0; i < n; ++ i) {
                    for (int j = 0; j < n; ++ j) {
                        if (map[i][j] == '0') {
                            continue;
                        }
                        Point p = Point(i, j) * rotate + translate;
                        int x = p.real();
                        int y = p.imag();
                        if (0 <= x && x < n && 0 <= y && y < n && map[x][y] == '1') {
                            next[get_id(i, j)] = get_id(x, y);
                            back[get_id(x, y)] = get_id(i, j);
                            merge(get_id(i, j), get_id(x, y));
                        }
                    }
                }
                bool valid = true;
                for (int i = 0; i < n; ++ i) {
                    for (int j = 0; j < n; ++ j) {
                        if (map[i][j] == '1') {
                            valid &= !(size[find(get_id(i, j))] & 1);
                        }
                    }
                }
                if (valid) {
                    puts("YES");
                    memset(visit, 0, sizeof(visit));
                    memset(color, 0, sizeof(color));
                    for (int i = 0; i < n; ++ i) {
                        for (int j = 0; j < n; ++ j) {
                            if (map[i][j] == '1') {
                                int id = get_id(i, j);
                                if (visit[id]) {
                                    continue;
                                }
                                while (back[id] != -1) {
                                    id = back[id];
                                    if (id == get_id(i, j)) {
                                        break;
                                    }
                                }
                                int c = 1;
                                while (id != -1 && !visit[id]) {
                                    visit[id] = true;
                                    color[id / n][id % n] = c;
                                    id = next[id];
                                    c ^= 1;
                                }
                            }
                        }
                    }
                    for (int i = 0; i < n; ++ i) {
                        for (int j = 0; j < n; ++ j) {
                            printf("%d", color[i][j]);
                        }
                        puts("");
                    }
                    return 0;
                }
            }
        }
        rotate *= Point(0, 1);
    }
    puts("NO");
    return 0;
}
