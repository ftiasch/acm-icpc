#include <cstdio>
#include <cstring>
#include <iostream>
#include <queue>
#include <vector>

const int N = 100000;
const int M = 8;
const int THRESHOLD = 2 * M - 1;

char buffer[N + 1];
int color[N], distance[N], color_distance[M][M], mask[N], count[M][1 << M];
std::vector<int> group[M];

int get_distance(int v, int c)
{
    return color_distance[color[v]][c] + (mask[v] >> c & 1);
}

void update(int& diameter, long long& count, int sp, int sp_count)
{
    if (diameter < sp) {
        diameter = sp;
        count = 0;
    }
    if (diameter == sp) {
        count += sp_count;
    }
}

int main()
{
#ifdef LOCAL_JUDGE
    freopen("E.in", "r", stdin);
#endif
    int n;
    while (scanf("%d%s", &n, buffer) == 2) {
        for (int i = 0; i < M; ++ i) {
            group[i].clear();
        }
        for (int i = 0; i < n; ++ i) {
            group[color[i] = buffer[i] - 'a'].push_back(i);
        }
        memset(color_distance, -1, sizeof(color_distance));
        memset(mask, 0, sizeof(*mask) * n);
        for (int c = 0; c < M; ++ c) {
            memset(distance, -1, sizeof(*distance) * n);
            std::queue<int> queue;
            for (auto&& v : group[c]) {
                distance[v] = 0;
                queue.push(v);
            }
            color_distance[c][c] = 0;
            while (!queue.empty()) {
                int u = queue.front();
                queue.pop();
                if (color_distance[color[u]][c] == -1) {
                    color_distance[color[u]][c] = distance[u];
                    for (auto&& v : group[color[u]]) {
                        if (distance[v] == -1) {
                            distance[v] = distance[u] + 1;
                            queue.push(v);
                        }
                    }
                }
                for (auto&& delta : {-1, 1}) {
                    int v = u + delta;
                    if (0 <= v && v < n && distance[v] == -1) {
                        distance[v] = distance[u] + 1;
                        queue.push(v);
                    }
                }
            }
            if (!group[c].empty()) {
                for (int i = 0; i < n; ++ i) {
                    mask[i] |= distance[i] - color_distance[color[i]][c] << c;
                }
            }
        }
        for (int i = 0; i < M; ++ i) {
            for (int j = 0; j < M; ++ j) {
                if (color_distance[i][j] == -1) {
                    color_distance[i][j] = (int)1e9;
                }
            }
        }
        memset(count, 0, sizeof(count));
        int diameter = 0;
        long long result = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = std::max(i - THRESHOLD, 0); j < i; ++ j) {
                int sp = i - j;
                for (int c = 0; c < M; ++ c) {
                    sp = std::min(sp, get_distance(j, c) + 1 + get_distance(i, c));
                }
                update(diameter, result, sp, 1);
            }
            for (int c = 0; c < M; ++ c) {
                for (int msk = 0; msk < 1 << M; ++ msk) {
                    if (count[c][msk]) {
                        int sp = INT_MAX;
                        for (int d = 0; d < M; ++ d) {
                            sp = std::min(sp, (color_distance[c][d] + (msk >> d & 1)) + 1 + get_distance(i, d));
                        }
                        update(diameter, result, sp, count[c][msk]);
                    }
                }
            }
            if (i >= THRESHOLD) {
                int j = i - THRESHOLD;
                count[color[j]][mask[j]] ++;
            }
        }
        std::cout << diameter << " " << result << std::endl;
    }
}
