// POI X Stage II -- Mastermind II(mas)
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 9;

int n, a[N], b[N], code[N][N], result[N];
bool visit[N], has[N][N], found;

void dfs(int i) {
    if (found) {
        return;
    }
    if (i < n) {
        for (int k = 1; k <= 9; ++ k) {
            if (!visit[k - 1]) {
                visit[k - 1] = true;
                result[i] = k;
                dfs(i + 1);
                visit[k - 1] = false;
            }
        }
    } else {
        for (int i = 0; i < n; ++ i) {
            int curA = 0;
            int curB = 0;
            for (int j = 0; j < n; ++ j) {
                if (has[i][result[j] - 1]) {
                    curB += result[j];
                }
                if (code[i][j] == result[j]) {
                    curA += result[j];
                    curB -= result[j];
                }
            }
            if (curA != a[i] || curB != b[i]) {
                return;
            }
        }
        found = true;
        for (int i = 0; i < n; ++ i) {
            printf("%d%c", result[i], i == n - 1? '\n': ' ');
        }
    }
}

int main() {
    scanf("%d", &n);
    memset(has, 0, sizeof(has));
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d", a + i, b + i);
        for (int j = 0; j < n; ++ j) {
            scanf("%d", &code[i][j]);
            has[i][code[i][j] - 1] = true;
        }
    }
    memset(visit, 0, sizeof(visit));
    found = false;
    dfs(0);
    return 0;
}
