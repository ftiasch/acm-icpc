// Codeforces Beta Round #75
// Problem C -- Ski Base
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 100000;
const int MOD = 1000000009;

int n, m;

int parent[N];

int find(int i) {
    if (parent[i] != i) {
        parent[i] = find(parent[i]);
    }
    return parent[i];
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        parent[i] = i;
    }
    int result = 1;
    for (int i = 0; i < m; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b);
        a --;
        b --;
        if (find(a) == find(b)) {
            result = result * 2 % MOD;
        } else {
            parent[find(a)] = find(b);
        }
        printf("%d\n", (result + MOD - 1) % MOD);
    }
    return 0;
}
