#include <cstdio>
#include <cstring>

const int N = 3000;
const int DELTA_X[8] = {-1, -1, -1,  0,  0,   1,  1, 1};
const int DELTA_Y[8] = {-1,  0,  1, -1,  1,  -1,  0, 1};

int n, m, q, parent[N * N * 2];
bool existed[N][N * 2];

int get_location(int x, int y) {
    return x * m * 2 + y;
}

int find(int u) {
    return ~parent[u] ? parent[u] = find(parent[u]) : u;
}

void merge(int u, int v) {
    if (find(u) != find(v)) {
        parent[find(u)] = find(v);
    }
}

void add(int x, int y) {
    existed[x][y] = true;
    for (int i = 0; i < 8; ++ i) {
        int xx = x + DELTA_X[i];
        int yy = (y + DELTA_Y[i]) % (2 * m);
        if (yy < 0) {
            yy += 2 * m;
        }
        if (0 <= xx && xx < n && existed[xx][yy]) {
            merge(get_location(x, y), get_location(xx, yy));
        }
    }
}

int stamp[N * N * 2];

int main() {
    scanf("%d%d%d", &n, &m, &q);
    int result = 0;
    memset(parent, -1, sizeof(parent));
    memset(existed, 0, sizeof(existed));
    int now = 0;
    memset(stamp, -1, sizeof(stamp));
    while (q --) {
        int x, y;
        scanf("%d%d", &x, &y);
        x --;
        y --;
        now ++;
        bool valid = m > 1;
        for (int i = 0; i < 8; ++ i) {
            int xx = x + DELTA_X[i];
            int yy = (y + DELTA_Y[i]) % (2 * m);
            if (yy < 0) {
                yy += 2 * m;
            }
            if (0 <= xx && xx < n && existed[xx][yy]) {
                stamp[find(get_location(xx, yy))] = now;
            }
        }
        for (int i = 0; i < 8; ++ i) {
            int xx = x + DELTA_X[i];
            int yy = (y + m + DELTA_Y[i]) % (2 * m);
            if (yy < 0) {
                yy += 2 * m;
            }
            if (0 <= xx && xx < n && existed[xx][yy]) {
                valid &= stamp[find(get_location(xx, yy))] != now;
            }
        }
//printf("(%d, %d) %s\n", x + 1, y + 1, valid ? "Yes" : "No");
        if (valid) {
            result ++;
            add(x, y);
            add(x, y + m);
        }
    }
    printf("%d\n", result);
    return 0;
}
