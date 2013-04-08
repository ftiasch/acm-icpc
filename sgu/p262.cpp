// SGU 262 -- Symbol Recognition
#include <cstdio>
#include <cstring>
#include <climits>
#include <utility>
#include <vector>
#include <map>
#include <algorithm>

const int K = 6;
const int N = 10;

int n, m, k;
char screen[K + 1][N][N + 1];

typedef std::vector <int> Vector;
typedef std::pair <std::pair <int, int>, Vector> Data;

std::map <Vector, int> memory;
std::map <Vector, Data> choices;

int solve(Vector v) {
    if (v[k - 1] == k - 1) {
        return 0;
    }
    if (memory.find(v) == memory.end()) {
        int &ret = memory[v];
        ret = INT_MAX;
        Data &choice = choices[v];
        for (int x = 0; x < n; ++ x) {
            for (int y = 0; y < m; ++ y) {
                Vector nv;
                int count = 0;
                for (int i = 0; i < k; ++ i) {
                    int j = 0;
                    while (j < i && !(v[i] == v[j] && screen[i][x][y] == screen[j][x][y])) {
                        j ++;
                    }
                    nv.push_back(j < i ? nv[j] : count ++);
                }
                if (nv != v && solve(nv) + 1 < ret) {
                    ret = solve(nv) + 1;
                    choice = std::make_pair(std::make_pair(x, y), nv);
                }
            }
        }
    }
    return memory[v];
}

void construct(Vector v) {
    if (v[k - 1] < k - 1) {
        Data &choice = choices[v];
        screen[k][choice.first.first][choice.first.second] = '1';
        construct(choice.second);
    }
}

int main() {
    scanf("%d%d%d", &n, &m, &k);
    for (int i = 0; i < k; ++ i) {
        for (int x = 0; x < n; ++ x) {
            scanf("%s", screen[i][x]);
        }
    }
    printf("%d\n", solve(Vector(k, 0)));
    for (int x = 0; x < n; ++ x) {
        for (int y = 0; y < m; ++ y) {
            screen[k][x][y] = '0';
        }
        screen[k][x][m] = '\0';
    }
    construct(Vector(k, 0));
    for (int x = 0; x < n; ++ x) {
        puts(screen[k][x]);
    }
    return 0;
}
