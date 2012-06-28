// SGU 190 -- Dominos
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 40;
const int M = N * N;
const int DELTA[4][2] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

int n, match[M];
bool mark[N][N], graph[M][M], visit[M];

int id(int i, int j) {
    return i * n + j;
}

bool find(int i) {
    if (visit[i]) {
        return false;
    }
    visit[i] = true;
    for (int j = 0; j < n * n; ++ j) {
        if (graph[i][j]) {
            if (match[j] == -1 || find(match[j])) {
                match[j] = i;
                return true;
            }
        }
    }
    return false;
}

int main() {
    int m;
    scanf("%d%d", &n, &m);
    memset(mark, 0, sizeof(mark));
    for (int i = 0; i < m; ++ i) {
        int x, y;
        scanf("%d%d", &x, &y);
        mark[x - 1][y - 1] = true;
    }
    memset(graph, 0, sizeof(graph));
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            if ((i + j & 1) == 0 && !mark[i][j]) {
                for (int k = 0; k < 4; ++ k) {
                    int x = i + DELTA[k][0];
                    int y = j + DELTA[k][1];
                    if (0 <= x && x < n && 0 <= y && y < n && !mark[x][y]) {
                        graph[id(i, j)][id(x, y)] = true;
                    }
                }
            }
        }
    }
    memset(visit, 0, sizeof(visit));
    memset(match, -1, sizeof(match));
    int matchCount = 0;
    for (int i = 0; i < n * n; ++ i) {
        if (find(i)) {
            matchCount ++;
            memset(visit, 0, sizeof(visit));
        }
    }
    if (matchCount * 2 + m != n * n) {
        puts("No");
    } else {
        puts("Yes");
        vector <pair <int, int> > horizontal, vertical;
        for (int i = 0; i < n * n; ++ i) {
            if (match[i] != -1) {
                if (match[i] % n == i % n) {
                    horizontal.push_back(make_pair(min(match[i] / n, i / n), i % n));
                } else {
                    vertical.push_back(make_pair(i / n, min(match[i] % n, i % n)));
                }
            } 
        }
        printf("%d\n", (int)horizontal.size());
        for (vector <pair <int, int> > :: iterator iter = horizontal.begin(); iter != horizontal.end(); ++ iter) {
            printf("%d %d\n", iter->first + 1, iter->second + 1);
        }
        printf("%d\n", (int)vertical.size());
        for (vector <pair <int, int> > :: iterator iter = vertical.begin(); iter != vertical.end(); ++ iter) {
            printf("%d %d\n", iter->first + 1, iter->second + 1);
        }
    }
    return 0;
}
