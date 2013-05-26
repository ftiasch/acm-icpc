// Codeforces Alpha Round #20 
// Problem B -- Equation
#include <cstdio>
#include <cstring>
#include <cmath>
#include <vector>
#include <algorithm>

int a, b, c;

int main() {
    scanf("%d%d%d", &a, &b, &c);
    std::vector <double> solutions;
    if (a == 0) {
        if (b == 0) {
            if (c == 0) {
                puts("-1");
                return 0;
            } 
        } else {
            solutions.push_back(-(double)c / b);
        }
    } else {
        long long delta = (long long)b * b - 4LL * a * c;
        if (delta > 0) {
            solutions.push_back(0.5 * (-b - sqrt(delta)) / a);
            solutions.push_back(0.5 * (-b + sqrt(delta)) / a);
        } else if (delta == 0) {
            solutions.push_back(-0.5 * b / a);
        }
    }
    printf("%d\n", (int)solutions.size());
    std::sort(solutions.begin(), solutions.end());
    for (int i = 0; i < (int)solutions.size(); ++ i) {
        printf("%.10f\n", solutions[i]);
    }
    return 0;
}
