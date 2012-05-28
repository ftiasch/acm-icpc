// SGU 104 -- Little shop of flowers
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 111;
const int INF = 1000000000;

int n, m, value[N][N], maximum[N][N];
bool choice[N][N];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            scanf("%d", &value[i][j]);
        }
    }
    memset(maximum, 0, sizeof(maximum));
    memset(choice, 0, sizeof(choice));
    for (int i = n - 1; i >= 0; -- i) {
        maximum[i][m] = -INF;
        for (int j = m - 1; j >= 0; -- j) {
            maximum[i][j] = maximum[i][j + 1];
            if (maximum[i + 1][j + 1] + value[i][j] > maximum[i][j]) {
                choice[i][j] = true;
                maximum[i][j] = maximum[i + 1][j + 1] + value[i][j];
            }
        }
    }
    printf("%d\n", maximum[0][0]);
    for (int i = 0, j = 0; i < n; ++ j) {
        if (choice[i][j]) {
            printf("%d%c", j + 1, i + 1 == n? '\n': ' ');
            i ++;
        }
    }
    return 0;
}
