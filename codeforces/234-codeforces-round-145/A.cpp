// Codeforces Round #145
// Problem A -- Lefthanders and Righthanders
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 100;

int n;
char type[N + 2];

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    scanf("%d%s", &n, type + 1);
    for (int i = 1; i <= n >> 1; ++ i) {
        if (type[i] == 'R' && type[i + (n >> 1)] == 'L') {
            printf("%d %d\n", i + (n >> 1), i);
        } else {
            printf("%d %d\n", i, i + (n >> 1));
        }
    }
    return 0;
}
