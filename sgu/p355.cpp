// SGU 355 -- Numbers Painting
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 10000;

int n, maximum[N + 1];

int main() {
    scanf("%d", &n);
    memset(maximum, 0, sizeof(maximum));
    for (int i = 1; i <= n; ++ i) {
        maximum[i] = max(maximum[i], 1);
        for (int j = 2 * i; j <= n; j += i) {
            maximum[j] = max(maximum[j], maximum[i] + 1);
        }
    }
    int limit = *max_element(maximum + 1, maximum + 1 + n);
    printf("%d\n", limit);
    for (int i = 1; i <= n; ++ i) {
        printf("%d%c", maximum[i], i == n? '\n': ' ');
    }
    return 0;
}
