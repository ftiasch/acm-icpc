// SGU 344 -- Weed
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>

const int N = 1000;
const int DELTA[4][2] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

int n, m, degree[N][N];
char map[N][N + 1];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", map[i]);
    }
    memset(degree, 0, sizeof(degree));
    std::vector <std::pair <int, int> > queue;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            if (map[i][j] == 'X') {
                degree[i][j] = 2;
                queue.push_back(std::make_pair(i, j));
            }
        }
    }
    for (int head = 0; head < (int)queue.size(); ++ head) {
        int i = queue[head].first;
        int j = queue[head].second;
        for (int k = 0; k < 4; ++ k) {
            int x = i + DELTA[k][0];
            int y = j + DELTA[k][1];
            if (0 <= x && x < n && 0 <= y && y < m) {
                degree[x][y] ++;
                if (degree[x][y] == 2) {
                    queue.push_back(std::make_pair(x, y));
                }
            }
        }
    }
    printf("%d\n", (int)queue.size());
    return 0;
}
