// Codeforces Round #135
// Problem C -- Color Stripe
#include <cstdio>
#include <cstring>
#include <climits>
#include <algorithm>
using namespace std;

const int N = 500000 + 1;
const int M = 26;

int n, m, minimum[N][M];
short choice[N][M];
char text[N];

int main() {
    scanf("%d%d%s", &n, &m, text);
    for (int j = 0; j < m; ++ j) {
        minimum[n][j] = 0;
    }
    for (int i = n - 1; i >= 0; -- i) {
        int best = min_element(minimum[i + 1], minimum[i + 1] + m) - minimum[i + 1];
        for (int j = 0; j < m; ++ j) {
            if (j != best) {
                choice[i][j] = best;
                minimum[i][j] = minimum[i + 1][best];
            } else {
                minimum[i][j] = INT_MAX;
                for (int k = 0; k < m; ++ k) {
                    if (k != j && minimum[i + 1][k] < minimum[i][j]) {
                        choice[i][j] = k;
                        minimum[i][j] = minimum[i + 1][k];
                    }
                }
            }
            minimum[i][j] += text[i] - 'A' != j;
        }
    }
    int j = min_element(minimum[0], minimum[0] + m) - minimum[0];
    printf("%d\n", minimum[0][j]);
    for (int i = 0; i < n; ++ i) {
        putchar('A' + j);
        j = choice[i][j];
    }
    puts("");
    return 0;
}
