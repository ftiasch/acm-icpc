// POI X Stage I -- Printed-Circuit Boards(ply)
#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <algorithm>
using namespace std;

const int N = 10000000;
const int INF = 1000000000;

const int cnt[4] = {0, 1, 1, 2};

char buffer[2];

void update(int &x, int a) {
    x = min(x, a);
}

void print(int *v) {
    printf("{%d, %d, %d, %d}\n", v[0], v[1], v[2], v[3]);
}

int* solve() {
    scanf("%s", buffer);
    if (*buffer == 'X') {
        int* res = (int*)calloc(4, sizeof(int));
        res[0] = INF;
        res[1] = res[2] = 1;
        res[3] = 2;
        return res;
    }
    int size;
    scanf("%d", &size);
    size --;
    if (*buffer == 'S') {
        int* res = solve();
        while (size > 0) {
            size --;
            int* ret = solve();
            int* cur = (int*)calloc(4, sizeof(int));
            for (int i = 0; i < 2; ++ i) {
                for (int j = 0; j < 2; ++ j) {
                    cur[(i << 1) | j] = INF;
                    for (int k = 0; k < 2; ++ k) {
                        update(cur[(i << 1) | j], res[(i << 1) | k] + ret[(k << 1) | j] - k);
                    }
                }
            }
            free(res);
            free(ret);
            res = cur;
        }
        return res;
    }
    int* res = solve();
    while (size > 0) {
        size --;
        int* ret = solve();
        int* cur = (int*)calloc(4, sizeof(int));
        cur[0] = cur[1] = cur[2] = cur[3] = INF;
        for (int i = 0; i < 4; ++ i) {
            for (int j = 0; j < 4; ++ j) {
                update(cur[i | j], res[i] + ret[j]);
            }
        }
        free(res);
        free(ret);
        res = cur;
    }
    int* cur = (int*)calloc(4, sizeof(int));
    for (int i = 0; i < 4; ++ i) {
        cur[i] = INF;
        for (int j = 0; j < 4; ++ j) {
            if ((i | j) == 3) {
                update(cur[i], cnt[i] + res[j]);
            }
        }
    }
    free(res);
    return cur;
}

int main() {
    int* ret = solve();
    printf("%d\n", *min_element(ret, ret + 4));
    return 0;
}
