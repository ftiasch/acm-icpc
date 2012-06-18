// SGU 163 -- Wise King
#include <cstdio>
#include <cstring>
using namespace std;

int n, k;

int power(int a, int n) {
    int result = 1;
    for (int i = 0; i < n; ++ i) {
        result *= a;
    }
    return result;
}

int main() {
    scanf("%d%d", &n, &k);
    int result = 0;
    for (int i = 0; i < n; ++ i) {
        int a_i;
        scanf("%d", &a_i);
        if (power(a_i, k) > 0) {
            result += power(a_i, k);
        }
    }
    printf("%d\n", result);
    return 0;
}
