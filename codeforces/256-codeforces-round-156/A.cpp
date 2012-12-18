// Codeforces Round #156
// Problem A -- Almost Arithmetical Progression
#include <cstdio>
#include <cstring>
#include <map>
#include <algorithm>

const int N = 4000;

int n, a[N], length[N][N];

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    std::map <int, int> map;
    int answer = 1;
    for (int i = 0; i < n; ++ i) {
        for (int j = i + 1; j < n; ++ j) {
            length[i][j] = 2;
            if (map.count(a[j])) {
                length[i][j] = std::max(length[i][j], length[map[a[j]]][i] + 1);
            }
            answer = std::max(answer, length[i][j]);
        }
        map[a[i]] = i;
    }
    printf("%d\n", answer);
    return 0;
}
