// SGU 169 -- Numbers
#include <cstdio>
#include <cstring>
using namespace std;

int main() {
    int length;
    scanf("%d", &length);
    int prefix = 0;
    for (int i = 0; i < length; ++ i) {
        prefix = (prefix * 10 + (i == length - 1? 0: 1)) % 2520;
    }
    int result = 0;
    for (int digit = 1; digit < 9; ++ digit) {
        if ((prefix + digit) % digit == 0 && (prefix + digit + 1) % (digit + 1) == 0) {
            result ++;
        }
    }
    printf("%d\n", result);
    return 0;
}
