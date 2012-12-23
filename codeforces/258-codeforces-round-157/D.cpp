// Codeforces Round #157
// Problem D -- Little Elephant and Broken Sorting
#include <cstdio>
#include <cstring>

const int N = 1000;

int n, m, p[N], a[N], b[N];
double prob[N][N];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        scanf("%d", p + i);
    }
    for (int i = 0; i < m; ++ i) {
        scanf("%d%d", a + i, b + i);
        a[i] --;
        b[i] --;
    }
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            prob[i][j] = i < j ? 1.0 : 0.0;
        }
    }
    for (int k = m - 1; k >= 0; -- k) {
        int u = a[k];
        int v = b[k];
        for (int i = 0; i < n; ++ i) {
            if (i != u && i != v) {
                prob[i][u] = prob[i][v] = (prob[i][u] + prob[i][v]) / 2.0;
                prob[u][i] = prob[v][i] = (prob[u][i] + prob[v][i]) / 2.0;
            }
        }
        prob[u][v] = prob[v][u] = (prob[u][v] + prob[v][u]) / 2.0;
    }
    double answer = 0;
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            if (p[i] > p[j]) {
                answer += prob[i][j];
            }
        }
    }
    printf("%.10f\n", answer);
    return 0;
}
