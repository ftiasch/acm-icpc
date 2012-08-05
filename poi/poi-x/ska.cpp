// POI X Stage III -- Treasure(ska)
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <algorithm>
using namespace std;

const int N = 222;
const int M = N * N;

int n, edgeCount, edgeID[N][N], to[M], length[M], twin[M], next[M], g, m, startEdge[N], cycle[N];
bool hasInfo[N][N], meet[N][N], gather[N], info[N];
vector <int> delta[N][N];

int gcd(int a, int b) {
    return b == 0? a: gcd(b, a % b);
}

int main() {
    scanf("%d", &n);
    edgeCount = 0;
    memset(edgeID, -1, sizeof(edgeID));
    for (int i = 0; i < n; ++ i) {
        int size;
        scanf("%d", &size);
        vector <int> adjacent;
        while (size > 0) {
            size --;
            int j, l;
            scanf("%d%d", &j, &l);
            adjacent.push_back(edgeCount);
            to[edgeCount] = --j;
            length[edgeCount] = l;
            edgeID[i][j] = edgeCount ++;
        }
        for (int k = 0; k < (int)adjacent.size(); ++ k) {
            next[adjacent[(k + 1) % (int)adjacent.size()]] = adjacent[k];
        }
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            if (edgeID[i][j] != -1) {
                twin[edgeID[i][j]] = edgeID[j][i];
            }
        }
    }
    scanf("%d%d", &g, &m);
    memset(hasInfo, 0, sizeof(hasInfo));
    for (int i = 0; i < g; ++ i) {
        int a, b, size;
        scanf("%d%d%d", &a, &b, &size);
        startEdge[i] = edgeID[-- a][-- b];
        while (size > 0) {
            size --;
            int x;
            scanf("%d", &x);
            hasInfo[i][-- x] = true;
        }
        cycle[i] = 0;
        int e = startEdge[i];
        do {
            delta[i][to[twin[e]]].push_back(cycle[i]);
            cycle[i] += length[e];
            e = next[twin[e]];
        } while (e != startEdge[i]);
    }
    memset(meet, 0, sizeof(meet));
    for (int i = 0; i < g; ++ i) {
        for (int j = 0; j < i; ++ j) {
            for (int k = 0; k < n && !meet[i][j]; ++ k) {
                int d = gcd(cycle[i], cycle[j]);
                for (vector <int> :: iterator x = delta[i][k].begin(); x != delta[i][k].end() && !meet[i][j]; ++ x) {
                    for (vector <int> :: iterator y = delta[j][k].begin(); y != delta[j][k].end() && !meet[i][j]; ++ y) {
                        meet[i][j] |= (*x - *y) % d == 0;
                    }
                }
            }
        }
    }
    for (int i = 0; i < g; ++ i) {
        meet[i][i] = true;
        for (int j = 0; j < i; ++ j) {
            meet[j][i] = meet[i][j];
        }
    }
    for (int k = 0; k < g; ++ k) {
        for (int i = 0; i < g; ++ i) {
            for (int j = 0; j < g; ++ j) {
                meet[i][j] |= meet[i][k] && meet[k][j];
            }
        }
    }
    memset(gather, 0, sizeof(gather));
    int result = 0;
    for (int i = 0; i < g; ++ i) {
        memset(info, 0, sizeof(info));
        for (int j = 0; j < g; ++ j) {
            if (meet[i][j]) {
                for (int k = 0; k < m; ++ k) {
                    info[k] |= hasInfo[j][k];
                }
            }
        }
        gather[i] = true;
        for (int j = 0; j < m; ++ j) {
            if (!info[j]) {
                gather[i] = false;
            }
        }
        if (gather[i]) {
            result ++;
        }
    }
    printf("%d\n", result);
    for (int i = 0; i < g; ++ i) {
        if (gather[i]) {
            printf("%d\n", i + 1);
        }
    }
    return 0;
}
