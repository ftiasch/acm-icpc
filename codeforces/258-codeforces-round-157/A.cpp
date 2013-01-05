// Codeforces Round #157
// Problem A -- Little Elephant and Bits
#include <cstdio>
#include <cstring>

const int N = 100000;

char s[N + 1];

int main() {
    scanf("%s", s);
    int n = strlen(s);
    for (int i = 0; i < n; ++ i) {
        if (s[i] == '0') {
            for (int j = 0; j < n; ++ j) {
                if (i != j) {
                    putchar(s[j]);
                }
            }
            puts("");
            return 0;
        }
    }
    printf("%s\n", s + 1);
    return 0;
}
