// Problem B -- Hide That Number
#include <cstdio>
#include <cstring>
using namespace std;

char buffer[1111111];

int main() {
    int test_count = 0;
    while (true) {
        scanf("%s", buffer);
        if (strcmp(buffer, "0") == 0) {
            break;
        }
        int length = strlen(buffer);
        int remainder = 0;
        for (int i = 0; i < length; ++ i) {
            remainder = (remainder * 10 + buffer[i] - '0') % 11;
        }
        printf("%d. ", ++ test_count);
        if (remainder == 0) {
            puts("IMPOSSIBLE");
        } else {
            if ((length & 1) == 0) {
                remainder = 11 - remainder;
            }
            for (int i = 0; i < length; ++ i) {
                remainder = remainder * 10 + buffer[i] - '0';
                printf("%d", remainder / 11);
                remainder %= 11;
            }
            puts("");
        }
    }
    return 0;
}
