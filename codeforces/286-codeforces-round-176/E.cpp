// Codeforces Round #176
// Problem E -- Ladies' Shop
#include <cmath>
#include <cstdio>
#include <complex>
#include <algorithm>

const double PI = acos(-1.0);

typedef std::complex <double> Complex;

void sincos(double p, double *y, double *x) {
    *y = sin(p);
    *x = cos(p);
}

void FFT(Complex P[], int n, int oper) {
    for (int i = 1, j = 0; i < n - 1; i++) {
        for (int s = n; j ^= s >>= 1, ~j & s;);
        if (i < j) {
            swap(P[i], P[j]);
        }
    }
    Complex unit_p0;
    for (int d = 0; (1 << d) < n; d++) {
        int m = 1 << d, m2 = m * 2;
        double p0 = PI / m * oper;
        sincos(p0, &unit_p0.imag(), &unit_p0.real());
        for (int i = 0; i < n; i += m2) {
            Complex unit = 1;
            for (int j = 0; j < m; j++) {
                Complex &P1 = P[i + j + m], &P2 = P[i + j];
                Complex t = unit * P1;
                P1 = P2 - t;
                P2 = P2 + t;
                unit = unit * unit_p0;
            }
        }
    }
}

const int N = 1 << 21;

Complex ps[N];
bool a[N];

int main() {
    int n, m;
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; ++ i) {
        int ai;
        scanf("%d", &ai);
        a[ai] = true;
        ps[ai] = 1;
    }
    FFT(ps, N, 1);
    for (int i = 0; i < N; ++ i) {
        ps[i] *= ps[i];
    }
    FFT(ps, N, -1);
    int answer = 0;
    for (int i = 0; i <= m; ++ i) {
        int p = (int)(ps[i].real() / N + 0.5);
        if (p) {
            if (!a[i]) {
                puts("NO");
                return 0;
            }
            a[i] = false;
        }
    }
    for (int i = 0; i <= m; ++ i) {
        answer += a[i];
    }
    puts("YES");
    printf("%d\n", answer);
    for (int i = 0; i <= m; ++ i) {
        if (a[i]) {
            printf("%d ", i);
        }
    }
    puts("");
    return 0;
}
