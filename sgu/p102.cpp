// SGU 102 -- Coprimes
#include <cstdio>
#include <cstring>
using namespace std;

int phi(int n) {
    int result = n;
    for (int i = 2; i * i <= n; ++ i) {
        if (n % i == 0) {
            result = result / i * (i - 1);
            while (n % i == 0) {
                n /= i;
            }
        }
    }
    if (n > 1) {
        result = result / n * (n - 1);
    }
    return result;
}

int main() {
    int n;
    scanf("%d", &n);
    printf("%d\n", phi(n));
    return 0;
}
