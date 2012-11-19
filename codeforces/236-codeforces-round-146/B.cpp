// Codeforces Round #146
// Problem B -- Easy Number Challenge
#include <cstdio>
#include <cstring>
using namespace std;

const int N = 10000000;

int divisor[N + 1];
unsigned sigma[N + 1];

int main() {
    memset(divisor, -1, sizeof(divisor));
    for (int i = 2; i <= N; ++ i) {
        if (divisor[i] == -1) {
            divisor[i] = i;
            if ((long long)i * i <= N) {
                for (int j = i * i; j <= N; j += i) {
                    divisor[j] = i;
                }
            }
        }
    }
    sigma[1] = 1;
    for (int i = 2; i <= N; ++ i) {
        int &d = divisor[i];
        int e = 0;
        int j = i;
        while (j % d == 0) {
            j /= d;
            e ++;
        }
        sigma[i] = sigma[j] * (e + 1);
    }
    int a, b, c;
    scanf("%d%d%d", &a, &b, &c);
    unsigned answer = 0;
    for (int i = 1; i <= a; ++ i) {
        for (int j = 1; j <= b; ++ j) {
            for (int k = 1; k <= c; ++ k) {
                answer += sigma[i * j * k];
            }
        }
    }
    printf("%d\n", (int)answer % (1 << 30));
    return 0;
}
