// Codeforces Beta Round #34
// Problem E -- Collisions
#include <cstdio>
#include <cstring>
#include <algorithm>

const int N = 10;
const double EPS = 1e-9;

int signum(double x) {
    return x < -EPS ? -1 : x > EPS;
}

int n, order[N];
double t, x[N], v[N], m[N];

bool by_x(int i, int j) {
    return x[i] < x[j];
}

void deal(double &v_1, double &v_2, double m_1, double m_2) {
    double vv_1 = v_1;
    double vv_2 = v_2;
    v_1 = ((m_1 - m_2) * vv_1 + 2 * m_2 * vv_2) / (m_1 + m_2);
    v_2 = ((m_2 - m_1) * vv_2 + 2 * m_1 * vv_1) / (m_1 + m_2);
}

int main() {
    scanf("%d%lf", &n, &t);
    for (int i = 0; i < n; ++ i) {
        scanf("%lf%lf%lf", x + i, v + i, m + i);
        order[i] = i;
    }
    std::sort(order, order + n, by_x);
    while (t > 0) {
        double next = t;
        for (int i = 1; i < n; ++ i) {
            if (signum(v[order[i - 1]] - v[order[i]]) == 0) {
                continue;
            }
            double ret = (x[order[i]] - x[order[i - 1]]) / (v[order[i - 1]] - v[order[i]]);
            if (ret > 0) {
                next = std::min(next, ret);
            }
        }
        t -= next;
        for (int i = 0; i < n; ++ i) {
            x[i] += v[i] * next;
        }
        for (int i = 1; i < n; ++ i) {
            if (signum(x[order[i - 1]] - x[order[i]]) == 0) {
                deal(v[order[i - 1]], v[order[i]], m[order[i - 1]], m[order[i]]);
            }
        }
    }
    for (int i = 0; i < n; ++ i) {
        printf("%.8f\n", x[i]);
    }
    return 0;
}
