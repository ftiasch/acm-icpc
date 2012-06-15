// SGU 160 -- Magic Multiplying Machine
#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

const int N = 10001;
const int M = 1000;

int n, m, a[N], choice[N][M];
bool can[N][M];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", a + i);
    }
    memset(can, 0, sizeof(can));
    can[0][1] = true;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < m; ++ j) {
            if (can[i][j]) {
                can[i + 1][j] = true;
                choice[i + 1][j] = j;
                can[i + 1][(j * a[i]) % m] = true;
                choice[i + 1][(j * a[i]) % m] = j;
            }
        }
    }
    int j = m - 1;
    while (!can[n][j]) {
        j --;
    }
    printf("%d\n", j);
    vector <int> result;
    for (int i = n; i >= 1; -- i) {
        if ((choice[i][j] * a[i - 1]) % m == j) {
            j = choice[i][j];
            result.push_back(i);
        }
    }
    reverse(result.begin(), result.end());
    for (int i = 0; i < (int)result.size(); ++ i) {
        printf("%d%c", result[i], i == (int)result.size() - 1? '\n': ' ');
    }
    return 0;
}
