// Codeforces Beta Round #18
// Problem C -- Stripe
#include <cstdio>
#include <map>
#include <algorithm>


int main() {
    int n;
    scanf("%d", &n);
    int sum = 0;
    std::map <int, int> map;
    for (int i = 0; i < n; ++ i) {
        int a;
        scanf("%d", &a);
        sum += a;
        if (i != n - 1) {
            map[sum] ++;
        }
    }
    if (sum % 2 == 0) {
        printf("%d\n", map[sum / 2]);
    } else {
        puts("0");
    }
    return 0;
}
