#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 100;

int n, m;
bool success[N];
char marks[N][N + 1];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%s", marks[i]);
    }
    memset(success, 0, sizeof(success));
    for (int j = 0; j < m; ++ j) {
        int best = 0;
        for (int i = 0; i < n; ++ i) {
            best = max(best, marks[i][j] - '0');
        }
        for (int i = 0; i < n; ++ i) {
            if (marks[i][j] - '0' == best) {
                success[i] = true;
            }
        }
    }
    int result = 0;
    for (int i = 0; i < n; ++ i) {
        result += success[i];
    }
    printf("%d\n", result);
    return 0;
}
