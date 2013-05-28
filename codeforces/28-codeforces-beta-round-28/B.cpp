// Codeforces Beta Round #28 
// Problem B -- pSort
#include <cstdio>
#include <cstring>

const int N = 100;

int n, d[N], p[N], parent[N], count[N][N];

int find(int i) {
    if (parent[i] != i) {
        parent[i] = find(parent[i]);
    }
    return parent[i];
}

void merge(int i, int j) {
    if (find(i) != find(j)) {
        parent[find(i)] = find(j);
    }
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", p + i);
        p[i] --;
        parent[i] = i;
    }
    for (int i = 0; i < n; ++ i) {
        scanf("%d", d + i);
    }
    for (int i = 0; i < n; ++ i) {
        if (i >= d[i]) {
            merge(i, i - d[i]);
        }
        if (i + d[i] < n) {
            merge(i, i + d[i]);
        }
    }
    memset(count, 0, sizeof(count));
    for (int i = 0; i < n; ++ i) {
        count[find(i)][i] ++;
        count[find(i)][p[i]] --;
    }
    bool valid = true;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            valid &= count[i][j] == 0;
        }
    }
    puts(valid ? "YES" : "NO");
    return 0;
}
