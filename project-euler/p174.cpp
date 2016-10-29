#include <cstdio>

const int N = 1000000;

int count[N + 1];

int main()
{
    for (int i = 2; i <= N; i += 2) {
        for (int j = i + 2; (long long)i * j <= N; j += 2) {
            count[i * j] ++;
        }
    }
    int result = 0;
    for (int i = 1; i <= N; ++ i) {
        result += 1 <= count[i] && count[i] <= 10;
    }
    printf("%d\n", result);
}
