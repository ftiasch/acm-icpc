// Northeastern Europe 2007
// Problem B -- Building for UN
#include <cstdio>
#include <cstring>
using namespace std;

int n;

char map(int i) {
    if (i < 26) {
        return 'a' + i;
    }
    return 'A' + i - 26;
}

int main() {
    scanf("%d", &n);
    printf("%d %d %d\n", 2, n, n);
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            printf("%c", map(i));
        }
        puts("");
    }
    puts("");
    for (int i = 0; i < n; ++ i) {
        for (int j = 0; j < n; ++ j) {
            printf("%c", map(j));
        }
        puts("");
    }
    return 0;
}
