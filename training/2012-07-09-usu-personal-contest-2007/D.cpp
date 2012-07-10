#include <cstdio>
#include <cstring>
#include <vector>
using namespace std;


int main() {
    int n;
    scanf("%d", &n);
    if (n % 2 == 1) {
        for (int i = 1; i <= n; ++ i) {
            printf("%d ", i % 2 == 1? i: n - i + 1);
        }
    } else {
        n /= 2;
        vector <int> v;
        for (int i = 1; i <= n; i += 2) {
            v.push_back(i);
        }
        for (int i = n & ~1; i >= 1; i -= 2) {
            v.push_back(i);
        }
        for (int i = 0; i < 2 * n; ++ i) {
            printf("%d ", i % 2 == 0? v[i / 2]: 2 * n - v[n - i / 2 - 1] + 1);
        }
        n *= 2;
    }
    puts("");
    for (int i = 1; i <= n; i += 2) {
        printf("%d ", i);
    }
    for (int i = n % 2 == 0? n: n - 1; i >= 1; i -= 2) {
        printf("%d ", i);
    }
    puts("");
    return 0;
}

