// SGU 138 -- Games of Chess
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 100;
const int M = 10000;

int n, degree[N], order[N], win[M], lose[M];

bool compare(int i, int j) {
    return degree[i] > degree[j];
}

int main() {
    scanf("%d", &n);
    int game_count = 0;
    for (int i = 0; i < n; ++ i) {
        scanf("%d", degree + i);
        game_count += degree[i];
        order[i] = i;
    }
    game_count >>= 1;
    sort(order, order + n, compare);
    memset(lose, -1, sizeof(lose));
    int j = 0;
    for (int i = 0; i < game_count; ++ i) {
        while (degree[order[j]] == 0) {
            j ++;
        }
        if (degree[order[j]] <= 1) {
            lose[i] = order[j];
            i --;
        } else {
            win[i] = order[j];
        }
        degree[order[j]] --;
    }
    for (int i = 0; i < game_count; ++ i) {
        if (lose[i] == -1) {
            while (degree[order[j]] == 0) {
                j ++;
            }
            lose[i] = order[j];
            degree[order[j]] --;
        }
    }
    printf("%d\n", game_count);
    for (int i = 0; i < game_count; ++ i) {
        printf("%d %d\n", win[i] + 1, lose[i] + 1);
    }
    return 0;
}
