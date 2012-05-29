#include <cstdio>
#include <cstring>
using namespace std;

int n, m, k;

int pow_mod(int a, int n, int m) {
    int result = 1 % m;
    while (n > 0) {
        if ((n & 1) == 1) {
            result = (result * a) % m;
        }
        a = (a * a) % m;
        n >>= 1;
    }
    return result;
}

int main() {
    scanf("%d%d%d", &n, &m, &k);
    int result = 0;
    for (int i = 0; i < n; ++ i) {
        int a_i;
        scanf("%d", &a_i);
        if (pow_mod(a_i, m, k) == 0) {
            result ++;
        }
    }
    printf("%d\n", result);
    return 0;
}
