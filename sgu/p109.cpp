#include <cstdio>
#include <cstring>
using namespace std;

int n;

int id(int i, int j) {
    return i * n + j + 1;
}

int main() {
    scanf("%d", &n);
    printf("%d", n);
    if (n == 2) {
        printf("3 4\n5 2 3\n");
    } else {
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                if (i + j > n) {
                    printf(" %d", id(i, j));
                }
            }
        }
        puts("");
        int step = (n + 1) | 1;
        for (int sum = n; sum >= 1; -- sum) {
            printf("%d", step);
            for (int i = 0; i < n; ++ i) {
                if (0 <= sum - i && sum - i < n) {
                    printf(" %d", id(i, sum - i));
                }
            }
            puts("");
            step += 2;
        }
    }
    return 0;
}
