// SGU 127 -- Telephone directory
#include <cstdio>
#include <cstring>
using namespace std;

int m, n, number_count[10];

int main() {
    scanf("%d%d", &m, &n);
    memset(number_count, 0, sizeof(number_count));
    for (int i = 0; i < n; ++ i) {
        int number;
        scanf("%d", &number);
        int first = number / 1000;
        number_count[first] ++;
    }
    int result = 2;
    for (int i = 0; i < 10; ++ i) {
        result += (number_count[i] + (m - 1)) / m;
    }
    printf("%d\n", result);
    return 0;
}
