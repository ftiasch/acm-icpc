// SGU 304 -- Mars Stomatology
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

#define ALL(v) (v).begin(), (v).end()
#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

const int N = 600;

int n, m, budget, gum_cost[N], tooth_cost[N], minimum[N + 1][N + 1];
std::vector <int> teeth[N];

bool by_cost(int i, int j) {
    return tooth_cost[i] < tooth_cost[j];
}

void update(int &x, int a) {
    if (x == -1 || a < x) {
        x = a;
    }
}

int main() {
    scanf("%d%d%d", &n, &m, &budget);
    for (int i = 0; i < m; ++ i) {
        scanf("%d", gum_cost + i);
    }
    for (int i = 0; i < n; ++ i) {
        int gum;
        scanf("%d%d", tooth_cost + i, &gum);
        teeth[-- gum].push_back(i);
    }
    for (int i = 0; i < m; ++ i) {
        std::sort(ALL(teeth[i]), by_cost);
    }
    memset(minimum, -1, sizeof(minimum));
    minimum[0][0] = 0;
    for (int i = 0; i < m; ++ i) {
        for (int j = 0; j <= n; ++ j) {
            if (minimum[i][j] != -1) {
                update(minimum[i + 1][j], minimum[i][j]);
                int sum = gum_cost[i];
                int count = 0;
                foreach (iter, teeth[i]) {
                    count ++;
                    sum += tooth_cost[*iter];
                    update(minimum[i + 1][j + count], minimum[i][j] + sum);
                }
            }
        }
    }
    while (minimum[m][n] > budget) {
        n --;
    }
    printf("%d\n", n);
    std::vector <int> answer;
    while (m --) {
        if (minimum[m][n] != minimum[m + 1][n]) {
            int sum = gum_cost[m];
            int count = 0;
            foreach (iter, teeth[m]) {
                count ++;
                sum += tooth_cost[*iter];
                answer.push_back(*iter);
                if (minimum[m][n - count] + sum == minimum[m + 1][n]) {
                    n -= count;
                    break;
                }
            }
        }
    }
    foreach (iter, answer) {
        printf("%d ", *iter + 1);
    }
    puts("");
    return 0;
}
