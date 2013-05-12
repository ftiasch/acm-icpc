// Testing Round #6
// Problem D -- Polygon 
#include <cassert>
#include <cmath>
#include <cstdio>
#include <cstring>
#include <complex>
#include <iostream>
#include <algorithm>

typedef std::complex <double> Complex;

const double PI = acos(-1.0);

const int N = 100;
const double EPS = 1e-8;

int n;
double length[N];
Complex dirs[N];

int signum(double x) {
    return x < -EPS ? -1 : x > EPS;
}

bool valid() {
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < i; ++ j) {
            if (signum(length[i] - length[j]) == 0) {
                return false;
            }
        }
    }
    return true;
}

double dot(const Complex &a, const Complex &b) {
    return a.real() * b.real() + a.imag() * b.imag();
}

int main() {
    scanf("%d", &n);
    if (n <= 4) {
        puts("No solution");
    } else {
        double theta = 2 * PI / n;
        Complex delta = 0;
        for (int i = 0; i < n; ++ i) {
            dirs[i] = Complex(cos(theta * i), sin(theta * i));
            length[i] = (double)rand() / RAND_MAX;
            delta += dirs[i] * length[i];
        }
        while (signum(delta.imag()) != 0) {
            int i = rand() % n;
            if (signum(dirs[i].imag()) == 0) {
                continue;
            }
            double d = -delta.imag() / dirs[i].imag();
            if (0 < length[i] + d) {
                length[i] += d;
                delta += dirs[i] * d;
            }
        }
        while (signum(delta.real()) != 0) {
            int i = rand() % n;
            if (signum(dirs[i].real()) == 0) {
                continue;
            }
            int j = (n - i) % n;
            double d = -delta.real() / dirs[i].real() / 2;
            if (0 < length[i] + d && 0 < length[j] + d) {
                length[i] += d;
                length[j] += d;
                delta += dirs[i] * d;
                delta += dirs[j] * d;
            }
        }
        Complex now;
        for (int i = 0; i < n; ++ i) {
            length[i] += 1.0;
            length[i] *= 10.0;
            printf("%.8f %.8f\n", now.real(), now.imag());
            now += length[i] * dirs[i];
        }
    }
    return 0;
}
