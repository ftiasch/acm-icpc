// Codeforces Round #184
// Problem C -- Ivan and Powers of Two
#include <cstdio>
#include <cstring>
#include <vector>

int n;

int main() {
    scanf("%d", &n);
    int now = 0;
    int count = 0;
    int answer = 0;
    for (int i = 0; i < n; ++ i) {
        int a;
        scanf("%d", &a);
        while (now < a && count) {
            now ++;
            answer -= count & 1;
            count >>= 1;
        }
        now = a;
        count ++;
    }
    while (count) {
        now ++;
        answer -= count & 1;
        count >>= 1;
    }
    answer += now;
    printf("%d\n", answer);
    return 0;
}
