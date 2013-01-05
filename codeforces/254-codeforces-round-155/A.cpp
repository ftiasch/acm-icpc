// Codeforces Round #155
// Problem A -- Cards with Numbers
#include <cstdio>
#include <vector>

std::vector <int> indices[5001];

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    int n;
    scanf("%d", &n);
    n <<= 1;
    for (int i = 1; i <= n; ++ i) {
        int a;
        scanf("%d", &a);
        indices[a].push_back(i);
    }
    for (int i = 1; i <= 5000; ++ i) {
        if (indices[i].size() & 1) {
            puts("-1");
            return 0;
        }
    }
    for (int i = 1; i <= 5000; ++ i) {
        int m = indices[i].size();
        for (int j = 0; j < m; j += 2) {
            printf("%d %d\n", indices[i][j], indices[i][j + 1]);
        }
    }
    return 0;
}
