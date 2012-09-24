#include <cstdio>
#include <cstring>
using namespace std;

const int N = 100;

int n, x[N], y[N], parent[N];

int find(int i) {
    if (parent[i] != -1) {
        return parent[i] = find(parent[i]);
    }
    return i;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", x + i, y + i);
    }
    memset(parent, -1, sizeof(parent));
    int need = n - 1;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < i; ++ j) {
            if ((x[i] == x[j] || y[i] == y[j]) && find(i) != find(j)) {
                need --;
                parent[find(i)] = find(j);
            }
        }
    }
    printf("%d\n", need);
    return 0;
}
