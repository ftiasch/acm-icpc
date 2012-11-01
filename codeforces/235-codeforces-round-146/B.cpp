// Codeforces Round #146
// Problem B -- Let's Play Osu!
#include <cstdio>
#include <cstring>
using namespace std;

int main() {
    int n;
    scanf("%d", &n);
    double result = 0;
    double sum = 0;
    for (int i = 0; i < n; ++ i) {
        double p;
        scanf("%lf", &p);
        sum = (1 + sum) * p;
        result += 2 * sum;
        result -= p;
    }
    printf("%.8f\n", result);
    return 0;
}
