// ONTAK 2010 Day 2 -- Keyboard(kla)
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 70;

int n, m;
char letters[N][N + 1], tiles[N][N + 1];

const int V = N * N;
const int E = V << 1;

int edgeCount, firstEdge[V], to[E], nextEdge[E];

const int DELTA[4][2] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

int getID(int x, int y) {
    return x * m + y;
}

void addEdge(int u, int v) {
    to[edgeCount] = v;
    nextEdge[edgeCount] = firstEdge[u];
    firstEdge[u] = edgeCount ++;
}

int result, deepest;

const char VOWELS[6] = {'a', 'e', 'i', 'o', 'u', 'y'};

bool isVowel(char c) {
    for (int i = 0; i < 6; ++ i) {
        if (VOWELS[i] == c) {
            return true;
        }
    }
    return false;
}

bool reachable[V];

bool dfs(int p, int u, int d) {
    reachable[u] = true;
    bool has = isVowel(letters[u / m][u % m]);
    if (has) {
        deepest = max(deepest, d);
    }
    for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
        int v = to[iter];
        if (v != p) {
            bool ret = dfs(u, v, d + 1);
            if (ret) {
                result += 2;
            }
            has |= ret;
        }
    }
    return has;
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", letters[i]);
    }
    for (int i = 0; i < n; ++ i) {
        scanf("%s", tiles[i]);
    }
    edgeCount = 0;
    memset(firstEdge, -1, sizeof(firstEdge));
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            for (int k = 0; k < 4; ++ k) {
                int x_0 = i + DELTA[k][0];
                int y_0 = j + DELTA[k][1];
                int x_1 = x_0 + DELTA[k][0];
                int y_1 = y_0 + DELTA[k][1];
                if (0 <= x_1 && x_1 < n && 0 <= y_1 && y_1 < m) {
                    char t = DELTA[k][0] == 0? '-': '|';
                    if (tiles[x_0][y_0] == t && tiles[x_1][y_1] == t) {
                        addEdge(getID(i, j), getID(x_1, y_1));
                    }
                }
            }
        }
    }
    result = 0;
    deepest = 0;
    memset(reachable, 0, sizeof(reachable));
    dfs(-1, getID(0, 0), 0);
    bool valid = true;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            valid &= !isVowel(letters[i][j]) || reachable[getID(i, j)];
        }
    }
    if (valid) {
        printf("%d\n", result - deepest);
    } else {
        puts("NIE");
    }
    return 0;
}
