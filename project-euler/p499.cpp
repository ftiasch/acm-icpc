#include <cmath>
#include <cstdio>

double eval(double x)
{
    double result = 1.;
    for (int _ = 0; _  < 15; ++ _) {
        result *= x;
    }
    double power = .5;
    for (int _ = 0; _ < 50; ++ _) {
        result -= x * power;
        x *= x;
        power *= .5;
    }
    return result;
}

int main()
{
    double low = 0.9;
    double high = 1.0;
    double f_low = eval(low);
    double f_high = eval(high);
    for (int _ = 0; _ < 100; ++ _) {
        double middle = .5 * (low + high);
        double f_middle = eval(middle);
        if (f_low * f_middle < 0.) {
            high = middle;
            f_high = f_middle;
        } else {
            low = middle;
            f_low = f_middle;
        }
    }
    double x = .5 * (low + high);
    printf("%.7f\n", 1. - pow(x, 1e9));
}
