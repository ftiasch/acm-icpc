// SGU 118 -- Digital Root
#include <cstdio>
#include <cstring>
using namespace std;

int main() {
    int test_count;
    scanf("%d", &test_count);
    while (test_count > 0) {
        test_count --;
        int n;
        scanf("%d", &n);
        int a[n];
        for (int i = 0; i < n; ++ i) {
            scanf("%d", a + i);
        }
        if (a[0] == 0) {
            puts("0");
        } else {
            int result = 0;
            for (int i = n - 1; i >= 0; -- i) {
                result = ((long long)result + 1) * a[i] % 9;
            }
            printf("%d\n", result == 0? 9: result);
        }
    }
    return 0;
}
