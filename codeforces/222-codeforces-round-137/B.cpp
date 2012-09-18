// Codeforces Round #137
// Problem B -- Cosmic Tables
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 1000;

int n, m, k, value[N][N], rowOrder[N], columnOrder[N];

int main() {
    scanf("%d%d%d", &n, &m, &k);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            scanf("%d", &value[i][j]);
        }
    }
    for (int i = 0; i < n; ++ i) {
        rowOrder[i] = i;
    }
    for (int j = 0; j < m; ++ j) {
        columnOrder[j] = j;
    }
    while (k > 0) {
        k --;
        char type[2];
        int x, y;
        scanf("%s%d%d", type, &x, &y);
        x --;
        y --;
        if (*type == 'c') {
            swap(columnOrder[x], columnOrder[y]);
        } 
        if (*type == 'r') {
            swap(rowOrder[x], rowOrder[y]);
        }
        if (*type == 'g') {
            printf("%d\n", value[rowOrder[x]][columnOrder[y]]);
        }
    }
    return 0;
}
