// Codeforces Round #135
// Problem A -- k-String
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 1000;

int n, k, counter[26];
char string[N + 1];

int main() {
    scanf("%d%s", &k, string);
    n = strlen(string);
    memset(counter, 0, sizeof(counter));
    for (int i = 0; i < n; ++ i) {
        counter[string[i] - 'a'] ++;
    }
    for (int i = 0; i < 26; ++ i) {
        if (counter[i] % k != 0) {
            puts("-1");
            return 0;
        }
    }
    for (int i = 0; i < k; ++ i) {
        for (int j = 0; j < 26; ++ j) {
            for (int _ = 0; _ < counter[j] / k; ++ _) {
                putchar('a' + j);
            }
        }
    }
    puts("");
    return 0;
}
