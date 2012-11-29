// 444. Headstrong Student
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 1000000;

int mark[N];

int main() {
    int a, b;
    scanf("%d%d", &a, &b);
    a %= b;
    memset(mark, -1, sizeof(mark));
    int length = 0;
    while (a) {
        if (mark[a] != -1) {
            printf("%d %d\n", mark[a], length - mark[a]);
            return 0;
        }
        mark[a] = length;
        (a *= 10) %= b;
        length ++;
    }
    printf("%d %d\n", length, 0);
    return 0;
}
