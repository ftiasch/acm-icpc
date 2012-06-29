// Problem G -- Interesting Game
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 111;
const double EPS = 1e-9;

double coeff[N][N];

int sgn(double x) {
    return x < -EPS? -1: x > EPS;
}

int main() {
    int testCount;
    scanf("%d", &testCount);
    while (testCount > 0) {
        testCount --;
        int a, b, c, d;
        double p;
        scanf("%d%d%d%d%lf", &a, &b, &c, &d, &p);
        int n = a + b;
        memset(coeff, 0, sizeof(coeff));
        for (int i = 0; i <= n; ++ i) {
            int j = n - i;
            coeff[i][i] += 1;
            if (i - c >= 0) {
                coeff[i][i - c] -= p;
            }
            if (j - d >= 0) {
                coeff[i][i + d] -= 1 - p;
            } else {
                coeff[i][n + 1] += 1 - p;
            }
        }
        for (int k = 0; k <= n; ++ k) {
            int pivot = k;
            while (pivot <= n && sgn(coeff[pivot][k]) == 0) {
                pivot ++;
            }
            for (int i = 0; i <= n + 1; ++ i) {
                swap(coeff[k][i], coeff[pivot][i]);
            }
            for (int i = 0; i <= n; ++ i) {
                if (i != k && sgn(coeff[i][k]) != 0) {
                    double times = coeff[i][k] / coeff[k][k];
                    for (int j = 0; j <= n + 1; ++ j) {
                        coeff[i][j] -= coeff[k][j] * times;
                    }
                }
            }
        }
        printf("%.2f\n", coeff[a][n + 1] / coeff[a][a]);
    }
    return 0;
}
