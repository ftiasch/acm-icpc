// SGU 278 -- Fuel
#include <cmath>
#include <cstdio>
#include <cstring>
#include <deque>
#include <complex>
#include <algorithm>

typedef std::complex <double> Point;

const int N = 75000;

int n, d, e, a[N], b[N], c[N], order[N];

bool compare(int i, int j) {
    int det = a[i] * b[j] - a[j] * b[i];
    return det > 0;
}

double get_distance(int u) {
    return c[u] / sqrt(a[u] * a[u] + b[u] * b[u]);
}

Point intersect(int u, int v) {
    int det = a[u] * b[v] - a[v] * b[u];
    return Point(b[v] * c[u] - b[u] * c[v], a[u] * c[v] - a[v] * c[u]) / (double)det;
}

bool is_convex(int u, int v, int w) {
    Point p = intersect(v, w);
    return a[u] * p.real() + b[u] * p.imag() > c[u];
}

int main() {
    scanf("%d%d%d", &n, &d, &e);
    for (int i = 0; i < n; ++ i) {
        scanf("%d%d%d", a + i, b + i, c + i);
        order[i] = i;
    }
    std::sort(order, order + n, compare);
    std::deque <int> stack;
    for (int i = 0; i < n; ++ i) {
        int u = order[i];
        if (!stack.empty()) {
            int v = stack.back();
            if (!compare(u, v) && !compare(v, u)) {
                if (get_distance(v) >= get_distance(u)) {
                    continue;
                }
                stack.pop_back();
            }
        }
        while ((int)stack.size() >= 2 && !is_convex(stack[(int)stack.size() - 2], stack.back(), u)) {
            stack.pop_back();
        }
        stack.push_back(u);
    }
    while ((int)stack.size() >= 2) {
        Point p = intersect(stack.front(), stack[1]);
        if (p.real() >= 0) {
            break;
        }
        stack.pop_front();
    }
    while ((int)stack.size() >= 2) {
        Point p = intersect(stack[(int)stack.size() - 2], stack.back());
        if (p.imag() >= 0) {
            break;
        }
        stack.pop_back();
    }
    double answer = std::min((double)e * c[stack.front()] / b[stack.front()], (double)d * c[stack.back()] / a[stack.back()]);
    for (int i = 1; i < (int)stack.size(); ++ i) {
        Point p = intersect(stack[i - 1], stack[i]);
        answer = std::min(answer, d * std::max(p.real(), 0.0) + e * std::max(p.imag(), 0.0));
    }
    printf("%.8f\n", answer);
    return 0;
}
