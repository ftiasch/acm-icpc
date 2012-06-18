// Problem A -- Quicksum
#include <cstdio>
#include <cstring>
using namespace std;

int main() {
    char buffer[300];
    while (true) {
        fgets(buffer, 300, stdin);
        if (*buffer == '#') {
            break;
        }
        int length = strlen(buffer);
        int result = 0;
        for (int i = 0; i < length; ++ i) {
            if ('A' <= buffer[i] && buffer[i] <= 'Z') {
                result += (i + 1) * (buffer[i] -'A' + 1);
            }
        }
        printf("%d\n", result);
    }
    return 0;
}
