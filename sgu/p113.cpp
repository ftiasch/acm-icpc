#include <cstdio>
using namespace std;

bool check(int n) {
    int prime_count = 0;
    for (int i = 2; i * i <= n; ++ i) {
        while (n % i == 0) {
            prime_count ++;
            n /= i;
        }
    }
    if (n > 1) {
        prime_count ++;
    }
    return prime_count == 2;
}

int main() {
    int test_count;
    scanf("%d", &test_count);
    while (test_count > 0) {
        test_count --;
        int n;
        scanf("%d", &n);
        puts(check(n)? "Yes": "No");
    }
    return 0;
}
