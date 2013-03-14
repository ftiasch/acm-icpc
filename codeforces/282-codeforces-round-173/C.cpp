#include <cstdio>
#include <cstring>

const int N = 1000000;

char a[N + 1], b[N + 1];

int main() {
    scanf("%s%s", a, b);
    if (strlen(a) != strlen(b)) {
        puts("NO");
    } else {
        int n = strlen(a);
        int i = 0;
        while (a[i] == '0') {
            i ++;
        }
        int j = 0;
        while (b[j] == '0') {
            j ++;
        }
        puts((i < n) ^ (j < n) ? "NO": "YES");
    }
    return 0;
}
