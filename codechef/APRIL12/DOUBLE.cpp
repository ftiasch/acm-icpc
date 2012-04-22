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
        printf("%d\n", n & ~1);
    }
    return 0;
}
